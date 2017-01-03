var localStorage;
var cartContent;

// Получаем данные из LocalStorage
function getCartData() {
    return JSON.parse(localStorage.getItem('cart'));
}
// Записываем данные в LocalStorage
function setCartData(o) {
    localStorage.setItem('cart', JSON.stringify(o));
    return false;
}
// Функция кроссбраузерной установка обработчика событий
function addEvent(elem, type, handler) {
    if (elem.addEventListener) {
        elem.addEventListener(type, handler, false);
    } else {
        elem.attachEvent('on' + type, function () {
            handler.call(elem);
        });
    }
    return false;
}

function fillBasket() {
    var d = document,
        itemBox = d.querySelectorAll('.product'); // блок каждого товара
    cartContent = d.getElementById('cart_content'); // блок вывода данных корзины

    // Добавляем товар в корзину
    function addToCart(e) {
//                console.log($("#cart-overflow").scrollTop());
        this.disabled = true; // блокируем кнопку на время операции с корзиной
        var cartData = getCartData() || {}, // получаем данные корзины или создаём новый объект, если данных еще нет
            parentBox = this.parentNode, // родительский элемент кнопки "Добавить в корзину"
            itemId = this.getAttribute('data-id'), // ID товара
            img = parentBox.querySelector('.div_img').innerHTML, // название товара
            itemProductName = parentBox.querySelector('.product_name').innerHTML,
            itemFirmName = parentBox.querySelector('.firm_name').innerHTML,
            itemPrice = parentBox.querySelector('.price').innerHTML; // стоимость товара
        if (cartData.hasOwnProperty(itemId)) { // если такой товар уже в корзине, то добавляем +1 к его количеству
            cartData[itemId][3] += 1;
            cartData[itemId][4] = cartData[itemId][3] * cartData[itemId][5];
        } else { // если товара в корзине еще нет, то добавляем в объект
            cartData[itemId] = [img, itemProductName, itemFirmName, 1, itemPrice, itemPrice];
        }
        if (!setCartData(cartData)) { // Обновляем данные в LocalStorage
            this.disabled = false; // разблокируем кнопку после обновления LS
        }
        return false;
    }

    // Устанавливаем обработчик события на каждую кнопку "Добавить в корзину"
    for (var i = 0; i < itemBox.length; i++) {
        addEvent(itemBox[i].querySelector('.btn'), 'click', addToCart);
    }
}

/* Открыть корзину */
function openCart(scrollTopValue) {
    var cartData = getCartData(), // вытаскиваем все данные корзины
        totalPrice = 0,
        totalItems = '';
    // если что-то в корзине уже есть, начинаем формировать данные для вывода
    if (cartData !== null && Object.keys(cartData).length != 0) {
        totalItems = '<a href="javascript:;" onclick="clearBasket()" aria-label="Left Align" > ' +
            '<h4><span class="glyphicon glyphicon-remove-circle"></span> Очистить корзину</a> ' +
            '</h4>';
        totalItems += '<div id="cart-overflow"><table width="100%" class="shopping_list">';

        for (var items in cartData) {
            totalItems += '<tr>';

            totalItems += '<td><a href="javascript:;" onclick="buttonDelete(' + items + ')" aria-label="Left Align">' +
                '<span class="glyphicon glyphicon-remove-circle"></span></a><input name="id" value="' + items + '" style="display: none"/></td>' +
                '<td style="width: 100px; height: 100px">' + cartData[items][0] + '</td>' +
                '<td><div>' + cartData[items][1] + '<div> ' + cartData[items][2] + '</div></div></td>' +
                '<td> <a href="javascript:;" onclick="itemPriceDecrement(' + items + ')"> - </a>'
                + cartData[items][3] + '<input name="count" value="' + cartData[items][3] + '" style="display: none"/><a href="javascript:;" onclick="itemPriceIncrement(' + items + ')"> +</a> </td>' +
                '<td> × ' + cartData[items][4] + ' грн.</td></tr>';
            totalPrice += parseInt(cartData[items][4]);
        }
        totalItems += '</table></div>';
        totalItems += '<div id="totalPrice" style="text-align: right; margin-right: 7%"><h3>Итого: ' + totalPrice + '</h3></div>';

        $("#cart_form").fadeIn('fast');
        cartContent.innerHTML = totalItems;
    } else {
        // если в корзине пусто, то сигнализируем об этом
        $("#cart_form").fadeOut('fast');
        cartContent.innerHTML = '<div style="text-align: center"><h4>В корзине пусто!</h4></div>';
    }

    if (typeof (scrollTopValue) == 'number') {
        var overflow = document.getElementById('cart-overflow');
        overflow.scrollTop = scrollTopValue;
    }
    return false;
}

function buttonDelete(itemId) {
    var cartData = getCartData();
    delete cartData[itemId];
    setCartData(cartData);
    openCart($("#cart-overflow").scrollTop());
}

function clearBasket() {
    localStorage.removeItem('cart');
    openCart();
}

function itemPriceIncrement(itemId) {
    var cartData = getCartData();
    cartData[itemId][3] += 1;
    cartData[itemId][4] = cartData[itemId][3] * cartData[itemId][5];
    setCartData(cartData);
    openCart($("#cart-overflow").scrollTop());
}

function itemPriceDecrement(itemId) {
    var cartData = getCartData();
    if (cartData[itemId][3] > 1) {
        cartData[itemId][3] -= 1;
        cartData[itemId][4] = cartData[itemId][3] * cartData[itemId][5];
        setCartData(cartData);
        openCart($("#cart-overflow").scrollTop());
    }
}
