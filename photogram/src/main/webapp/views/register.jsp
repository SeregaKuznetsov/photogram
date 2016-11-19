<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register page</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <form class="form-signin" action="register" method="post">
        <h2 class="form-signin-heading">Форма регистрации</h2>
        <input type="text" name="username" class="form-control" placeholder="Логин" autofocus="">
        <input type="password" name="password" class="form-control" placeholder="Пароль">
        <input type="email" name="email" class="form-control" placeholder="Почта">
        <input type="number" name="age" class="form-control" placeholder="Возраст">
        <button id="login-btn" class="btn btn-sm btn-primary" type="submit">Зарегистрироваться</button>
    </form>

</div>

</body>
</html>

