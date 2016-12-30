function showContent(link) {
    console.info('showContent');
    var cont = document.getElementById('contentBody');
    var loading = document.getElementById('loading');
    cont.innerHTML = loading.innerHTML;
    var http = createRequestObject();
    if (http) {
        http.open('get', link);
        http.onreadystatechange = function () {
            if (http.readyState == 4) {
                cont.innerHTML = http.responseText;
            }
        };
        http.send(null);
    }
    else {
        document.location = link;
    }
}

function sendDataAndShowContent(link, content) {
    var cont = document.getElementById('contentBody');
    var loading = document.getElementById('loading');
    cont.innerHTML = loading.innerHTML;
    var http = createRequestObject();
    if (http) {
        console.log(content)
        http.open('get', link + "?" + content, true);

        http.onreadystatechange = function () {
            if (http.readyState == 4) {
                cont.innerHTML = http.responseText;
            }
        };
        http.send(content);

    }
    else {
        document.location = link;
    }
}

function showSecondContent(link) {
    console.info('showContent');
    var cont = document.getElementById('secondContentBody');
    var loading = document.getElementById('secondLoading');
    cont.innerHTML = loading.innerHTML;
    var http = createRequestObject();
    if (http) {
        http.open('get', link);
        http.onreadystatechange = function () {
            if (http.readyState == 4) {
                cont.innerHTML = http.responseText;
            }
        };
        http.send(null);
    }
    else {
        document.location = link;
    }
}

function sendSecondDataAndShowContent(link, content) {
    var cont = document.getElementById('secondContentBody');
    var loading = document.getElementById('secondLoading');
    cont.innerHTML = loading.innerHTML;
    var http = createRequestObject();
    if (http) {
        http.open('get', link + "?" + content, true);

        http.onreadystatechange = function () {
            if (http.readyState == 4) {
                cont.innerHTML = http.responseText;
            }
        };
        http.send(content);

    }
    else {
        document.location = link;
    }
}

// ajax объект
function createRequestObject() {
    try {
        return new XMLHttpRequest()
    }
    catch (e) {
        try {
            return new ActiveXObject('Msxml2.XMLHTTP')
        }
        catch (e) {
            try {
                return new ActiveXObject('Microsoft.XMLHTTP')
            }
            catch (e) {
                return null;
            }
        }
    }
}
