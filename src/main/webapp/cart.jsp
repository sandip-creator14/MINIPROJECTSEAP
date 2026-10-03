<%@ page contentType="text/html;charset=UTF-8" import="com.example.shop.model.CartItem" %>
<%@ include file="header.jsp" %>
<% com.example.shop.service.CartService cs = (com.example.shop.service.CartService) request.getAttribute("cartService"); %>
<h2>Your Cart</h2>
<% if (cs.isEmpty()) { %>
  <p>Your cart is empty. <a href="<%= ctx %>/products">Browse products</a></p>
<% } else { %>
<table>
  <tr><th>Product</th><th>Price</th><th>Quantity</th><th>Subtotal</th><th></th></tr>
  <% for (CartItem ci : cs.items()) { int id = ci.getProduct().getId(); %>
  <tr>
    <td><%= ci.getProduct().getEmoji() %> <%= ci.getProduct().getName() %></td>
    <td>₹<%= String.format("%,.2f", ci.getProduct().getPrice()) %></td>
    <td>
      <form class="inline" method="post" action="<%= ctx %>/cart"><input type="hidden" name="id" value="<%= id %>"><input type="hidden" name="action" value="dec"><button class="qty">−</button></form>
      <%= ci.getQuantity() %>
      <form class="inline" method="post" action="<%= ctx %>/cart"><input type="hidden" name="id" value="<%= id %>"><input type="hidden" name="action" value="inc"><button class="qty">+</button></form>
    </td>
    <td>₹<%= String.format("%,.2f", ci.getSubtotal()) %></td>
    <td><form class="inline" method="post" action="<%= ctx %>/cart"><input type="hidden" name="id" value="<%= id %>"><input type="hidden" name="action" value="remove"><button class="btn danger">Remove</button></form></td>
  </tr>
  <% } %>
  <tr class="total"><td colspan="3">Total</td><td colspan="2">₹<%= String.format("%,.2f", cs.total()) %></td></tr>
</table>
<form method="post" action="<%= ctx %>/checkout"><button class="btn" id="checkout" type="submit">Checkout</button></form>
<% } %>
<%@ include file="footer.jsp" %>
