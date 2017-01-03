function contactForm() {
    var name = false;
    var phone = false;
    var service = false;

    $('#contactForm')
        .find('[name="project.service"]')
        .selectpicker()
        .change(function (e) {
            $('#contactForm').bootstrapValidator('revalidateField', 'service');
        })
        .end()
        .bootstrapValidator({
            framework: 'bootstrap',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            excluded: ':disabled',
            fields: {
                name: {
                    onSuccess: function (e, data) {
                        name = true;
                        console.log('nameSuccess')
                    },
                    onError: function (e, data) {
                        console.log('nameNotSucces');
                        name = false
                    },
                    validators: {
                        notEmpty: {

                            message: 'заполните поле'
                        },
                        stringLength: {
                            max: 30,
                            message: 'не больше 30 символов'
                        }
                    }
                },
                phone: {
                    onSuccess: function (e, data) {
                        phone = true;
                        console.log('phoneSuccess')
                    },
                    onError: function (e, data) {
                        console.log('phoneNotSucces');
                        phone = false
                    },
                    digits: {
                        message: 'Пожалуйста, введите только цифры'
                    },
                    validators: {
                        notEmpty: {
                            message: 'заполните поле'
                        },
                        stringLength: {
                            min: 15,
                            message: 'номер телефона 10 цифр'
                        },
                    }
                },
                service: {
                    onSuccess: function (e, data) {
                        service = true;
                        console.log('serviceSuccess')
                    },
                    onError: function (e, data) {
                        console.log('serviceNotSucces');
                        service1 = false
                    },

                    validators: {
                        notEmpty: {
                            message: 'выберите услугу'
                        }
                    }
                },
                note: {
                    validators: {
                        stringLength: {
                            max: 250,
                            message: 'Описание должно быть не более 250 символов'
                        }
                    }
                }
            }
        });

    $("#send").click(function () {
        if (name && phone && service) {
            $.ajax({
                type: 'POST',
                url: '/addCallback',
                data: $("#contact").serialize(),
                success: function (data) {
                    $("#contactForm").modal('hide');
                    if (data == "true") {
                        $("#myModalBox").modal('show');

                        $("#success").fadeIn('fast');
                        setTimeout(function () {
                            $("#success").fadeOut('fast');
                            $("#myModalBox").modal("hide");
                        }, 3000);

                    } else {
                        $("#myModalBox").modal('show');

                        $("#notSuccessful").fadeIn('fast');
                        setTimeout(function () {
                            $("#notSuccessful").fadeOut('fast');
                            $("#myModalBox").modal("hide");
                        }, 3000);
                    }
                }
            });
        }
    });
}

function makePurchase() {
    var name = false;
    var email = false;
    var address = false;
    var phone = false;

    $('#phoneOrder').mask('(###) ###-##-##');
    $('#make-purchase')
        .bootstrapValidator({
            framework: 'bootstrap',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            excluded: ':disabled',
            fields: {
                name: {
                    onSuccess: function (e, data) {
                        name = true;
                        console.log('nameSuccess')
                    },
                    onError: function (e, data) {
                        console.log('nameNotSuccess');
                        name = false
                    },
                    validators: {
                        notEmpty: {
                            message: 'заполните имя'
                        },
                        stringLength: {
                            max: 30,
                            message: 'не больше 30 символов'
                        }
                    }
                },
                email: {
                    onSuccess: function (e, data) {
                        email = true;
                        console.log('emailSuccess')
                    },
                    onError: function (e, data) {
                        console.log('emailNotSuccess');
                        email = false
                    },
                    validators: {
                        notEmpty: {
                            message: 'заполните имейл'
                        },
                        regexp: {
                            regexp: '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
                            message: 'Значение не действительный адрес электронной почты'
                        }
                    }
                },
                address: {
                    onSuccess: function (e, data) {
                        address = true;
                        console.log('serviceSuccess')
                    },
                    onError: function (e, data) {
                        console.log('serviceNotSucces');
                        address = false
                    },

                    validators: {
                        notEmpty: {
                            message: 'заполните адресс'
                        }
                    }
                },
                phone: {
                    onSuccess: function (e, data) {
                        phone = true;
                        console.log('serviceSuccess')
                    },
                    onError: function (e, data) {
                        console.log('serviceNotSucces');
                        phone = false
                    },
                    digits: {
                        message: 'Пожалуйста, введите только цифры'
                    },
                    validators: {
                        notEmpty: {
                            message: 'заполните телефон'
                        },
                        stringLength: {
                            min: 15,
                            message: 'номер телефона 10 цифр'
                        }
                    }
                }
            }
        });

    $("#sendOrder").click(function () {
        console.log($("#basket-cart").serialize());
        if (name && phone && address && email) {
            $.ajax({
                type: 'POST',
                url: '/addOrder',
                data: $("#basket-cart").serialize(),
                success: function (data) {
                    $("#make-purchase").modal('hide');
                    if (data == "true") {
                        $("#myModalBox").modal('show');

                        $("#success").fadeIn('fast');
                        setTimeout(function () {
                            $("#success").fadeOut('fast');
                            $("#myModalBox").modal("hide");
                        }, 3000);

                    } else {
                        $("#myModalBox").modal('show');

                        $("#notSuccessful").fadeIn('fast');
                        setTimeout(function () {
                            $("#notSuccessful").fadeOut('fast');
                            $("#myModalBox").modal("hide");
                        }, 3000);
                    }
                }
            });
        }
    });
}