package org.example.repository;

import org.example.model.Product;
import org.example.model.SimpleProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StubProductRepository implements ProductRepository {
    private static final List<Product> PRODUCTS = List.of(
            new SimpleProduct("2025.06.15", "Rúzsa Magdi koncert", "A", 200, 15000.0),
            new SimpleProduct("2025.06.15", "Rúzsa Magdi koncert", "B", 200, 12000.0),
            new SimpleProduct("2025.06.15", "Rúzsa Magdi koncert", "C", 200, 10000.0),
            new SimpleProduct("2025.07.10", "Fradi - Szpari focimeccs", "A", 200, 8000.0)
    );

    @Override
    public List<Product> getAllProducts() {
        return PRODUCTS;
    }
}