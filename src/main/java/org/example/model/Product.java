package org.example.model;

public interface Product {
    String getDate();
    String getName();
    String getSector();
    int getAvailableTickets();
    double getPrice();
    void purchaseTickets(int quantity);
}