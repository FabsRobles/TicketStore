<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Checkout</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='./css/index.css'
</head>
<body>
    <main class="container">
        <h1 class="title">Checkout</h1>
        <form action="checkout" method="post" class="checkout__form">
            <label for="address">Dirección:</label>
            <input type="text" id="address" name="address" required><br>
            <label for="payment">Método de Pago:</label>
           <select name="payment" id="payment">
                <option value="credit">Tarjeta de Crédito</option>
                <option value="debit">Tarjeta de Débito</option>
                <option value="cash">Efectivo</option>
            </select>
            <button type="submit">Pagar</button>
        </form>
    </main>
</body>
</html>
