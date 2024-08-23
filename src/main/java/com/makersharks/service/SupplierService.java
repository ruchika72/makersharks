package com.makersharks.service;

import com.makersharks.model.Supplier;
import com.makersharks.model.NatureOfBusiness;
import com.makersharks.model.ManufacturingProcess;
import com.makersharks.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> searchSuppliers(String location, NatureOfBusiness natureOfBusiness, ManufacturingProcess process) {
        List<Supplier> list = supplierRepository.findByLocationNatureAndProcess(location, natureOfBusiness, process);
        System.out.println(list.toString());
        return list;
    }

    public List<Supplier> findAllSuppliers() {
        List<Supplier> list = supplierRepository.findAll();
        System.out.println(list.toString());
        System.out.println(list.size());
        return list;
    }
}
