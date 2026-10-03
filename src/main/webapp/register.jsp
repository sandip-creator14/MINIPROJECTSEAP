<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<div class="card form">
  <h2>Register</h2>
  <% if (request.getAttribute("error") != null) { %><div class="alert err"><%= request.getAttribute("error") %></div><% } %>
  <form method="post" action="<%= ctx %>/register">
    <label>Full name</label><input name="name" required>
    <label>Email</label><input name="email" type="email" required>
    <label>Phone</label><input name="phone" pattern="[0-9]{10}" title="10 digit phone number" required>
    <label>Address</label><textarea name="address" rows="3" required></textarea>
    <label>Password</label><input name="password" type="password" minlength="4" required>
    <button class="btn" type="submit">Create account</button>
  </form>
  <p>Already registered? <a href="<%= ctx %>/login">Login</a></p>
</div>
<%@ include file="footer.jsp" %>
