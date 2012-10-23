<%@ page import="edu.jspbeanservlet.model.ComputeModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<jsp:useBean id="computeModel" class="edu.jspbeanservlet.model.ComputeModel" scope="session"/>
<jsp:setProperty name="computeModel" property="*"/>

<c:if test="${computeModel.defined}">
    <c:redirect url="result"/>
</c:if>

<div class="framed">
    <form action="compute" method="post">
        <div>
            <label>
                First number: <input name="firstNumber"
                                     value="<jsp:getProperty name="computeModel" property="firstNumber" />"/>
            </label>
        </div>
        <div>
            <label>
                Second number: <input name="secondNumber"
                                      value="<jsp:getProperty name="computeModel" property="secondNumber" />"/>
            </label>
        </div>
        <div>
            <label>
                Operation: <select name="operation">
                <option value="<%=ComputeModel.Operation.ADD.name()%>">
                    <%=ComputeModel.Operation.ADD.getDisplayName()%>
                </option>
                <option value="<%=ComputeModel.Operation.SUB.name()%>">
                    <%=ComputeModel.Operation.SUB.getDisplayName()%>
                </option>
                <option value="<%=ComputeModel.Operation.MUL.name()%>">
                    <%=ComputeModel.Operation.MUL.getDisplayName()%>
                </option>
                <option value="<%=ComputeModel.Operation.DIV.name()%>">
                    <%=ComputeModel.Operation.DIV.getDisplayName()%>
                </option>
            </select>
            </label>
        </div>
        <div>
            <button type="submit">Submit</button>
        </div>
    </form>

</div>

</body>
</html>