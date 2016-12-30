<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="admin_overflow">
        <table class="list">
            <tr>
                <th>Услуги</th>
            </tr>
            <c:forEach items="${serviceList}" var="service" varStatus="status">
                <tr>
                    <td>${service.name}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <form id="serviceForm" name="serviceForm" method="post" class="form-horizontal">
        <label for="newService">Добавить услугу: <input id="newService" onkeypress="return runScript(event)"
                                                        type="text" name="newService"/></label>
        <a id="send" class="btn btn-success"
           onclick="sendDataAndShowContent('/addService', $('#serviceForm').serialize());">Добавить
        </a>
    </form>
</div>