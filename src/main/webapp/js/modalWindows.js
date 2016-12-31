function contactForm() {
    var name = false;
    var phone = false;
    var service = false;

    $('#contactForm')
        .find('[name="project.service"]')
        .selectpicker()
        .change(function (e) {
            /* Revalidate the language when it is changed */
            $('#contactForm').bootstrapValidator('revalidateField', 'service');
        })
        .end()
        .bootstrapValidator({
//                container: '#messages',
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

                            message: 'заполните поле'//'The full name is required and cannot be empty'
                        },
                        stringLength: {
                            max: 30,
                            message: 'не больше 30 символов'//'The title must be less than 5 characters long',
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
                            message: 'заполните поле'//'Contact Phoneno is required'
                        },
                        stringLength: {
                            min: 15,
                            message: 'номер телефона 10 цифр'//'The title must be more than 15 characters long',
                        },
                    }
                },
                service: {
                    onSuccess: function (e, data) {
                        service = true;
                        console.log('serviceSuccess')
                    },
                    onError: function (e, data) {
                        console.log('serviceNotSucces')
                        service1 = false
                    },

                    validators: {
                        notEmpty: {
                            message: 'выберите услугу'//'The size is required'
                        }
                    }
                },
                note: {
                    validators: {
//                                    notEmpty: {
//                                        message: 'заполните поле'//'The description is required'
//                                    },
                        stringLength: {
//                                        min: 50,
                            max: 250,
                            message: 'Описание должно быть не более 250 символов'
                            //'The description must be more than 50 and less than 1000 characters'
                        }
                    }
                }
            }
        });
//                    .find('[name="project.service"]')
//                    .combobox()
//                    .end();

    $("#send").click(function () {
        if (name && phone && service) {
            console.log('name ' + name + ' phone ' + phone + ' service ' + service);
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
                            // Скрываем его
                        }, 3000);

                    } else {
                        $("#myModalBox").modal('show');

                        $("#notSuccessful").fadeIn('fast');
                        setTimeout(function () {
                            $("#notSuccessful").fadeOut('fast');
                            $("#myModalBox").modal("hide");
                            // Скрываем его
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
                            message: 'заполните имя'//'The full name is required and cannot be empty'
                        },
                        stringLength: {
                            max: 30,
                            message: 'не больше 30 символов'//'The title must be less than 5 characters long',
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
                            message: 'заполните адресс'//'The size is required'
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
                            message: 'заполните поле'//'Contact Phoneno is required'
                        },
                        stringLength: {
                            min: 15,
                            message: 'номер телефона 10 цифр'//'The title must be more than 15 characters long',
                        },
                    }
                }
//
            }
        });

    $("#sendOrder").click(function () {
        console.log($("#basket-cart").serialize());
        if (name && phone && address && email) {
            console.log('name ' + name + ' phone ' + phone + ' service ' + address + ' email ' + email);
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
                            // Скрываем его
                        }, 3000);

                    } else {
                        $("#myModalBox").modal('show');

                        $("#notSuccessful").fadeIn('fast');
                        setTimeout(function () {
                            $("#notSuccessful").fadeOut('fast');
                            $("#myModalBox").modal("hide");
                            // Скрываем его
                        }, 3000);
                    }
                }
            });
        }
    });
}