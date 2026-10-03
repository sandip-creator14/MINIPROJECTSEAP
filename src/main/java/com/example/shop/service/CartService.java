package com.example.shop.service;

import com.example.shop.model.*;
import java.util.*;

/** One instance per HTTP session. */
public class CartService {
    private final Map<Integer, CartItem> items = new LinkedHashMap<>();

    public void add(Product p) {
        items.merge(p.getId(), new CartItem(p, 1), (a, b) -> { a.setQuantity(a.getQuantity() + 1); return a; });
    }
    public void remove(int id) { items.remove(id); }
    public void change(int id, int delta) {
        CartItem c = items.get(id);
        if (c == null) return;
        int q = c.getQuantity() + delta;
        if (q <= 0) items.remove(id); else c.setQuantity(q);
    }
    public double total() { return items.values().stream().mapToDouble(CartItem::getSubtotal).sum(); }
    public int count() { return items.values().stream().mapToInt(CartItem::getQuantity).sum(); }
    public List<CartItem> items() { return new ArrayList<>(items.values()); }
    public boolean isEmpty() { return items.isEmpty(); }
    public void clear() { items.clear(); }
}
