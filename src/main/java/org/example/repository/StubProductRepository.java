package org.example.repository;

import org.example.model.Product;
import org.example.model.SimpleProduct;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class StubProductRepository implements ProductRepository {

    private final List<Product> products;

    public StubProductRepository() {
        products = new ArrayList<>();
        // Long típusú id-k, nem stringek
        products.add(new SimpleProduct(1L, "2025.06.15", "Rúzsa Magdi koncert", "A", 200, 15000.0));
        products.add(new SimpleProduct(2L, "2025.06.15", "Rúzsa Magdi koncert", "B", 200, 12000.0));
        products.add(new SimpleProduct(3L, "2025.06.15", "Rúzsa Magdi koncert", "C", 200, 10000.0));
        products.add(new SimpleProduct(4L, "2025.07.10", "Fradi - Szpari focimeccs", "A", 200, 8000.0));
    }

    @Override
    public List<Product> findAllProducts() {
        return products;
    }

    @Override
    public Product saveProduct(Product product) {
        long productId = generateNextId();
        SimpleProduct productToSave = new SimpleProduct(productId, product.date(), product.name(), product.sector(), product.AvailableTickets(), product.price());
        products.add(productToSave);
        return productToSave;
    }

    @Override
    public Product findProduct(Long id) {
        return products.stream()
                .filter(product -> product.id().equals(id))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = findProduct(id);
        SimpleProduct updatedSimpleProduct = new SimpleProduct(productDetails.id(), productDetails.date(), productDetails.name(), productDetails.sector(), product.AvailableTickets(), productDetails.price());
                product.id();
                product.date();
                product.name();
                product.sector();
                product.AvailableTickets();
                product.price();
                deleteProduct(id);
                products.add(updatedSimpleProduct);
                return updatedSimpleProduct;
    }
    @Override
    public void deleteProduct(Long id) {
        products.removeIf(product -> product.id().equals(id));
    }

    private long generateNextId() {
        return products.stream().mapToLong(Product::id).max().orElse(0L) + 1;
    }

}