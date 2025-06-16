package org.example.model;

import java.util.List;
import java.util.UUID;

public interface Cart {
    List<Product> getProducts();
    void addProduct(Product product);
    void removeProduct(Product product);
    void clearCart();

    String getCustomerName();

    UUID getId();
}