# Supplier Management API

## Overview

This project is a Spring Boot application that provides RESTful APIs for managing and searching suppliers based on their location, nature of business, and manufacturing processes. The application uses JPA for data persistence with an H2 in-memory database and is equipped with Swagger for API documentation.

## Prerequisites

- **Java 17** or later
- **Maven 3.6.3** or later

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/supplier-management-api.git
cd supplier-management-api
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

### 4. Access the Application
- **[API](http://localhost:8080/api/supplier)**: `http://localhost:8080/api/supplier`
- **[Swagger API Documentation](http://localhost:8080/swagger-ui/index.html)**: `http://localhost:8080/swagger-ui/index.html`
- **[H2 Database Console](http://localhost:8080/h2-console)**: `http://localhost:8080/h2-console`
  - **JDBC URL**: `jdbc:h2:mem:testdb`
  - **Username**: `sa`
  - **Password**: password
 
### API Endpoints
1. **Get List of All Suppliers**

   ```bash
   curl -X GET "http://localhost:8080/api/supplier"
   ```

2. **Query Suppliers by Location, Nature of Business, and Manufacturing Process**
```bash
curl -X POST "http://localhost:8080/api/supplier/query?location=India&natureOfBusiness=SMALL_SCALE&process=THREE_D_PRINTING&page=0&size=10&sort=supplierId,asc"
```

3. **Search Suppliers by Custom Query**
```bash
curl -X POST "http://localhost:8080/api/supplier/search?query=fetch%20the%20list%20of%20manufacturers%20that%20located%20in%20UK%20andisSMALL_scale%20and%20does%20coating"
```
