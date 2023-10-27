package com.example.catalogelementmanagement.controller;

import com.example.catalogelementmanagement.model.CatalogElement;
import com.example.catalogelementmanagement.model.LifecycleStatus;
import com.example.catalogelementmanagement.service.CatalogElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog-element")
public class CatalogElementController {

    private final CatalogElementService catalogElementService;

    @Autowired
    public CatalogElementController(CatalogElementService catalogElementService) {
        this.catalogElementService = catalogElementService;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestBody LifecycleStatus newStatus) {
        CatalogElement catalogElement = catalogElementService.getCatalogElementById(id);
        ResponseEntity<String> response = catalogElementService.validateAndUpdateStatus(catalogElement.getId(), newStatus);
        return response;
    }

    @PostMapping
    public CatalogElement createCatalogElement(@RequestBody CatalogElement catalogElement) {
        return catalogElementService.save(catalogElement);
    }
}
