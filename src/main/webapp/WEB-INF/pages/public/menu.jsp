<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
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
                <a class="button" href="#home" onclick="showContent('/home');">
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
                    <a class="navbar-brand" href="#home" onclick="showContent('/home')">Главная</a>
                </div>

                <div class="collapse navbar-collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false">Интернет магазин<span
                                    class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#contactForm" data-toggle="modal">Перезвоните мне</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#" onClick="showContent('/shop')">Товары</a></li>
                            </ul>
                        </li>

                    </ul>
                    <ul class="nav navbar-nav">
                        <li><a href="#contact" onClick="showContent('/contact');">Контакт</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/login">Админка</a></li>
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
        window.location.href = "#home";
        showContent('/home');
    </script>

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

    <div id="contactForm" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Заказ услуги</h4>
                </div>
                <form id="contact" name="contact" method="post" class="form-horizontal">
                    <div class="modal-body">

                        <div class="form-group has-feedback">
                            <label class="col-md-3 control-label">Имя</label>
                            <div class="col-md-6 selectContainer">
                                <input type="text" class="form-control" name="name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Номер телефона</label>
                            <div class="col-md-6 selectContainer">
                                <input id="phone" type="tel" name="phone" class="form-control"
                                       placeholder="(XXX) XXX-XX-XX" data-let-template="/^(0\d*|)$/"
                                       maxlength="15" autocomplete="off"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Услуга</label>
                            <div class="col-xs-5 selectContainer">
                                <select class="form-control" name="service">
                                    <option value="">Выберите услугу</option>

                                    <c:forEach items="${services}" var="service" varStatus="status">
                                        <option value="${service.id}">${service.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Примечание</label>
                            <div class="col-xs-8 selectContainer">
                                <textarea name="note" class="form-control" rows="5"></textarea>
                            </div>
                        </div>
                        <br/><br/>
                        <div class="form-group" id="optionTemplate">
                            <div class="col-md-9 col-md-offset-3">
                                <button id="send" class="btn btn-success">Отправить</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>
        $('#phone').mask('(###) ###-##-##');
        $(document).ready(contactForm());
    </script>
</body>
</html>

