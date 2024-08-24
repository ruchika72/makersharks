package com.makersharks;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.*;
import com.makersharks.model.Supplier;
import com.makersharks.model.NatureOfBusiness;
import com.makersharks.controller.SupplierController;
import com.makersharks.model.ManufacturingProcess;
import com.makersharks.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

class SupplierControllerTest {

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private SupplierController supplierController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetList() {
        // Mocking the service layer to return a predefined list of suppliers
        Supplier supplier = new Supplier("ABC Manufacturing", "http://abcmanufacturing.com", "India", 
                                         NatureOfBusiness.SMALL_SCALE, 
                                         Set.of(ManufacturingProcess.THREE_D_PRINTING));

        when(supplierService.getAll()).thenReturn(Collections.singletonList(supplier));

        // Call the controller method
        // List<String> result = supplierController.getList("india");
        String[] arr = {"supplierId","asc"};
        Page <Supplier> result = supplierController.querySuppliers("India", "SMALL_SCALE", "THREE_D_PRINTING", 10, 10, arr);

        // Assertions to check the expected behavior
        assertNull(result);
    }
}
