package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCartTest {

    private SimpleCart cart;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        cart = new SimpleCart("Test Customer");
        product1 = new SimpleProduct(1L, "2025-06-16", "Koncert", "A", 100, 4990.0);
        product2 = new SimpleProduct(2L, "2025-06-17", "Mozi", "B", 50, 2990.0);
    }

    @Test
    void testAddProduct() {
        cart.addProduct(product1);
        assertEquals(1, cart.getProducts().size());
        assertTrue(cart.getProducts().contains(product1));
    }

    @Test
    void testRemoveProduct() {
        cart.addProduct(product1);
        cart.removeProduct(product1);
        assertTrue(cart.getProducts().isEmpty());
    }

    @Test
    void testClearCart() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.clearCart();
        assertTrue(cart.getProducts().isEmpty());
    }

    @Test
    void testGetProductsReturnsReference() {
        List<Product> products = cart.getProducts();
        assertNotNull(products);
    }

    @Test
    void testToStringFormat() {
        cart.addProduct(product1);
        String str = cart.toString();
        assertTrue(str.contains("SimpleCart{"));
        assertTrue(str.contains("Koncert"));
    }
}