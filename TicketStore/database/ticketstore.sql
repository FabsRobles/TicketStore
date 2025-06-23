
DROP DATABASE IF EXISTS ticketstore;
CREATE DATABASE ticketstore;

USE ticketstore;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    order_date DATE NOT NULL,
    address VARCHAR(400) NOT NULL,
    method_payment VARCHAR(100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE carts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO products (name, price, stock) VALUES
('Concierto de Rock', 50.00, 5),
('Obra de Teatro', 35.00, 5),
('Partido de Fútbol', 75.00, 2),
('Concierto de Jazz', 45.00, 1),
('Ballet Clásico', 60.00, 0),
('Festival de Cine', 25.00, 2),
('Musical de Broadway', 100.00, 4),
('Conferencia Tecnológica', 150.00, 3),
('Evento de Comedia', 40.00, 6),
('Feria de Libro', 10.00, 2);

