<!DOCTYPE html>
<html>
<head>
    <title>Productos Recientemente Vistos</title>
</head>
<body>
    <h1>Recientemente Vistos</h1>
    <c:forEach var="product" items="${recentSearches}">
        <div>
            <h3>${product.name}</h3>
            <p>Precio: ${product.price}</p>
        </div>
    </c:forEach>
</body>
</html>
