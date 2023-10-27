package com.example.catalogelementmanagement.model;

import jakarta.persistence.*;

@Entity
public class CatalogElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LifecycleStatus status;

    public CatalogElement() {
    }

    public CatalogElement(LifecycleStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LifecycleStatus getStatus() {
        return status;
    }

    public void setStatus(LifecycleStatus status) {
        this.status = status;
    }
}
