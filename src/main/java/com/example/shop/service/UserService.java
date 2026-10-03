package com.example.shop.service;

import com.example.shop.model.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/** In-memory store (demo only). Replace with JDBC/MySQL for persistence. */
public class UserService {
    private static final Map<String, User> USERS = new ConcurrentHashMap<>();
    private static final Map<String, List<Order>> ORDERS = new ConcurrentHashMap<>();
    private static final AtomicInteger ORDER_SEQ = new AtomicInteger(1000);

    static {
        USERS.put("demo@gmail.com", new User("Demo User", "demo@gmail.com", "9876543210", "Thane, Maharashtra", "demo123"));
    }

    public boolean register(String name, String email, String phone, String address, String pwd) {
        if (email == null || email.isBlank() || pwd == null || pwd.length() < 4 || name == null || name.isBlank()) return false;
        return USERS.putIfAbsent(email.toLowerCase(), new User(name, email.toLowerCase(), phone, address, pwd)) == null;
    }
    public User login(String email, String pwd) {
        User u = email == null ? null : USERS.get(email.toLowerCase());
        return (u != null && u.checkPassword(pwd)) ? u : null;
    }
    public Order placeOrder(User u, CartService cart) {
        if (cart.isEmpty()) return null;
        Order o = new Order(ORDER_SEQ.incrementAndGet(), cart.items(), cart.total());
        ORDERS.computeIfAbsent(u.getEmail(), k -> new CopyOnWriteArrayList<>()).add(0, o);
        cart.clear();
        return o;
    }
    public List<Order> ordersFor(User u) { return ORDERS.getOrDefault(u.getEmail(), List.of()); }
}
