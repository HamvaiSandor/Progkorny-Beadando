package org.example.repository;

import org.example.model.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class StubOrderRepository implements OrderRepository {
    private static final Logger log = LoggerFactory.getLogger(StubOrderRepository.class);

    @Override
    public void saveOrder(Cart cart) {
        log.info("Order saved: {}", cart);
    }
}