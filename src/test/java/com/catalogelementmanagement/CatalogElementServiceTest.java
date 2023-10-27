package com.example.catalogelementmanagement;

import com.example.catalogelementmanagement.model.CatalogElement;
import com.example.catalogelementmanagement.model.LifecycleStatus;
import com.example.catalogelementmanagement.repository.CatalogElementRepository;
import com.example.catalogelementmanagement.service.CatalogElementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
class CatalogElementServiceTest {

    @InjectMocks
    private CatalogElementService catalogElementService;

    @Mock
    private CatalogElementRepository repository;

    @BeforeEach
    void setUp() {
        CatalogElement element = new CatalogElement(LifecycleStatus.IN_STUDY);
        element.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(element));
    }

    @Test
    void testValidateAndUpdateStatus_ValidTransition_StatusUpdated() {
        ResponseEntity<String> response = catalogElementService.validateAndUpdateStatus(1L, LifecycleStatus.IN_DESIGN);
        assertEquals(OK, response.getStatusCode());
    }

    @Test
    void testValidateAndUpdateStatus_InvalidTransition_StatusNotUpdated() {
        ResponseEntity<String> response = catalogElementService.validateAndUpdateStatus(1L, LifecycleStatus.ACTIVE);
        assertEquals(BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testValidateAndUpdateStatus_InvalidStatus_BadRequest() {
        ResponseEntity<String> response = catalogElementService.validateAndUpdateStatus(1L, null);
        assertEquals(BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetCatalogElementById_ExistingElement() {
        CatalogElement element = catalogElementService.getCatalogElementById(1L);
        assertEquals(LifecycleStatus.IN_STUDY, element.getStatus());
    }

    @Test
    void testGetCatalogElementById_NonExistingElement() {
        CatalogElement element = catalogElementService.getCatalogElementById(2L);
        assertEquals(null, element);
    }
}
