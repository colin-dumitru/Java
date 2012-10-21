<%--
  Catalin Dumitru
  Universitatea Alexandru Ioan Cuza
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<jsp:useBean id="computeModel" class="edu.jspbeanservlet.model.ComputeModel" scope="session"/>
<jsp:setProperty name="computeModel" property="*"/>

Result:
<jsp:getProperty name="computeModel" property="result"/>

</body>
</html>