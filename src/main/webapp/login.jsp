<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<div class="card form">
  <h2>Login</h2>
  <% if (request.getAttribute("error") != null) { %><div class="alert err"><%= request.getAttribute("error") %></div><% } %>
  <% if (request.getParameter("msg") != null) { %><div class="alert ok"><%= request.getParameter("msg") %></div><% } %>
  <form method="post" action="<%= ctx %>/login">
    <label>Email</label><input id="email" name="email" type="email" required>
    <label>Password</label><input id="password" name="password" type="password" required>
    <button class="btn" id="loginBtn" type="submit">Login</button>
  </form>
  <p>New here? <a href="<%= ctx %>/register">Create an account</a></p>
  <small>Demo: demo@gmail.com / demo123</small>
</div>
<%@ include file="footer.jsp" %>
