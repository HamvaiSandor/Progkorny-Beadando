package org.example.repository;

import org.example.model.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class StubOrderRepositoryTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private StubOrderRepository repo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // ✔ Biztosítjuk a mock objektumok inicializálását
    }

    @Test
    void testSaveOrderDoesNotThrow() {
        // given
        OrderEntity order = new OrderEntity(UUID.randomUUID(), "Test Customer", 2);
        when(orderRepository.save(order)).thenReturn(order); // ✔ Mockoljuk a visszatérési értéket

        // when & then
        assertDoesNotThrow(() -> orderRepository.save(order)); // ✔ Ellenőrizzük, hogy nem dob kivételt
    }
}