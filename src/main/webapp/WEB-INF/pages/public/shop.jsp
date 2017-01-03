<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<div class="container">
    <div id="basket_btn" onclick="openCart()" class="btn btn-info btn-lg" data-toggle="modal"
         data-target="#make-purchase">
        <span class="glyphicon glyphicon-shopping-cart"></span> Корзина
    </div>
    <div class="row">
        <form id="myForm" name="myForm" method="post">
            <h3 style="margin-top: 0">Цена</h3>
            <div id="slider_price"></div>
            <div class="row">
                <label for="priceFrom" class="col-md-5">От:
                    <input type="text" name="priceFrom" id="priceFrom" size="2" required>
                </label>

                <label for="priceTo" class="col-md-5">До:
                    <input type="text" name="priceTo" id="priceTo" size="2" value="${maxPrice}" required>
                </label>
                <a id="sendChooseProducts" class="btn btn-success"
                   onclick="destroyPagination(); sendSecondDataAndShowContent('/chooseProducts', $('#myForm').serialize());">Ok
                </a>
            </div>
            <div class="checkbox">

                <h2>Типы</h2>
                <c:forEach items="${productTypes}" var="productType" varStatus="status">
                    <label><input type="checkbox" name="checkboxProductTypeId"
                                  onclick="destroyPagination(); sendSecondDataAndShowContent('/chooseProducts', $('#myForm').serialize()); "
                                  value="${productType.id}">${productType.name}</label><br/>
                </c:forEach>

                <h2>Фирмы</h2>
                <c:forEach items="${productFirms}" var="productFirm" varStatus="status">
                    <label><input type="checkbox" name="checkboxProductFirmId"
                                  onclick="destroyPagination(); sendSecondDataAndShowContent('/chooseProducts', $('#myForm').serialize()); "
                                  value="${productFirm.id}">${productFirm.name} (${productFirm.country})</label>
                </c:forEach>
            </div>
        </form>

        <div id="secondContentBody" class="shopContentBody"></div>
        <div id="secondLoading">
            <div id="shopSpinner"></div>
            <iframe class="iframe" onload="createSpinner('shopSpinner')"></iframe>
        </div>
    </div>

    <nav id="page_navigation" aria-label="Page navigation">
        <ul class="pagination" id="pagination"></ul>
    </nav>
</div>

<div id="make-purchase" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <br/>
                <h4 class="modal-title" align="center">Ваша корзина</h4></div>
            <form id="basket-cart" name="basket-cart" method="post" class="form-horizontal">

                <div class="modal-body">
                    <div id="cart_content"></div>
                    <div id="cart_form" class="modal-footer">
                        <div class="col-md-5">
                            <div class="form-group selectContainer">
                                <input type="text" class="form-control" name="name" placeholder="Ваше имя"/>
                            </div>

                            <div class="form-group selectContainer">
                                <input id="email" type="text" class="form-control" name="email"
                                       placeholder="Ел. почта"/>
                            </div>
                            <div class="form-group selectContainer">
                                <input id="phoneOrder" type="tel" class="form-control" name="phone"
                                       placeholder="(XXX) XXX-XX-XX" data-let-template="/^(0\d*|)$/"
                                       maxlength="15" autocomplete="off"/>
                            </div>
                        </div>
                        <div class="col-md-5 col-md-offset-2">
                            <div class="form-group selectContainer">
                                <textarea name="address" class="form-control" rows="4" style="resize: none"
                                          placeholder="Адрес"></textarea>
                            </div>
                            <button id="sendOrder" class="btn btn-success">Отправить
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="myModalBox" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <div id="success" style="display:none">Успешно! Ваше сообщение отправлено :)</div>
                <div id="notSuccessful" style="display:none">Не успешно! Что то случилось :(</div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>

<iframe class="iframe" onload="loadSlider(); makePurchase(); showSecondContent('/productsList')"></iframe>
