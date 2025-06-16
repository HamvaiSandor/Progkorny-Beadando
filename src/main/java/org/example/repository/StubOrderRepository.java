package org.example.repository;

import org.example.model.OrderEntity;
import org.example.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StubOrderRepository implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(StubOrderRepository.class);
    private final OrderRepository orderRepository;

    public StubOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntity saveOrder(OrderEntity order) {
        OrderEntity savedOrder = orderRepository.save(order);
        log.info("Order saved in database: {}", savedOrder);
        return savedOrder;
    }

    @Override
    public OrderEntity findOrderById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }
}