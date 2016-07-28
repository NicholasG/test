$('.toggle').on('click', function () {
    $('.container').stop().addClass('active');
});

$('.close').on('click', function () {
    $('.container').stop().removeClass('active');
});

$(document).ready(function () {
    $('#loginButton').click(function (e) {
        $.ajax({
            type: 'POST',
            url: '/login',
            dataType: 'json',
            data: {
                username: $('#username').val(),
                password: $('#password').val()
            },
            success: function (data, textStatus, jqXHR) {
                alert(textStatus);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("Something really bad happened " + textStatus);
                alert(errorThrown);
            },
            complete: function (jqXHR, textStatus) {
                alert(textStatus);
            }
        });
    });
});
