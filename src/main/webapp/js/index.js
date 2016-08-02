$('.toggle').on('click', function () {
    $('.container').stop().addClass('active');
});

$('.close').on('click', function () {
    $('.container').stop().removeClass('active');
});

$(document).ready(function () {
    $('#loginButton').click(function (e) {

        var username = $('#username').val();
        var password = $('#password').val();
        // var url = '/login?username=' + username + '&password=' + password + '&submit=Login';
        const url = '/login';
        $.ajax({
            type: 'POST',
            url: url,
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded',
            data: {
                username: username,
                password: password
            },
            success: function (data, textStatus) {
                alert(textStatus);
            },
            error: function (textStatus, errorThrown) {
                console.log("Something really bad happened " + textStatus);
                alert(errorThrown);
            },
            complete: function (textStatus) {
                alert(textStatus);
            }
        });
    });
});
