package com.example.catalogelementmanagement.service;

import com.example.catalogelementmanagement.model.CatalogElement;
import com.example.catalogelementmanagement.model.LifecycleStatus;
import com.example.catalogelementmanagement.repository.CatalogElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CatalogElementService {

    @Autowired
    private CatalogElementRepository repository;

    public ResponseEntity<String> validateAndUpdateStatus(Long id, LifecycleStatus newStatus) {
        CatalogElement catalogElement = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CatalogElement not found with id: " + id));

        ResponseEntity<String> response = validateTransition(catalogElement.getStatus(), newStatus);

        // Update the status if the transition is valid
        if (response.getStatusCode() == HttpStatus.OK) {
            catalogElement.setStatus(newStatus);
            repository.save(catalogElement);
        }

        return response;
    }

    private ResponseEntity<String> validateTransition(LifecycleStatus currentStatus, LifecycleStatus newStatus) {
        if (!isValidTransition(currentStatus, newStatus)) {
            String errorMessage = "Invalid status transition from " + currentStatus + " to " + newStatus;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        // If the transition is valid, return a success response
        return ResponseEntity.ok("Status transition is valid.");
    }

    private boolean isValidTransition(LifecycleStatus currentStatus, LifecycleStatus newStatus) {

        switch (currentStatus) {
            case IN_STUDY:
                return newStatus == LifecycleStatus.IN_DESIGN;
            case IN_DESIGN:
                return newStatus == LifecycleStatus.IN_TEST;
            case IN_TEST:
                return newStatus == LifecycleStatus.ACTIVE || newStatus == LifecycleStatus.REJECTED;
            case ACTIVE:
                return newStatus == LifecycleStatus.LAUNCHED;
            case LAUNCHED:
                return newStatus == LifecycleStatus.RETIRED;
            case RETIRED:
                return newStatus == LifecycleStatus.OBSOLETE;
            default:
                return false;
        }
    }

    public CatalogElement getCatalogElementById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CatalogElement save(CatalogElement catalogElement) {
        return repository.save(catalogElement);
    }
}
