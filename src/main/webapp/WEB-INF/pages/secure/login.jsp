<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Premium water</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/style/content.css"/>
</head>
<body>
    <div align="center">
        <c:url value="/j_spring_security_check" var="loginUrl"/>

        <form action="${loginUrl}" method="POST">
            <label id="for_j_login" for="j_login"> Login:<br/>
            <input id="j_login" type="text" name="j_login">
        </label><br/>
            <label id="for_j_password" for="j_password">Password:<br/>
            <input id="j_password" type="password" name="j_password">
        </label><br/>
            <input class="btn btn-success" type="submit"/>

            <c:if test="${param.error ne null}">
                <h4>Wrong login or password!</h4>
            </c:if>

            <c:if test="${param.logout ne null}">
                <h4>Successfully logout!</h4>
            </c:if>
        </form>
    </div>
</body>
</html>
