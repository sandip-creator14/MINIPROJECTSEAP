package com.example.shop.web;

import com.example.shop.model.Product;
import com.example.shop.service.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private final ProductService products = new ProductService();

    public static CartService cartOf(HttpSession s) {
        CartService c = (CartService) s.getAttribute("cart");
        if (c == null) { c = new CartService(); s.setAttribute("cart", c); }
        return c;
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cartService", cartOf(req.getSession()));
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CartService cart = cartOf(req.getSession());
        String action = req.getParameter("action");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            if ("add".equals(action)) { Product p = products.find(id); if (p != null) cart.add(p); }
            else if ("remove".equals(action)) cart.remove(id);
            else if ("inc".equals(action)) cart.change(id, 1);
            else if ("dec".equals(action)) cart.change(id, -1);
        } catch (NumberFormatException ignored) { }
        String back = "add".equals(action) ? "/products?added=1" : "/cart";
        resp.sendRedirect(req.getContextPath() + back);
    }
}
