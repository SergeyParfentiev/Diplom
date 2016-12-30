<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="admin_overflow">
        <table class="list">
            <tr>
                <th>Название фирмы</th>
                <th>Название страны</th>
            </tr>
            <c:forEach items="${productFirmList}" var="productFirm" varStatus="status">
                <tr>
                    <td>${productFirm.name}</td>
                    <td>${productFirm.country}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <form id="productFirmForm" name="productFirmForm" method="post" class="form-horizontal">
        <label>Добавить фирму:</label><br/><label for="productFirmName">Название: <input id="productFirmName"
            onkeypress="return runScript(event)" type="text" name="productFirmName"/></label>
        <label for="productFirmCountry">Страна: <input id="productFirmCountry" onkeypress="return runScript(event)"
                                                       type="text" name="productFirmCountry"/></label>
        <a id="send" class="btn btn-success"
           onclick="sendDataAndShowContent('/addProductFirm', $('#productFirmForm').serialize());">Добавить
        </a>
    </form>
</div>