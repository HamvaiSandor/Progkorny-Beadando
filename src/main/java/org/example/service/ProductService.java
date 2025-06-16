package org.example.service;

import org.example.model.SimpleProduct;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    SimpleProduct save(SimpleProduct product);
    Optional<SimpleProduct> findById(Long id);
    void deleteById(Long id);
    List<SimpleProduct> findAll();  // <-- EZ HIÁNYZOTT
}