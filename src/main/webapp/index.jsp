<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<section class="hero">
  <h1>Everything you need, delivered.</h1>
  <p>Electronics, fashion, stationery and home essentials at great prices.</p>
  <a class="btn" href="<%= ctx %>/products">Shop Now</a>
</section>
<h2>Shop by category</h2>
<div class="grid">
  <% for (String c : new String[]{"Electronics","Fashion","Stationery","Home"}) { %>
  <a class="card cat" href="<%= ctx %>/products?category=<%= c %>"><h3><%= c %></h3></a>
  <% } %>
</div>
<%@ include file="footer.jsp" %>