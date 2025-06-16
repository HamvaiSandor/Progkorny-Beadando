package org.example.model;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SimpleCart implements Cart {
    private final String customerName;
    private final List<Product> products = new ArrayList<>();

    public SimpleCart(String customerName) { // ✔ Ügyfél név átadása
        this.customerName = customerName != null ? customerName : "Unknown Customer"; // Alapértelmezett érték
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(products); // Védelem a mutabilitás ellen
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        products.remove(product);
    }

    @Override
    public void clearCart() {
        products.clear();
    }

    @Override
    public String toString() {
        return "SimpleCart{" + products.stream().map(Object::toString).collect(Collectors.joining(", ")) + '}';
    }
}