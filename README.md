# e-commerce


DESCRIPTION :  Application e-commerce Spring Boot avec authentification JWT, 
interface d'administration, gestion des produits et système de gestion des commandes. 

FEATURES  :  

- Inscription et connexion des utilisateurs
- Authentification JWT
- Espace d'administration
- Gestion des produits (CRUD)
- Recherche de produits
- Gestion des commandes
- Base de données MySQL
- Spring Security
- API REST

TECHNOLOGIES :

- Java 17
- Spring Boot
- Spring Security
- JWT
- MySQL / MariaDB
- Maven
- Postman


INSTALLATION : 

1. Clone repository
2. Create MySQL database:
   CREATE DATABASE ecommerce_db;
3. Configure application.properties
4. Run EcommerceApplication

   

API ENDPOINTS : 


### Authentication
POST /api/auth/register
POST /api/auth/login

### Products
GET /api/products
GET /api/products/{id}
GET /api/products/search

### Admin
POST /api/admin/products
PUT /api/admin/products/{id}
DELETE /api/admin/products/{id}
GET /api/admin/users

### Orders
POST /api/orders
GET /api/orders/my-orders

DATABASE : 

 Database Tables

- users
- products
- categories
- orders
