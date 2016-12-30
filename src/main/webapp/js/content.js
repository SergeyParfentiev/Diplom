var totalPages;
var firstOnPageClick = false;
function destroyPagination() {
    firstOnPageClick = false;
    $('#pagination').twbsPagination('destroy');
}

function text() {
    console.log('text');
}

function pages() {
    var test = $("#totalPages");
    totalPages = $(test).val();

    return totalPages;
}

function loadPagination() {
    if (!firstOnPageClick) {
        $('#pagination').twbsPagination({
            totalPages: pages(),
            visiblePages: 10,
            onPageClick: function (event, page) {
                // window.scrollTo(0, 1000);
                if (firstOnPageClick) {
                    sendSecondDataAndShowContent('/chooseProducts', $("#myForm").serialize() + '&page=' + page);
                } else {
                    firstOnPageClick = true;
                }
            },
        });
    }
}

function createSpinner(id) {
    var opts = {
        lines: 13, // Число линий для рисования
        length: 0, // Длина каждой линии
        width: 10, // Толщина линии
        radius: 30, // Радиус внутреннего круга
        corners: 1, // Скругление углов (0..1)
        rotate: 0, // Смещение вращения
        direction: 1, // 1: по часовой стрелке, -1: против часовой стрелки
        color: '#000', // #rgb или #rrggbb или массив цветов
        speed: 2.2, // Кругов в секунду
        trail: 17, // Послесвечение
        shadow: false, // Тень(true - да; false - нет)
        hwaccel: false, // Аппаратное ускорение
        className: 'spinner', // CSS класс
        zIndex: 2e9, // z-index (по-умолчанию 2000000000)
        top: '60%', // Положение сверху относительно родителя
        left: '50%' // Положение слева относительно родителя
    };
    var target = document.getElementById(id);
    var spinner = new Spinner(opts).spin(target);
}

function initializeMap() {
    var fenway = {lat: 50.4380007, lng: 30.5135582};
    var map = new google.maps.Map(document.getElementById('map'), {
        center: fenway,
        zoom: 18
    });

    var panorama = new google.maps.StreetViewPanorama(
        document.getElementById('pano'), {
            position: fenway,
            pov: {
                heading: -110,
                pitch: 0,
                zoom: 2
            }
        });
    map.setStreetView(panorama);
}

function loadCarousel() {
    // Activate Carousel
    console.log('carousel1');
    $("#myCarousel").carousel({
        interval: 5000
    });
}

function loadSlider() {
    var priceFromVar = $("#priceFrom");
    var priceToVar = $("#priceTo");
    var slider_priceVar = $("#slider_price");

    var end = $(priceToVar).val();

    $(priceFromVar).change(function () {
        var min = $(this).val();
        var max = $(priceToVar).val();
        if (min == '') $(this).val(0);
        if (min > (max - 50)) {
            min = max - 50;
            $(this).val(min);
        }
        $(slider_priceVar).slider("values", 0, min);
    });

    $(priceToVar).change(function () {
        var min = $(priceFromVar).val();
        var max = $(this).val();
        if ((max > end) || max.length > end.length) $(this).val(end);
        if (min > (max - 50)) {
            max = min - (-50);
            $(this).val(max);
        }
        $(slider_priceVar).slider("values", 1, max);
    });

    var start = $(priceFromVar).val();


    $(slider_priceVar).slider({
        range: true,
        min: 0,
        max: end,
        values: [0, end],
        slide: function (event, ui) {
//                    $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
            if ((ui.values[1] - ui.values[0]) < 50) {
                return false;
            } else {
                $(priceFromVar).val(ui.values[0]);
                $(priceToVar).val(ui.values[1]);
            }
        }
    });
//            $("#amount").val("$" + $("#slider-range").slider("values", 0) +
//                    " - $" + $("#slider-range").slider("values", 1));
    $(priceFromVar).val($(slider_priceVar).slider("values", 0));
    $(priceToVar).val($(slider_priceVar).slider("values", 1));

    $(priceFromVar).keypress(function (e) {
        if (!(e.which > 47 && e.which < 58)) return false;
    });

    $(priceToVar).keypress(function (e) {
        if (!(e.which > 47 && e.which < 58)) return false;
    });
}

function sendDataWithFile(url, form, dynamicLoadingUrl) {
    var formData = new FormData(form[0]);
    $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: url,
            data: formData

            .done(function () {
                if (dynamicLoadingUrl != null) {
                    showContent(dynamicLoadingUrl);
                }
            })
    })
}

function runScript(e) {
    if (e.keyCode == 13) {
        event.preventDefault();
    }
}