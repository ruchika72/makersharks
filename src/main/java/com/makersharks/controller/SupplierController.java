package com.makersharks.controller;

import com.makersharks.model.Supplier;
import com.makersharks.model.NatureOfBusiness;
import com.makersharks.model.ManufacturingProcess;
import com.makersharks.service.SupplierService;

import org.hibernate.sql.ast.tree.select.QueryPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.makersharks.utils.*;
import java.util.*;

@RestController
@RequestMapping("/api/supplier")
@Validated
@Tag(name = "Supplier", description = "API for Supplier operations")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Operation(summary = "Get List of Suppliers", description = "Retrieve a list of all suppliers in the database.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/search")
    public List<String> getList(@RequestParam String query) {
        QueryParser q = new QueryParser(query);
        HashMap<String, String> queryMap = q.getKeyWords();
        System.out.println(queryMap);
        var business = NatureOfBusiness.valueOf(queryMap.get("natureOfBusiness"));
        var process = ManufacturingProcess.valueOf(queryMap.get("process"));
        List<Supplier> list = supplierService.searchSuppliers(queryMap.get("location"), business, process);
        List<String> res = new ArrayList<>();
        for (Supplier s : list)
            res.add(s.toString());
        return res;
    }

    @GetMapping
    public String hello() {
        return "Hello";
    }

    @Operation(summary = "Query Suppliers", description = "Search for suppliers by location, nature of business, and manufacturing process with pagination support.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully queried suppliers", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Supplier.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/query")
    public Page<Supplier> querySuppliers(
            @Parameter(description = "Location of the supplier", example = "India")
            @RequestParam @NotBlank @Size(min = 2, max = 50) String location,

            @Parameter(description = "Nature of the business", example = "SMALL_SCALE", required = true)
            @RequestParam @Pattern(regexp = "SMALL_SCALE|MEDIUM_SCALE|LARGE_SCALE") String natureOfBusiness,

            @Parameter(description = "Manufacturing process capability", example = "THREE_D_PRINTING", required = true)
            @RequestParam @Pattern(regexp = "MOULDING|THREE_D_PRINTING|CASTING|COATING") String process,

            @Parameter(description = "Page number for pagination", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Page size for pagination", example = "10")
            @RequestParam(defaultValue = "10") int size,

            @Parameter(description = "Sort criteria in the format: property, direction", example = "supplierId,asc")
            @RequestParam(defaultValue = "supplierId,asc") String[] sort) {

        // Convert to enum types
        NatureOfBusiness natureEnum = NatureOfBusiness.valueOf(natureOfBusiness.toUpperCase());
        ManufacturingProcess processEnum = ManufacturingProcess.valueOf(process.toUpperCase());

        // Create a Sort object
        Sort sortObj = Sort.by(Sort.Order.by(sort[0]).with(Sort.Direction.fromString(sort[1])));

        // Create a Pageable object
        Pageable pageable = PageRequest.of(page, size, sortObj);

        // Query the suppliers with pagination
        return supplierService.findSuppliers(location, natureEnum, processEnum, pageable);
    }
}
