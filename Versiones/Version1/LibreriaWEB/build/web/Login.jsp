<%@ page import="model.AdminUser" %>
<%@ page import="model.SalesAgent" %>
<%@ page import="model.ClientUser" %>

<form action="UserController" method="post">
    <input type="hidden" name="action" value="login">

    <label for="username">Username:</label>
    <input type="text" name="username" required>

    <label for="password">Password:</label>
    <input type="password" name="password" required>

    <label for="securitycode">Security Code:</label>
    <select name="securitycode">
        <option value="1">Admin</option>
        <option value="2">Sales Agent</option>
        <option value="3" selected>Client</option>
    </select>

    <button type="submit">Login</button>
</form>

<button onclick="location.href='index.jsp'">Register</button>