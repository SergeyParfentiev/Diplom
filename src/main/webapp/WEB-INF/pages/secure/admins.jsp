<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="admin_overflow">
        <table class="list">
            <tr>
                <th>Роль</th>
                <th>Имя</th>
                <th>Логин</th>
                <th>Пароль</th>
            </tr>
            <c:forEach items="${customUserList}" var="customUser" varStatus="status">
                <tr>
                    <td>${customUser.role}</td>
                    <td>${customUser.name}</td>
                    <td>${customUser.login}</td>
                    <td>${customUser.password}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <form id="adminForm" name="adminForm" method="post" class="form-horizontal">
        <label>Добавить админа:</label><br/>
        <label for="adminRole">Роль: <select id="adminRole" name="adminRole">
            <option value="">Выберите роль</option>
            <c:forEach items="${roleList}" var="role" varStatus="status">
                <option value="${role.id}">${role.name}</option>
            </c:forEach>
        </select>
        </label>
        <label for="adminName">Имя: <input id="adminName" onkeypress="return runScript(event)" type="text" name="adminName"/></label>
        <label for="adminLogin">Логин: <input id="adminLogin" onkeypress="return runScript(event)" type="text" name="adminLogin"/></label>
        <label for="adminPassword">Пароль: <input id="adminPassword" onkeypress="return runScript(event)" type="text" name="adminPassword"/></label>
        <a id="send" class="btn btn-success" onclick="sendDataAndShowContent('/addAdmin', $('#adminForm').serialize());">Добавить
        </a>
    </form>
</div>