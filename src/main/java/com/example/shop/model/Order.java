package com.example.shop.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order {
    private final int id; private final List<CartItem> items; private final double total;
    private final LocalDateTime placedAt = LocalDateTime.now();
    public Order(int id, List<CartItem> items, double total) { this.id = id; this.items = items; this.total = total; }
    public int getId() { return id; }
    public List<CartItem> getItems() { return items; }
    public double getTotal() { return total; }
    public String getPlacedAt() { return placedAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")); }
}
