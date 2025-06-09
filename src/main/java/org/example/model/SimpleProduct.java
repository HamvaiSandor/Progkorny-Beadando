package org.example.model;

import lombok.Builder;

@Builder
public record SimpleProduct(Long id, String date, String name, String sector, int AvailableTickets, Double price) implements Product {
}
