<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="admin_overflow">
        <table class="list">
            <tr>
                <th>Товары</th>
                <th>Имя</th>
                <th>Ел. почта</th>
                <th>Телефон</th>
                <th>Адресс</th>
            </tr>
            <c:forEach items="${orderList}" var="order" varStatus="status">
                <tr>
                    <td>
                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Список
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                <c:forEach items="${order.productOrders}" var="product" varStatus="status">
                                    <li><a href="#">Id: ${product.productId} Количесво: ${product.productCount}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </td>
                    <td>${order.customerName}</td>
                    <td>${order.customerEmail}</td>
                    <td>${order.customerPhone}</td>
                    <td>${order.customerAddress}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>