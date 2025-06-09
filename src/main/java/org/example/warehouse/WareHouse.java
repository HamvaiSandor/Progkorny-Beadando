package org.example.warehouse;

import org.example.model.Cart;
import org.example.model.Product;
import org.example.orderconfirm.Observer;

import java.util.List;

public class WareHouse implements Observer {
    @Override
    public void notify(Cart cart) {
        registerOrderedProducts(cart.getProducts());
    }

    public void registerOrderedProducts(List<Product> products) {
        System.out.println("Products registered in warehouse: " + products);
    }
}
