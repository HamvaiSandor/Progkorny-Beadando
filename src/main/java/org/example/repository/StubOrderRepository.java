package org.example.repository;

import org.example.model.Cart;

public class StubOrderRepository implements OrderRepository {
    @Override
    public void saveOrder(Cart cart) {
        System.out.println("Order saved: " + cart);
    }
}