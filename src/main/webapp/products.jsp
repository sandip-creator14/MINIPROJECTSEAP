<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,com.example.shop.model.Product" %>
<%@ include file="header.jsp" %>
<%
  List<String> categories = (List<String>) request.getAttribute("categories");
  List<Product> products = (List<Product>) request.getAttribute("products");
  String selected = (String) request.getAttribute("selected");
%>
<h2 class="page-title">Products</h2>
<% if (request.getParameter("added") != null) { %><div class="alert ok">Item added to cart.</div><% } %>
<div class="chips">
  <a class="chip <%= "All".equals(selected) ? "active" : "" %>" href="<%= ctx %>/products">All</a>
  <% for (String c : categories) { %>
  <a class="chip <%= c.equals(selected) ? "active" : "" %>" href="<%= ctx %>/products?category=<%= c %>"><%= c %></a>
  <% } %>
</div>
<div class="grid">
  <% for (Product p : products) { %>
  <div class="card product">
    <div class="emoji"><%= p.getEmoji() %></div>
    <h3><%= p.getName() %></h3>
    <small><%= p.getCategory() %></small>
    <p class="price">₹<%= String.format("%,.2f", p.getPrice()) %></p>
    <form method="post" action="<%= ctx %>/cart">
      <input type="hidden" name="action" value="add"><input type="hidden" name="id" value="<%= p.getId() %>">
      <button class="btn" type="submit">Add to Cart</button>
    </form>
  </div>
  <% } %>
</div>
<%@ include file="footer.jsp" %>