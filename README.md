# 🚀 BajarLink POS System

A complete **Multi-Store POS (Point of Sale) & Inventory Management System** built with modern backend technologies using **Spring Boot**.

This project provides a scalable REST API architecture for managing:

* 🏪 Multiple Stores
* 🏢 Branches
* 📦 Products & Categories
* 📊 Inventory
* 👨‍💼 Employees
* 👤 Customers
* 🧾 Orders
* 🔐 Authentication & Authorization

---

# ✨ Features

## 🔐 Authentication & Security

* JWT Authentication
* Role-Based Authorization
* Secure Password Encryption
* Protected REST APIs
* Spring Security Integration

---

## 🏪 Store Management

* Create & manage multiple stores
* Store contact management
* Store-level access control

---

## 🏢 Branch Management

* Multiple branches per store
* Branch working days support
* Branch-specific inventory & employees

---

## 📂 Product & Category Management

* Product CRUD operations
* Category management
* SKU support
* Product search functionality

---

## 📊 Inventory Management

* Branch-wise inventory tracking
* Quantity management
* Automatic inventory deduction after orders

---

## 👨‍💼 Employee Management

* Store Managers
* Branch Managers
* Cashiers
* Role-based employee access

---

## 👤 Customer Management

* Customer CRUD operations
* Customer search
* Order history support

---

## 🧾 Order Management

* Create customer orders
* Multi-item order support
* Recent branch orders
* Cashier-wise orders
* Customer-wise orders

---

# 🔄 Complete POS Workflow

```text
Store Manager Signup
        │
        ▼
Create Store
        │
        ▼
Create Branch
        │
        ▼
Create Categories
        │
        ▼
Create Products
        │
        ▼
Add Inventory
        │
        ▼
Create Employees
        │
        ▼
Create Customers
        │
        ▼
Create Orders
        │
        ▼
Inventory Auto Updates
```

---

# 👥 User Roles

| Role                | Description                  |
| ------------------- | ---------------------------- |
| ROLE_STORE_MANAGER  | Full store management access |
| ROLE_BRANCH_MANAGER | Branch management access     |
| ROLE_BRANCH_CASHIER | Order & customer handling    |

---

# 🌐 Base URL

```http
http://localhost:8080
```

---

# ⚙️ Tech Stack

| Technology      | Usage                |
| --------------- | -------------------- |
| Java            | Core Programming Language |
| Spring Boot     | Backend Framework    |
| Spring Security | Authentication & Security |
| JWT             | Authorization        |
| JPA             | Database Access      |
| MySQL           | Relational Database  |
| Maven           | Dependency Management |
| Postman         | API Testing          |

---

# 📁 Project Structure

```text
src/main/java/com/bajarlink
│
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── security
├── exception
└── util
```

---

# 🔐 Authentication APIs

## Signup User

```http
POST /auth/signup
```

### Request Body

```json
{
  "fullName": "your_name",
  "email": "your_email",
  "password": "your_password",
  "phoneNumber": "your_phoneNumber",
  "role": "your_role"
}
```

---

## Login

```http
GET /auth/login
```

---

## Get Current User Profile

```http
GET /api/user/profile
```

### Authorization

```http
Bearer Token Required
```
---

## Get User By ID

### Endpoint

```http
GET /api/user/{id}
```

Example:

```http
GET /api/user/1
```

---


# 🏪 Store APIs

| Method | Endpoint          | Description                                                                            |
| ------ | ----------------- | -------------------------------------------------------------------------------------- |
| POST   | `/api/store`      | Create a new store with brand, description, and contact information.                   |
| GET    | `/api/store/{id}` | Retrieve detailed information about a specific store by ID.                            |
| GET    | `/api/store`      | Fetch all stores available in the system.                                              |
| PUT    | `/api/store/{id}` | Update existing store information such as brand name, description, or contact details. |

---

# 📂 Category APIs

| Method | Endpoint                          | Description                                         |
| ------ | --------------------------------- | --------------------------------------------------- |
| POST   | `/api/categories`                 | Create a new product category for a specific store. |
| GET    | `/api/categories/store/{storeId}` | Retrieve all categories belonging to a store.       |
| PUT    | `/api/categories/{id}`            | Update the category name or details.                |
| DELETE | `/api/categories/{id}`            | Permanently remove a category from the system.      |

---

# 📦 Product APIs

