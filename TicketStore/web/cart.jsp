<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Carrito</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='./css/index.css'
</head>
<body>
    <main class="container">
        <h1 class="title">Carrito de Compras</h1>
        <ul class="links--small">
            <a class="links__item" href="home">Seguir Comprando</a>
             <c:if test="${!cart.items.isEmpty()}">
                <a class="links__item links__item--acent" href="checkout.jsp">Ir a la Caja</a>
            </c:if>
        </ul>
        <p class="cart__total">Total: $${cart.totalPrice}</p>
         <c:if test="${cart.items.isEmpty()}">
            <p class="title">No hay productos añadidos</p>
        </c:if>
        <c:if test="${!cart.items.isEmpty()}">
            <c:forEach var="item" items="${cart.items}">
                <div class="cart__item">
                    <h3 class="cart__item__title">${item.key.name}</h3>
                    <p class="cart__item__price">Precio: $${item.key.price}</p>
                    <form action="cart" method="post" class="cart__form">
                        <input type="hidden" name="productId" value="${item.key.id}">
                        <div class="cart__form__group">
                            <label for="quantity">Cantidad:</label>
                            <input type="number" id="quantity" name="quantity" min="0" value="${item.value}">
                        </div>
                        <button type="submit" name="action" value="update">Actualizar</button>
                        <button type="submit" name="action" value="remove">Eliminar</button>
                    </form>
                </div>
            </c:forEach>
        </c:if>
    </main>
</body>
</html>
