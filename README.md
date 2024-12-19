# E-commerce Application

A Java Spring Boot-based e-commerce application with REST APIs for managing products, users, and orders.

## Features

- User authentication and authorization
- Product management
- Shopping cart functionality
- Order processing
- Admin dashboard
- RESTful API endpoints

## Technologies Used

- Java 11
- Spring Boot 2.7.0
- Spring Security
- Spring Data JPA
- H2 Database
- Maven
- BCrypt Password Encoding

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/ecommerce.git
```

2. Navigate to the project directory:
```bash
cd ecommerce
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080/api`

### Default Users

The application comes with two default users:

1. Admin User
   - Username: admin
   - Password: password123
   - Role: ROLE_ADMIN

2. Regular User
   - Username: user
   - Password: password123
   - Role: ROLE_USER

## API Endpoints

### Products

- GET `/api/products` - Get all products
- GET `/api/products/{id}` - Get product by ID
