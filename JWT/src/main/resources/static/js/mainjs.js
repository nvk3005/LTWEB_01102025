// Hàm đăng xuất
$('#btnLogout').click(function() {
    localStorage.clear();
    window.location.href = '/login';
});

// Hàm login (submit form)
$('#loginForm').on('submit', function(e) {
    e.preventDefault();
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    $.ajax({
        url: '/api/auth/login',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({ email, password }),
        success: function(data) {
            localStorage.token = data.token;
            window.location.href = '/user/profile';
        },
        error: function(xhr) {
            alert('Login Failed');
        }
    });
});

// Hàm lấy profile
function getProfile() {
    $.ajax({
        url: '/users/me',
        type: 'GET',
        headers: { 'Authorization': 'Bearer ' + localStorage.token },
        success: function(data) {
            $('#userInfo').text(JSON.stringify(data));
        },
        error: function() {
            alert('Failed');
        }
    });
}