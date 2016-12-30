<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>

<c:forEach items="${productList}" var="product" varStatus="status">
    <div class="product">
        <div class="product_name">${product.productType.name}</div>
        <div class="firm_name">${product.productFirm.name}(${product.productFirm.country})
        </div>
        <div class="div_img">
            <img class="img" src="data:image/png;base64,${product.image}"></div>
        <div class="product_price">Цена:
            <div class="price" style="display:inline-block">${product.cost}</div>
            грн.
        </div>
        <button data-id="${product.id}" class="btn btn-success center-block">В корзину</button>
    </div>
</c:forEach>
<label for="totalPages">
    <input type="text" id="totalPages" name="totalPages" value="${totalPages}">
</label>
<iframe class="iframe" onload="loadPagination(); fillBasket(); window.scrollTo(0, 300)"></iframe>


