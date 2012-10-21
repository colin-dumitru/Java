<%--
  Catalin Dumitru
  Universitatea Alexandru Ioan Cuza
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<div style="width: 400px; margin-left: auto; margin-right: auto; border: 1px solid black; padding: 5px; border-radius: 5px;">
    <form action="register" method="post">
        <div>Username: <input name="userName"/></div>
        <div>Password: <input name="password" type="password"/></div>
        <div>
            <button type="submit">Register</button>
        </div>
    </form>
    <form action="login">
        <button type="submit">Login</button>
    </form>
</div>

</body>
</html>