<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="accessDenied" class="container">
    <h3>Доступ запрещен. Ваша роль - ${role}.</h3>
    <h3>Доступно только для GRAND_ADMIN.</h3>
</div>
