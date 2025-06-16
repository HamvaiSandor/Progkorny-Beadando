package org.example.service;

import org.example.model.OrderEntity;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntity saveOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    @Override
    public OrderEntity findOrderById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }
}
