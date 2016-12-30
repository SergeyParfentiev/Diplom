<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="admin_overflow">
        <table class="list">
            <tr>
                <th>Услуга</th>
                <th>Имя</th>
                <th>Телефон</th>
                <th>Примечание</th>
            </tr>
            <c:forEach items="${callbackList}" var="callback" varStatus="status">
                <tr>
                    <td>${callback.service.name}</td>
                    <td>${callback.name}</td>
                    <td>${callback.phone}</td>
                    <td>${callback.note}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>