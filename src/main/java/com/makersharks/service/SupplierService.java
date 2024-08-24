package com.makersharks.service;

import com.makersharks.model.Supplier;
import com.makersharks.model.NatureOfBusiness;
import com.makersharks.model.ManufacturingProcess;
import com.makersharks.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Page<Supplier> findSuppliers(String location, NatureOfBusiness natureOfBusiness, ManufacturingProcess process, Pageable pageable) {
        return supplierRepository.findByLocationNatureAndProcess(location, natureOfBusiness, process, pageable);
    }

    public List<Supplier> searchSuppliers(String location, NatureOfBusiness natureOfBusiness, ManufacturingProcess process) {
        List<Supplier> list = supplierRepository.findByLocationNatureAndProcess(location, natureOfBusiness, process);
        System.out.println(list.toString());
        return list;
    }

    public List <Supplier> getAll() {
        return supplierRepository.findAll();
    }
}
