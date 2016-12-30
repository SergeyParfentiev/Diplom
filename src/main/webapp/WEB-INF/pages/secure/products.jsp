<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="admin_overflow">
        <table class="list">
            <tr>
                <th>Тип продукта</th>
                <th>Название фирмы</th>
                <th>Название страны</th>
                <th>Цена</th>
                <th>Рисунок</th>
            </tr>
            <c:forEach items="${productList}" var="product" varStatus="status">
                <tr>
                    <td>${product.productType.name}</td>
                    <td>${product.productFirm.name}</td>
                    <td>${product.productFirm.country}</td>
                    <td>${product.cost}</td>
                    <td class="small_img">
                        <img class="img" src="data:image/png;base64,${product.image}" ></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <form id="productForm" name="productForm" enctype="multipart/form-data" method="post" class="form-horizontal">
        <label>Добавить продукт:</label><br/>
        <label for="productTypeId">Тип продукта: <select id="productTypeId" name="productTypeId">
            <option value="">Выберите тип продукта</option>
            <c:forEach items="${productTypeList}" var="productType" varStatus="status">
                <option value="${productType.id}">${productType.name}</option>
            </c:forEach>
        </select>
        </label>
        <label for="productFirmId">Название фирмы: <select id="productFirmId" name="productFirmId">
            <option value="">Выберите фирму</option>
            <c:forEach items="${productFirmList}" var="productFirm" varStatus="status">
                <option value="${productFirm.id}">${productFirm.name}(${productFirm.country})</option>
            </c:forEach>
        </select>
        </label>
        <label for="productCost">Цена: <input id="productCost" size="3" onkeypress="return runScript(event)"
                                              type="text" name="productCost"/></label>
        <label>Рисунок:</label>
        <label for="productPhoto"><input id="productPhoto" type="file" name="productPhoto"></label>
        <a id="send" class="btn btn-success"
           onclick="sendDataWithFile('/addProduct', $('#productForm'), '/showProducts')">Добавить
        </a>
    </form>
</div>