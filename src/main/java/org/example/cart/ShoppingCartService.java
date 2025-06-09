package org.example.cart;

import org.example.exception.NoSuchProductException;
import org.example.model.Product;
import org.example.orderconfirm.Observer;
import org.example.orderconfirm.Observable;
import org.example.model.Product;


import java.util.List;

public abstract interface ShoppingCartService extends Observable {
    void order();
    double getTotalPrice();
    void addProduct(String productName) throws NoSuchProductException;
    List<Product> getProductsFromCart();
    void removeProduct(Product productToRemove);
    void subscribe(Observer observer);
    void listProducts();
}

