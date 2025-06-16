package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Getter
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String customerName;
    private int itemCount;

    @Transient
    private Collection<Product> products = new ArrayList<>();

    public OrderEntity() {
    }

    public OrderEntity(String customerName, Collection<Product> products) {
        this.id = UUID.randomUUID();
        this.customerName = customerName;
        this.products = products;
        this.itemCount = products != null ? products.size() : 0;
    }

    // ✔ Ez hiányzott: most már megy a ShoppingCartServiceImpl-ben
    public OrderEntity(UUID id, String customerName, int itemCount) {
        this.id = id;
        this.customerName = customerName;
        this.itemCount = itemCount;
        this.products = new ArrayList<>();
    }

    public OrderEntity(String customerName, int itemCount) {
        this.id = UUID.randomUUID();
        this.customerName = customerName;
        this.itemCount = itemCount;
        this.products = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OrderEntity)) {
            return false;
        }
        OrderEntity other = (OrderEntity) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
