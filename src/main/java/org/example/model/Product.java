package org.example.model;

public interface Product {
    Long getId();
    String getDate();
    String getName();
    String getSector();
    int getAvailableTickets();
    Double getPrice();
}