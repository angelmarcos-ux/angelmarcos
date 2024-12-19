-- Insert admin user
INSERT INTO users (username, password, email, role) 
VALUES ('admin', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'admin@example.com', 'ROLE_ADMIN');

-- Insert some sample products
INSERT INTO product (name, description, price, stock_quantity, image_url) 
VALUES 
('Laptop', 'High-performance laptop with 16GB RAM', 999.99, 50, 'https://example.com/laptop.jpg'),
('Smartphone', 'Latest model with 5G capability', 699.99, 100, 'https://example.com/smartphone.jpg'),
('Headphones', 'Wireless noise-cancelling headphones', 199.99, 75, 'https://example.com/headphones.jpg'),
('Tablet', '10-inch display with 128GB storage', 449.99, 30, 'https://example.com/tablet.jpg'),
('Smartwatch', 'Fitness tracking and notifications', 249.99, 60, 'https://example.com/smartwatch.jpg');

-- Insert a regular user
INSERT INTO users (username, password, email, role) 
VALUES ('user', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'user@example.com', 'ROLE_USER');

-- Note: The password for both users is 'password123'
