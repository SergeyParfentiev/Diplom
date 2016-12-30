<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>

<div id="home" class="container">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner" role="listbox">
            <div class="item carousel-item active">
                <img class="carousel-img" src="<c:url value='/menuImages/cube1.jpg'/>" alt="cube">
                <div class="carousel-caption">
                    <div align="right">
                        <h3>Куб</h3>
                        <p>Лед.</p>
                    </div>
                </div>
            </div>

            <div class="item carousel-item">
                <img class="carousel-img" src="<c:url value='/menuImages/lake2.jpg'/>" alt="lake">
                <div class="carousel-caption">
                    <h3 align="right">Озеро</h3>
                    <p align="right">Среди рощи.</p>
                </div>
            </div>

            <div class="item carousel-item">
                <img class="carousel-img" src="<c:url value='/menuImages/wave3.jpg'/>" alt="wave">
                <div class="carousel-caption">
                    <h3>Волна</h3>
                    <p>Брызги.</p>
                </div>
            </div>

            <div class="item carousel-item">
                <img class="carousel-img" src="<c:url value='/menuImages/kitchen2.jpg'/>" alt="kitchen">
                <div class="carousel-caption">
                    <h3>Кухня</h3>
                    <p>Фильтры.</p>
                </div>
            </div>

            <div class="item carousel-item">
                <img class="carousel-img" src="<c:url value='/menuImages/glassTest2.jpg'/>" alt="glass">
                <div class="carousel-caption">
                    <h3>Стакан</h3>
                    <p>Прозрачная вода.</p>
                </div>
            </div>
        </div>

        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
            <li data-target="#myCarousel" data-slide-to="4"></li>
        </ol>
    </div>
    <iframe class="iframe" onload="loadCarousel()"></iframe>
</div>
