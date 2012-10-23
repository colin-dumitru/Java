<%--
  Catalin Dumitru
  Universitatea Alexandru Ioan Cuza
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>

<body>

<div class="framed">
    Result: <c:out value="${requestScope.result}"/>
</div>

</body>
</html>