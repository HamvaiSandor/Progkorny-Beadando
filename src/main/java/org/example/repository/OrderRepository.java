package org.example.repository;

import org.example.model.Cart;

public interface OrderRepository {
    void saveOrder(Cart cart);
}
