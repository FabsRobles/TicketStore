<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Iniciar Sesion</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='./css/index.css'
</head>
<body>
    <main class="container">
        <h1 class="title">Iniciar Sesion</h1>
        <form action="login" method="post" class="auth__form">
            <label for="username">Email:</label>
            <input type="text" id="username" name="username" required><br>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required><br>
            <button type="submit">Iniciar Sesion</button>
            <c:if test="${param.error != null}">
                <p style="color: red">${param.error}</p>
            </c:if>
            <span class="dimmed">
                ¿No tienes una cuenta?
                <a class="link" href="register.jsp">Registrate</a>
            </span/>
        </form>
    </main>
</body>
</html>
