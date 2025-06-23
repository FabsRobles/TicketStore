<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Home</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='./css/index.css'
</head>
<body>
    <main class="container">
        <nav class="navbar">
            <div class="logo">
                <span class="welcome">Bienvenido ${userName}</span>
                <span class="time">Tiempo conectado: ${loginTime} segundos</span>
            </div>
            <ul class="links">
                <a class="links__item" href="order-history">Ver Historial</a>
                <a class="links__item" href="cart">Ver Carrito</a>
                <a class="links__item" href="logout">Cerrar Sesión</a>
            </ul>
        </nav>
   
        <h2 class="title navbar--space">Eventos</h2>
        <div class="grid">
            <c:forEach var="product" items="${products}">
                <div class="product flex flex__column flex__center">
                    <h2 class="product__title">${product.name}</h2>
                    <p class="product__price">Precio: $${product.price}</p> 
                    <p class="product__stock">Stock: ${product.stock}</p> 
                    <form action="cart" method="post" class="product__form">
                        <input type="hidden" name="productId" value="${product.id}">
                        <label for="quantity">Cantidad:</label>
                       
                        <c:if test="${product.stock > 0}">
                            <input type="number" id="quantity" name="quantity" min="1" max="${product.stock}" value="1">
                            <button  type="submit" name="action" value="add">
                               Agregar al carrito
                            </button>
                        </c:if>
                        <c:if test="${product.stock <= 0}">
                            <input disabled type="number" id="quantity" name="quantity" min="1" value="1">
                            <button disabled type="submit" name="action" value="add">
                               Sin Stock
                            </button>
                        </c:if>

                    </form>
                </div>
            </c:forEach>
        <div>
    </main>
</body>
</html>
