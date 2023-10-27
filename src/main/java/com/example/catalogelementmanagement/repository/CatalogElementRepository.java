package com.example.catalogelementmanagement.repository;

import com.example.catalogelementmanagement.model.CatalogElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogElementRepository extends JpaRepository<CatalogElement, Long> {
}
