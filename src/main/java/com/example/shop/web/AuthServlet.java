package com.example.shop.web;

import com.example.shop.model.User;
import com.example.shop.service.UserService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet({"/login", "/register", "/logout", "/profile"})
public class AuthServlet extends HttpServlet {
    static final UserService USERS = new UserService();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ctx = req.getContextPath();
        switch (req.getServletPath()) {
            case "/logout": req.getSession().invalidate(); resp.sendRedirect(ctx + "/login?msg=Logged+out"); return;
            case "/profile":
                if (req.getSession().getAttribute("user") == null) { resp.sendRedirect(ctx + "/login"); return; }
                req.getRequestDispatcher("/profile.jsp").forward(req, resp); return;
            case "/register": req.getRequestDispatcher("/register.jsp").forward(req, resp); return;
            default: req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String ctx = req.getContextPath();
        if (req.getServletPath().equals("/register")) {
            boolean ok = USERS.register(req.getParameter("name"), req.getParameter("email"),
                    req.getParameter("phone"), req.getParameter("address"), req.getParameter("password"));
            if (ok) resp.sendRedirect(ctx + "/login?msg=Registered.+Please+login");
            else { req.setAttribute("error", "Invalid details or email already registered (password min 4 chars)."); 
                   req.getRequestDispatcher("/register.jsp").forward(req, resp); }
            return;
        }
        User u = USERS.login(req.getParameter("email"), req.getParameter("password"));
        if (u == null) { req.setAttribute("error", "Invalid email or password."); req.getRequestDispatcher("/login.jsp").forward(req, resp); return; }
        req.getSession().setAttribute("user", u);
        resp.sendRedirect(ctx + "/profile");
    }
}
