package com.example.shop.web;

import com.example.shop.service.ProductService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private final ProductService products = new ProductService();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cat = req.getParameter("category");
        req.setAttribute("categories", products.categories());
        req.setAttribute("selected", cat == null ? "All" : cat);
        req.setAttribute("products", products.byCategory(cat));
        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}
