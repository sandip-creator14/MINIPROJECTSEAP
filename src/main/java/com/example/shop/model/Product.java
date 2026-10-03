package com.example.shop.model;

public class Product {
    private final int id; private final String name, category, emoji; private final double price;
    public Product(int id, String name, String category, double price, String emoji) {
        this.id = id; this.name = name; this.category = category; this.price = price; this.emoji = emoji;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getEmoji() { return emoji; }
}
