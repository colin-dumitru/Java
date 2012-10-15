<%--
  Catalin Dumitru
  Universitatea Alexandru Ioan Cuza
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<div style="width: 400px; margin-left: auto; margin-right: auto; border: 1px solid black; padding: 5px; border-radius: 5px;">
    <form action="/login" method="post">
        <div>Username: <input name="userName"/></div>
        <div>Password: <input name="password"/></div>
        <div>
            <button type="submit">Login</button>
        </div>
    </form>
    <form action="register">
        <button type="submit">Register</button>
    </form>
</div>

</body>
</html>