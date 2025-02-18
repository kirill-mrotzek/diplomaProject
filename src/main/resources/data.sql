INSERT INTO categories (category_title) VALUES ('Tools and equipment');
INSERT INTO categories (category_title) VALUES ('Planting material');
INSERT INTO categories (category_title) VALUES ('Fertilizer');

INSERT INTO products (product_title, price, product_info, discount, category_id) VALUES
('Hammer', 15.99, 'Heavy hammer for construction works', 5.00, 1);
INSERT INTO products (product_title, price, product_info, discount, category_id) VALUES
('Tomato Seeds', 2.99, 'Premium quality tomato seeds', 0.00, 2);
INSERT INTO products (product_title, price, product_info, discount, category_id) VALUES
('Organic Fertilizer', 10.99, 'High-quality organic fertilizer', 0.00, 3);

INSERT INTO shop_users (username, email, password, phone_number, role) VALUES
('JohnDoe', 'john.doe@example.com', 'johnpassword', '+1234567890', 'ROLE_USER'),
('AdminUser', 'admin@example.com', 'adminpassword', '+1987654321', 'ROLE_ADMIN'),
('JaneSmith', 'jane.smith@example.com', 'janepassword', '+1122334455', 'ROLE_USER');

INSERT INTO storage (id, amount) VALUES (1, 1, 100);
INSERT INTO storage (id, amount) VALUES (2, 2, 50);
INSERT INTO storage (id, amount) VALUES (3, 3, 200);
