package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleProductTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        SimpleProduct product = new SimpleProduct(
                1L,
                "2025-06-16",
                "Koncert",
                "VIP",
                100,
                7990.0
        );

        assertEquals(1L, product.getId());
        assertEquals("2025-06-16", product.getDate());
        assertEquals("Koncert", product.getName());
        assertEquals("VIP", product.getSector());
        assertEquals(100, product.getAvailableTickets());
        assertEquals(7990.0, product.getPrice());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        SimpleProduct product = new SimpleProduct();
        product.setId(2L);
        product.setDate("2025-07-01");
        product.setName("Színház");
        product.setSector("B");
        product.setAvailableTickets(200);
        product.setPrice(5990.0);

        assertEquals(2L, product.getId());
        assertEquals("2025-07-01", product.getDate());
        assertEquals("Színház", product.getName());
        assertEquals("B", product.getSector());
        assertEquals(200, product.getAvailableTickets());
        assertEquals(5990.0, product.getPrice());
    }
}