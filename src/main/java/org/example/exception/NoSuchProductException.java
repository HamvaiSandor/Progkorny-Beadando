package org.example.exception;

public class NoSuchProductException extends Exception {
    public NoSuchProductException() {
        super("A jegy vásárlás nem lehetséges!");
    }
}