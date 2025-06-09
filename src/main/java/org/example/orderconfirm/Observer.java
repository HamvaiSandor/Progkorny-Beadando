package org.example.orderconfirm;

import org.example.model.Cart;

public interface Observer {
    void notify(Cart cart);
}
