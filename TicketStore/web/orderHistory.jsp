    <title>Historial</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='./css/index.css'
</head>
<body>
    <main class="container">
        <h1 class="title">Historial de Pedidos</h1>
            <a class="links__single" href="home">Seguir Comprando</a>
        <c:forEach var="order" items="${orders}">
            <div class="order">
                <div class="order__header">
                    <h3 class="order__title">Pedido #${order.id}</h3>
                    <p class="order__total">Total: $${order.total}</p>
                </div>
                <p class="order__date">Fecha: ${order.orderDate}</p>
                <input id="${order.id}" class="toggle" type="checkbox">
                <label for="${order.id}" class="toggle__label">Detalles</label>

                <ul class="order__list">
                    <c:forEach var="orderItem" items="${order.orderItems}">
                        <li class="order__item">
                            <p class="order__item__category">
                               <small>Producto </small>
                               ${orderItem.product.name}
                            </p>

                            <p class="order__item__category">
                               <small>Precio </small>
                               $${orderItem.product.price}
                            </p>

                            <p class="order__item__category">
                               <small>Cantidad </small>
                               ${orderItem.quantity}
                            </p>

                            <p class="order__item__category">
                                <small>Total Price </small>
                                $${orderItem.product.price * orderItem.quantity}
                            </p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </c:forEach>
    </main>
</body>
</html>