| Method | Endpoint                                             | Description                                                                |
| ------ | ---------------------------------------------------- | -------------------------------------------------------------------------- |
| POST   | `/api/product`                                       | Create a new product with pricing, SKU, brand, and category information.   |
| GET    | `/api/product/store/{storeId}`                       | Retrieve all products belonging to a specific store.                       |
| GET    | `/api/product/store/{storeId}/search?keyword=TSHIRT` | Search products within a store using a keyword, SKU, or product name.      |
| PATCH  | `/api/product/{id}`                                  | Partially update product information such as price, description, or brand. |
| DELETE | `/api/product/{id}`                                  | Delete a product from the store inventory system.                          |

---

# 🏢 Branch APIs

| Method | Endpoint                      | Description                                                               |
| ------ | ----------------------------- | ------------------------------------------------------------------------- |
| POST   | `/api/branch`                 | Create a new branch for a store with address and contact details.         |
| PUT    | `/api/branch/{id}`            | Update branch information such as phone number, address, or working days. |
| DELETE | `/api/branch/{id}`            | Remove a branch from the system.                                          |
| GET    | `/api/branch/{id}`            | Retrieve branch details using the branch ID.                              |
| GET    | `/api/branch/store/{storeId}` | Fetch all branches associated with a specific store.                      |

---

# 📊 Inventory APIs

| Method | Endpoint                                               | Description                                                            |
| ------ | ------------------------------------------------------ | ---------------------------------------------------------------------- |
| POST   | `/api/inventory`                                       | Add product inventory to a branch with an initial quantity.            |
| PUT    | `/api/inventory/{id}`                                  | Update inventory quantity for a specific inventory record.             |
| GET    | `/api/inventory/{id}`                                  | Retrieve inventory details using inventory ID.                         |
| GET    | `/api/inventory/branch/{branchId}`                     | Fetch all inventory items available in a branch.                       |
| GET    | `/api/inventory/product/{productId}/branch/{branchId}` | Retrieve inventory details of a specific product in a specific branch. |
| DELETE | `/api/inventory/{id}`                                  | Delete an inventory record from the system.                            |

---

# 👨‍💼 Employee APIs

| Method | Endpoint                          | Description                                                        |
| ------ | --------------------------------- | ------------------------------------------------------------------ |
| POST   | `/api/employee/store/{storeId}`   | Create a new employee under a store with assigned role and branch. |
| POST   | `/api/employee/branch/{branchId}` | Create a branch-level employee such as cashier or branch manager.  |
| PUT    | `/api/employee/{id}`              | Update employee details such as role, branch, or phone number.     |
| GET    | `/api/employee/store/{storeId}`   | Retrieve all employees belonging to a store.                       |
| GET    | `/api/employee/branch/{branchId}` | Retrieve all employees assigned to a branch.                       |
| DELETE | `/api/employee/{id}`              | Remove an employee account from the system.                        |

---

# 👤 Customer APIs

| Method | Endpoint                       | Description                                                |
| ------ | ------------------------------ | ---------------------------------------------------------- |
| POST   | `/api/customer`                | Create a new customer profile.                             |
| PUT    | `/api/customer/{id}`           | Update customer information such as email or phone number. |
| DELETE | `/api/customer/{id}`           | Delete a customer from the database.                       |
| GET    | `/api/customer/{id}`           | Retrieve customer details using customer ID.               |
| GET    | `/api/customer`                | Fetch all registered customers.                            |
| GET    | `/api/customer/search?q=gmail` | Search customers using keyword, email, or name.            |

---

# 🧾 Order APIs

| Method | Endpoint                               | Description                                                              |
| ------ | -------------------------------------- | ------------------------------------------------------------------------ |
| POST   | `/api/orders`                          | Create a new order for a customer with multiple products and quantities. |
| GET    | `/api/orders/{id}`                     | Retrieve complete order details using order ID.                          |
| GET    | `/api/orders/branch/{branchId}`        | Fetch all orders created within a specific branch.                       |
| GET    | `/api/orders/cashier/{cashierId}`      | Retrieve all orders processed by a specific cashier.                     |
| GET    | `/api/orders/customer/{customerId}`    | Fetch all orders associated with a customer.                             |
| GET    | `/api/orders/branch/{branchId}/recent` | Retrieve the most recent orders placed in a branch.                      |
| DELETE | `/api/orders/{id}`                     | Delete or cancel an order from the system.                               |

---

# 🗃️ Database Relationship Overview

```text
User
 ├── Store
 │     ├── Category
 │     ├── Product
 │     ├── Branch
 │            ├── Inventory
 │            ├── Employee
 │            └── Orders
 │
 └── Customer
```

---

# ▶️ Getting Started

## 1️⃣ Clone Repository

```bash
git clone <your_repo_url>
```

---

## 2️⃣ Configure Database

