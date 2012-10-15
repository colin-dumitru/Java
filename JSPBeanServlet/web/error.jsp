<%--
  Catalin Dumitru
  Universitatea Alexandru Ioan Cuza
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<div style="width: 400px; margin-left: auto; margin-right: auto; border: 1px solid black; padding: 5px; border-radius: 5px;">

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