package org.example.service;

import org.example.model.OrderEntity;
import java.util.UUID;

public interface OrderService {
    OrderEntity saveOrder(OrderEntity order);
    OrderEntity findOrderById(UUID id);
}