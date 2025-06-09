package org.example.model;

public class SimpleProduct implements Product {
    private final String name;
    private final String sector;
    private final double price;
    private int availableTickets;
    private final String date;

    public SimpleProduct(String date, String name, String sector, int availableTickets, double price) {
        this.date = date;
        this.name = name;
        this.sector = sector;
        this.availableTickets = availableTickets;
        this.price = price;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSector() {
        return sector;
    }

    @Override
    public int getAvailableTickets() {
        return availableTickets;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void purchaseTickets(int quantity) {
        if (quantity > availableTickets) {
            throw new IllegalArgumentException("Nincs elég jegy a " + sector + " szektorban!");
        }
        availableTickets -= quantity;
    }

    @Override
    public String toString() {
        return name + " (" + sector + ", " + availableTickets + " db, " + price + " Ft)";
    }
}