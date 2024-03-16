<%@ page import="model.ClientUser" %>

<form action="UserController" method="post">
    <input type="hidden" name="action" value="register">

    <label for="username">Username:</label>
    <input type="text" name="username" required>

    <label for="name">Name:</label>
    <input type="text" name="name" required>

    <label for="lastname">Last Name:</label>
    <input type="text" name="lastname" required>

    <label for="number">Number:</label>
    <input type="number" name="number" required>

    <label for="mail">Mail:</label>
    <input type="email" name="mail" required>

    <label for="password">Password:</label>
    <input type="password" name="password" required>

    <button type="submit">Register</button>
</form>
<button onclick="location.href='Login.jsp'">Login</button>