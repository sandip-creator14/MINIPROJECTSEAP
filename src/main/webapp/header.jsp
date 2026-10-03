<%@ page import="com.example.shop.model.User, com.example.shop.service.CartService" %>
<%
    String ctx = request.getContextPath();
    User sessionUser = (User) session.getAttribute("user");
    CartService navCart = (CartService) session.getAttribute("cart");
    int navCount = navCart == null ? 0 : navCart.count();
%>
<!DOCTYPE html>
<html lang="en"><head>
<meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>ShopEasy</title>
<link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head><body>
<nav class="nav">
  <a class="brand" href="<%= ctx %>/">🛒 ShopEasy</a>
  <div class="links">
    <a id="nav-home" href="<%= ctx %>/">Home</a>
    <a id="nav-products" href="<%= ctx %>/products">Products</a>
    <a id="nav-cart" href="<%= ctx %>/cart">Cart (<%= navCount %>)</a>
    <% if (sessionUser == null) { %>
      <a id="nav-login" href="<%= ctx %>/login">Login</a>
      <a id="nav-register" href="<%= ctx %>/register">Register</a>
    <% } else { %>
      <a id="nav-orders" href="<%= ctx %>/orders">Orders</a>
      <a id="nav-profile" href="<%= ctx %>/profile">Hi, <%= sessionUser.getName() %></a>
      <a id="nav-logout" href="<%= ctx %>/logout">Logout</a>
    <% } %>
  </div>
</nav>
<main class="container">
