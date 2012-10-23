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
    <form action="register" method="post">
        <div>Username: <input name="userName"/></div>
        <div>Password: <input name="password" type="password"/></div>
        <div>
            <img src="captcha.png" alt="Captcha"/>
        </div>
        <div>
            <input name="enteredCaptcha">
        </div>
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