package com.makersharks.repository;

import com.makersharks.model.Supplier;
import com.makersharks.model.NatureOfBusiness;
import com.makersharks.model.ManufacturingProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    
    @Query("SELECT s FROM Supplier s WHERE s.location = :location AND s.natureOfBusiness = :natureOfBusiness AND :process MEMBER OF s.manufacturingProcesses")
    List<Supplier> findByLocationNatureAndProcess(
            @Param("location") String location,
            @Param("natureOfBusiness") NatureOfBusiness natureOfBusiness,
            @Param("process") ManufacturingProcess process);
}
