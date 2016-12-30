<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Геометрические фигуры</title>
    <link rel="shortcut icon" href="<c:url value='/menuImages/favicon.ico'/>" type="image/x-icon">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.2.0/animate.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/css/bootstrap-select.min.css"/>
    <script type="text/javascript"
            src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/js/bootstrap-select.min.js"></script>

    <link rel="stylesheet"
          href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css"/>
    <script type="text/javascript"
            src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

    <script type="text/javascript"
            src="http://maps.google.com/maps/api/js?key=AIzaSyAkm7z93jf-Qcnz7_GdtEoNJxLL5qAF3qE"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
    <script style="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/dynamicLoading.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/spin.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/basket.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/content.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/content.css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/modalWindows.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modalWindows.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-combobox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.twbsPagination.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mask.js"></script>
</head>
<body>
    <div class="container">
        <nav aria-label="...">
            <ul class="pager">
                <a class="button" href="#callbacks" onclick="showContent('/showCallbacks');">
                    <img id="logo" src="<c:url value='/menuImages/logo.jpg'/>"/>
                </a>
            </ul>
        </nav>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#navbar-collapse" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>

                <div class="collapse navbar-collapse navbar-nav" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="#callbacks" onclick="showContent('/showCallbacks');">Перезвонить</a></li>
                        <li><a href="#orders" onclick="showContent('/showOrders');">Заказы</a></li>
                        <li><a href="#services" onclick="showContent('/showServices');">Услуги</a></li>
                        <li><a href="#productType" onclick="showContent('/showProductTypes');">Тип продукта</a></li>
                        <li><a href="#productFirm" onclick="showContent('/showProductFirms');">Тип фирмы</a></li>
                        <li><a href="#product" onclick="showContent('/showProducts');">Продукт</a></li>
                        <li><a href="#admins" onclick="showContent('/showAdmins');">Администраторы</a></li>
                        <c:url value="/logout" var="logoutUrl"/>
                        <li><a href="${logoutUrl}">Выход</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>

    <div id="contentBody"></div>
    <div id="loading">
        <%--Loading...--%>
        <div id="menuSpinner"></div>
        <script>createSpinner('menuSpinner')</script>
    </div>

    <script type="text/javascript">
        window.location.href = "#callbacks";
        showContent('/showCallbacks');
    </script>
</body>
</html>
