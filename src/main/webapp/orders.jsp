<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,com.example.shop.model.*" %>
<%@ include file="header.jsp" %>
<% List<Order> orders = (List<Order>) request.getAttribute("orders"); %>
<h2>My Orders</h2>
<% if (orders.isEmpty()) { %><p>No orders yet. <a href="<%= ctx %>/products">Start shopping</a></p><% } %>
<% for (Order o : orders) { %>
<div class="card order">
  <h3>Order #<%= o.getId() %> <small><%= o.getPlacedAt() %></small></h3>
  <ul><% for (CartItem ci : o.getItems()) { %>
    <li><%= ci.getProduct().getName() %> × <%= ci.getQuantity() %> — ₹<%= String.format("%,.2f", ci.getSubtotal()) %></li>
  <% } %></ul>
  <p class="price">Total: ₹<%= String.format("%,.2f", o.getTotal()) %></p>
</div>
<% } %>
<%@ include file="footer.jsp" %>
