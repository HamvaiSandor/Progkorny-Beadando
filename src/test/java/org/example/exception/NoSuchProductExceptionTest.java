package org.example.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoSuchProductExceptionTest {

    @Test
    void testExceptionMessage() {
        NoSuchProductException exception = new NoSuchProductException();
        assertEquals("A jegy vásárlás nem lehetséges!", exception.getMessage());
    }
}