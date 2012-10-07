<%--
  Created by IntelliJ IDEA.
  User: colin
  Date: 10/7/12
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Euler number</title>
  </head>
  <body>
    <form action="get-euler" method="post">
        <div style="color: red;">
            <%--@elvariable id="error" type="java.lang.String"--%>
            <c:out value="${error}" />
        </div>

        <div>
            Decimals: <input name="decimals">
        </div>

        <div>
            <c:if test="${not empty number}">
                Euler number:  <c:out value="${number}" />
            </c:if>
        </div>

    </form>
  </body>
</html>