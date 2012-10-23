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

    <c:if test="${not empty error}">
        <div style="color: red">
            <c:out value="${error}"/>
        </div>
    </c:if>

    <c:if test="${empty error}">
        <div style="color: gray;">
            No errors
        </div>
    </c:if>
</div>

</body>
</html>