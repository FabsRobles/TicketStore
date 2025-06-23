<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Productos</title>
</head>
<body>
    <h1>Productos</h1>
    <c:forEach var="product" items="${products}">
        <div>
            <h3>${product.name}</h3>
            <p>Precio: ${product.price}</p>
            <p>Stock: ${product.stock}</p>
            <form action="cart" method="post">
                <input type="hidden" name="productId" value="${product.id}">
                <label for="quantity">Cantidad:</label>
                <input type="number" id="quantity" name="quantity" min="1" value="1">
                <button type="submit" name="action" value="add">Agregar al carrito</button>
            </form>
        </div>
    </c:forEach>
</body>
</html>
