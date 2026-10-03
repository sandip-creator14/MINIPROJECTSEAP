package com.example.shop;

import static org.junit.Assert.*;
import com.example.shop.model.Product;
import com.example.shop.service.*;
import org.junit.*;

public class CartServiceTest {
    private CartService cart; private Product phone, pen;

    @Before public void setUp() {
        cart = new CartService();
        ProductService ps = new ProductService();
        phone = ps.find(1); pen = ps.find(8);
    }
    @Test public void addAndTotal() {
        cart.add(phone); cart.add(pen); cart.add(pen);
        assertEquals(3, cart.count());
        assertEquals(15999 + 2 * 99, cart.total(), 0.001);
    }
    @Test public void increaseDecreaseQuantity() {
        cart.add(pen); cart.change(8, 1);
        assertEquals(2, cart.items().get(0).getQuantity());
        cart.change(8, -1); cart.change(8, -1);
        assertTrue(cart.isEmpty());
    }
    @Test public void removeItem() {
        cart.add(phone); cart.remove(1);
        assertTrue(cart.isEmpty());
        assertEquals(0.0, cart.total(), 0.001);
    }
    @Test public void userRegisterAndLogin() {
        UserService us = new UserService();
        assertNotNull(us.login("demo@gmail.com", "demo123"));
        assertNull(us.login("demo@gmail.com", "wrong"));
        assertTrue(us.register("Test", "t1@x.com", "9999999999", "Addr", "pass1"));
        assertFalse(us.register("Test", "t1@x.com", "9999999999", "Addr", "pass1"));
    }
    @Test public void checkoutCreatesOrderAndClearsCart() {
        UserService us = new UserService();
        cart.add(phone);
        assertNotNull(us.placeOrder(us.login("demo@gmail.com", "demo123"), cart));
        assertTrue(cart.isEmpty());
    }
    @Test public void categoryFilter() {
        assertEquals(3, new ProductService().byCategory("Electronics").size());
    }
}
