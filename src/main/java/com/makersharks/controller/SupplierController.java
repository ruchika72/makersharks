package com.makersharks.controller;

import com.makersharks.model.Supplier;
import com.makersharks.model.NatureOfBusiness;
import com.makersharks.model.ManufacturingProcess;
import com.makersharks.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/query")
    public List<Supplier> searchSuppliers(@RequestParam String location,
                                          @RequestParam NatureOfBusiness natureOfBusiness,
                                          @RequestParam ManufacturingProcess process,
                                          @RequestParam(defaultValue = "10") int limit) {
        List<Supplier> suppliers = supplierService.searchSuppliers(location, natureOfBusiness, process);
        
        return suppliers.subList(0, Math.min(limit, suppliers.size()));
    }

    @PostMapping("/all")
    public List<Supplier> findAllSuppliers() {
        List<Supplier> suppliers = supplierService.findAllSuppliers();
        return suppliers;
    }

    @GetMapping
    public String hello(){
        return "hello";
    }

}
