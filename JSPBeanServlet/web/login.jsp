<%--
  Catalin Dumitru
  Universitatea Alexandru Ioan Cuza
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>

<div class="framed">
    <form action="login" method="post">
        <div>Username: <input name="userName"/></div>
        <div>Password: <input name="password" type="password"/></div>
        <div>Remember me: <input name="rememberMe" type="checkbox"></div>
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