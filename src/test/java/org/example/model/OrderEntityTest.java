package org.example.model;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class OrderEntityTest {

    @Test
    void testOrderEntityInitialization() {
        OrderEntity order = new OrderEntity("Alice", 5);

        assertNotNull(order.getId()); // ✔ Biztosítjuk, hogy ne legyen null
        assertEquals("Alice", order.getCustomerName());
        assertEquals(5, order.getItemCount());
    }

    @Test
    void testConstructorWithoutId() {
        OrderEntity order = new OrderEntity("Bob", 3);

        assertNotNull(order.getId()); // ✔ UUID automatikusan generálódik
        assertEquals("Bob", order.getCustomerName());
        assertEquals(3, order.getItemCount());
    }

    @Test
    void testEqualsAndHashCode() {
        UUID orderId = UUID.randomUUID();
        OrderEntity order1 = new OrderEntity(orderId, "Charlie", 2);
        OrderEntity order2 = new OrderEntity(orderId, "Charlie", 2);

        assertEquals(order1, order2); // ✔ Most már jól működik
        assertEquals(order1.hashCode(), order2.hashCode());

        OrderEntity order3 = new OrderEntity(UUID.randomUUID(), "Charlie", 2);
        assertNotEquals(order1, order3);
    }
}