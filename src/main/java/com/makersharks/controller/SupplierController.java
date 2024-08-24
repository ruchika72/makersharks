package com.makersharks.controller;

import com.makersharks.model.Supplier;
import com.makersharks.model.NatureOfBusiness;
import com.makersharks.model.ManufacturingProcess;
import com.makersharks.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.RequestEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.*;

@RestController
@RequestMapping("/api/supplier")
@Validated
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public List <String> getList() {
        List <Supplier> list = supplierService.getAll();
        List <String> res = new ArrayList <> ();
        for (Supplier s : list) 
            res.add(s.toString());
        return res;
    }

    @PostMapping("/query")
    public Page<Supplier> querySuppliers(
            @RequestParam @NotBlank @Size(min = 2, max = 50) String location,
            @RequestParam @Pattern(regexp = "SMALL_SCALE|MEDIUM_SCALE|LARGE_SCALE") String natureOfBusiness,
            @RequestParam @Pattern(regexp = "MOULDING|THREE_D_PRINTING|CASTING|COATING") String process,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
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
