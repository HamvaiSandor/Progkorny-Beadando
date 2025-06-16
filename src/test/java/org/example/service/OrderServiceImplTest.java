package org.example.service;

import org.example.model.OrderEntity;
import org.example.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // JUnit Jupiter támogatás
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository; // Mockolt repository

    @InjectMocks
    private OrderServiceImpl orderService; // Automatikusan injektálja a mockokat

    private OrderEntity testOrder;

    @BeforeEach
    void setUp() {
        testOrder = new OrderEntity("John Doe", 3);
    }

    @Test
    void testSaveOrder() {
        // Mockoljuk a repository mentési műveletet
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(testOrder);

        OrderEntity savedOrder = orderService.saveOrder(testOrder);

        assertNotNull(savedOrder);
        assertEquals("John Doe", savedOrder.getCustomerName());
        verify(orderRepository, times(1)).save(testOrder);
    }

    @Test
    void testFindOrderById_OrderExists() {
        UUID orderId = UUID.randomUUID();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(testOrder));

        OrderEntity foundOrder = orderService.findOrderById(orderId);

        assertNotNull(foundOrder);
        assertEquals("John Doe", foundOrder.getCustomerName());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void testFindOrderById_OrderNotFound() {
        UUID orderId = UUID.randomUUID();
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        OrderEntity foundOrder = orderService.findOrderById(orderId);

        assertNull(foundOrder);
        verify(orderRepository, times(1)).findById(orderId);
    }
}