package com.makersharks.model;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Set;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    private String companyName;
    private String website;
    private String location;

    @Enumerated(EnumType.STRING)
    private NatureOfBusiness natureOfBusiness;

    @ElementCollection(targetClass = ManufacturingProcess.class)
    @CollectionTable(name = "supplier_processes", joinColumns = @JoinColumn(name = "supplier_id"))
    @Enumerated(EnumType.STRING)
    private Set<ManufacturingProcess> manufacturingProcesses;

    // Getters and Setters
    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", companyName='" + companyName + '\'' +
                ", website='" + website + '\'' +
                ", location='" + location + '\'' +
                ", natureOfBusiness='" + natureOfBusiness + '\'' +
                ", manufacturingProcesses=" + manufacturingProcesses +
                '}';
    }
}
