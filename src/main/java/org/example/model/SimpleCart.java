package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleCart implements Cart {
    private final List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getProducts() {
        return products;
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