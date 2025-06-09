package org.example.cart;

import org.example.exception.NoSuchProductException;
import org.example.model.Product;
import org.example.orderconfirm.Observer;

import java.util.List;

public interface ShoppingCartService {
    void order();
    double getTotalPrice();
    void addProduct(String productName, int quantity) throws NoSuchProductException;
    List<Product> getProductsFromCart();
    void removeProduct(Product productToRemove);
    void subscribe(Observer observer);
}
