package com.example.shop.web;

import com.example.shop.model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet({"/orders", "/checkout"})
public class OrderServlet extends HttpServlet {

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null) { resp.sendRedirect(req.getContextPath() + "/login"); return; }
        req.setAttribute("orders", AuthServlet.USERS.ordersFor(u));
        req.getRequestDispatcher("/orders.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null) { resp.sendRedirect(req.getContextPath() + "/login?msg=Login+to+checkout"); return; }
        AuthServlet.USERS.placeOrder(u, CartServlet.cartOf(req.getSession()));
        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}