Update your `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bajarlink
spring.datasource.username=root
spring.datasource.password=your_password
```

---

## 3️⃣ Install Dependencies

```bash
mvn clean install
```

---

## 4️⃣ Run Application

```bash
mvn spring-boot:run
```

---

# 🔒 Security Features

* JWT Token Authentication
* Stateless Authentication
* Role-Based Access Control
* Password Encryption using BCrypt
* Secured API Endpoints

---
# 📈 Future Improvements

## 💳 Payment & Billing

* Payment Gateway Integration (Razorpay, Stripe, PayPal)
* Subscription Billing Automation
* Auto Invoice Generation
* Refund Processing System
* Payment Failure Retry Mechanism
* Wallet & Store Credits

---

## 📊 Advanced Analytics & Reports

* Branch Analytics Dashboard
* Store Analytics Dashboard
* Super Admin Analytics
* Real-Time Sales Reports
* Revenue Forecasting
* Product Performance Insights
* Cashier Performance Tracking
* Profit & Loss Reports
* Tax & GST Reports
* Export Reports to PDF / Excel

---

## 🔄 Shift & Refund Management

* Cashier Shift Management
* Shift Opening & Closing Reports
* Live Shift Tracking
* Refund Management System
* Refund Approval Workflow
* Shift-Based Revenue Analytics

---

## 🏪 Multi-Store & Enterprise Features

* Multi-Warehouse Support
* Franchise Management
* Region-Based Access Control
* Cross-Branch Inventory Transfer
* Centralized Inventory Management
* Branch Performance Ranking

---

## 📦 Inventory Improvements

* Low Stock Alerts
* Automatic Restock Suggestions
* Barcode Scanner Support
* QR Code Product Support
* Batch & Expiry Management
* Supplier & Purchase Management

---

## 🛒 eCommerce & Integrations

* eCommerce Store Integration
* Shopify Integration
* WooCommerce Integration
* Third-Party API Integrations
* Marketplace Synchronization
* Online Order Management

---

## 📧 Communication & Notification System

* Email Service Integration
* SMS Notification Service
* WhatsApp Notifications
* Order Confirmation Emails
* Low Stock Email Alerts
* Subscription Expiry Notifications
* Refund Notifications

---

## 👨‍💼 Employee & Customer Enhancements

* Employee Attendance Tracking
* Employee Salary Management
* Customer Loyalty Program
* Reward Points System
* Customer Purchase History Analytics
* Membership & VIP Customers

---

## 🧾 Invoice & Printing

* Custom Invoice Branding
* Thermal Printer Support
* Invoice PDF Generation
* GST Invoice Support
* Receipt Printing System

---

## ☁️ Deployment & Scalability

* Docker Support
* Kubernetes Deployment
* CI/CD Pipeline
* AWS / Azure / GCP Deployment
* Redis Caching
* API Rate Limiting
* Microservices Architecture

---

## 🔐 Security Improvements

* Two Factor Authentication (2FA)
* OAuth2 Login
* Device Session Tracking
* Login Activity Monitoring
* Advanced Permission System
* Audit Logs

---

## 📱 Mobile & Offline Support

* Mobile POS Application
* Tablet POS Interface
* Offline Mode Support
* Sync After Reconnection
* Progressive Web App (PWA)

---

## 🤖 AI & Automation

* AI-Based Sales Predictions
* Smart Product Recommendations
* Automated Inventory Forecasting
* AI Customer Insights
* Chatbot Support System

---

# 🚀 Upcoming Modules

| Module                  | Description                                                   |
| ----------------------- | ------------------------------------------------------------- |
| Refund Management       | Handle order refunds with payment tracking and refund history |
| Shift Report System     | Track cashier shifts, sales, refunds, and shift summaries     |
| Admin Dashboard         | Super admin analytics and platform overview                   |
| Branch Analytics        | Branch-level sales and performance analytics                  |
| Store Analytics         | Store-wide reporting and KPI monitoring                       |
| Subscription Plans      | SaaS subscription plans with feature limits                   |
| Subscription Management | Store subscription lifecycle management                       |
| Payment Service         | Online payment handling and transaction management            |
| Email Service           | Email notifications, OTPs, invoices, and alerts               |

---

# 🧪 API Testing

You can test all APIs using:

* Postman
* Thunder Client
* Insomnia

---


# 👨‍💻 Developer


Built with ❤️ by [Md Tarikul Hassan](https://github.com/mdtarikul-hassan)

🔗 GitHub Repository: https://github.com/mdtarikul-hassan/bajarlink

using **Java + Spring Boot** for scalable POS & inventory management solutions.