<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<div class="card form">
  <h2>My Profile</h2>
  <p><b>Name:</b> <span id="p-name"><%= sessionUser.getName() %></span></p>
  <p><b>Email:</b> <span id="p-email"><%= sessionUser.getEmail() %></span></p>
  <p><b>Phone:</b> <span id="p-phone"><%= sessionUser.getPhone() %></span></p>
  <p><b>Address:</b> <span id="p-address"><%= sessionUser.getAddress() %></span></p>
  <a class="btn" href="<%= ctx %>/orders">My Orders</a>
  <a class="btn" href="<%= ctx %>/products">Continue Shopping</a>
  <a class="btn danger" href="<%= ctx %>/logout">Logout</a>
</div>
<%@ include file="footer.jsp" %>
