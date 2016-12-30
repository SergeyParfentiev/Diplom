<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="admin_overflow">
        <table class="list">
            <tr>
                <th>Типы продукта</th>
            </tr>
            <c:forEach items="${productTypeList}" var="productType" varStatus="status">
                <tr>
                    <td>${productType.name}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <form id="productTypeForm" name="productTypeForm" method="post" class="form-horizontal">
        <label for="productType">Добавить тип продукта: <input id="productType" onkeypress="return runScript(event)"
                                                               type="text" name="productType"/></label>
        <a id="send" class="btn btn-success"
           onclick="sendDataAndShowContent('/addProductType', $('#productTypeForm').serialize());">Добавить
        </a>
    </form>
</div>