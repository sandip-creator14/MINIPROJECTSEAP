package com.example.shop.service;

import com.example.shop.model.Product;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private static final List<Product> PRODUCTS = List.of(
        new Product(1, "Smartphone", "Electronics", 15999, "📱"),
        new Product(2, "Laptop", "Electronics", 54999, "💻"),
        new Product(3, "Headphones", "Electronics", 2499, "🎧"),
        new Product(4, "T-Shirt", "Fashion", 599, "👕"),
        new Product(5, "Sneakers", "Fashion", 2999, "👟"),
        new Product(6, "Backpack", "Fashion", 1299, "🎒"),
        new Product(7, "Notebook Set", "Stationery", 249, "📓"),
        new Product(8, "Pen Pack", "Stationery", 99, "🖊️"),
        new Product(9, "Coffee Mug", "Home", 349, "☕"),
        new Product(10, "Desk Lamp", "Home", 899, "💡"));

    public List<Product> all() { return PRODUCTS; }
    public List<Product> byCategory(String c) {
        if (c == null || c.isBlank() || c.equals("All")) return PRODUCTS;
        return PRODUCTS.stream().filter(p -> p.getCategory().equals(c)).collect(Collectors.toList());
    }
    public List<String> categories() {
        return PRODUCTS.stream().map(Product::getCategory).distinct().collect(Collectors.toList());
    }
    public Product find(int id) { return PRODUCTS.stream().filter(p -> p.getId() == id).findFirst().orElse(null); }
}
