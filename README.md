## Citronix  
Farm Management System for Lemon Farms  

📖 **Project Overview**  
Citronix is a comprehensive farm management system designed for lemon farms. It enables farmers to efficiently manage farms, fields, trees, harvests, and sales, while tracking productivity based on tree age and optimizing resource usage.

## 🧩 Core Functionalities  

1. **Farm Management**  
   - Create, modify, and view farm information (name, location, size, creation date).  
   - Perform multicriteria search using `CriteriaBuilder`.  

2. **Field Management**  
   - Associate fields with farms and define field sizes.  
   - Enforce size constraints:  
     - The total field area must be strictly less than the farm area.  

3. **Tree Management**  
   - Track trees by planting date, age, and field association.  
   - Calculate tree age dynamically.  
   - Determine annual productivity based on age:  
     - **Young Tree (< 3 years):** 2.5 kg/season.  
     - **Mature Tree (3–10 years):** 12 kg/season.  
     - **Old Tree (> 10 years):** 20 kg/season.  

4. **Harvest Management**  
   - Manage seasonal harvests (winter, spring, summer, autumn).  
   - Restrict to one harvest per season per field.  
   - Record harvest date and total quantity.  

5. **Harvest Details**  
   - Track harvest quantity per tree for each harvest.  
   - Associate harvest details with specific trees.  

6. **Sales Management**  
   - Log sales information (date, price, client, associated harvest).  
   - Calculate revenue:  
     - **Revenue = Quantity × Unit Price**.  

## 🔐 Constraints  

1. **Field Size**  
   - Minimum size: **0.1 hectares**.  
   - Maximum size: **50% of the total farm size**.  

2. **Tree Density**  
   - Maximum of **100 trees per hectare**.  

3. **Tree Life Cycle**  
   - Trees are productive up to **20 years** only.  

4. **Planting Season**  
   - Trees can only be planted between **March and May**.  

5. **Harvest Restrictions**  
   - Only **one harvest per field per season** is allowed.  
   - Trees cannot be included in multiple harvests during the **same season**.

## 💻 Technical Details

### 1. **Framework & Tools**  
   - **Spring Boot**: Used for REST API development.  
   - **Layered Architecture**:  
     - **Controller**: Manages HTTP requests and responses.  
     - **Service**: Business logic and interaction with repositories.  
     - **Repository**: Data access layer, responsible for CRUD operations.  
     - **Entity**: Core objects representing data.  
   - **Data Validation**: Leveraging **Spring annotations** (e.g., `@NotNull`, `@Min`, `@Max`, `@Size`) to ensure proper input validation.  
   - **Centralized Exception Handling**: Managed via `@ControllerAdvice` for uniform error handling.

### 2. **Design Patterns**  
   - **Builder Pattern**: Implemented using **Lombok** to create fluent and immutable entity objects with optional parameters.  
   - **MapStruct**: Used for converting between **DTOs** and **entities** to simplify data transfer and mapping.

### 3. **Testing**  
   - **JUnit**: Used for unit testing individual components and methods.  
   - **Mockito**: Employed for mocking dependencies and isolating units during testing.

## 📁 Project Structure

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com.wora.citronix/
│   │       ├── configs/
│   │       ├── controllers/
│   │       ├── exceptions/
│   │       ├── helps/
│   │       ├── mappers/
│   │       ├── models/
│   │       │   ├── DTOs/
│   │       │   ├── entites/
│   │       │   └── enumes/
│   │       ├── repositories/
│   │       ├── services/
│   │       └── CitronixApplication.java
│   └── resources/
│       ├── application.properties
└── test/
    └── java/
        └── com.wora.citronix/
            └── services.impl
            └── CitronixApplicationTests.java
```

## 🚀 API Endpoints

### **Farm API**
- **POST /farms**: Create a new farm.
- **GET /farms**: Retrieve a paginated list of all farms.
- **GET /farm/{id}**: Retrieve a specific farm by its ID.
- **PUT /farm/{id}**: Update farm details.
- **DELETE /farm/{id}**: Delete a farm.
- **GET /search/farms**: Search farms by various criteria such as name, location, size, or creation date.

### **Field API**
- **POST /fields**: Add a field to a farm.
- **GET /fields**: List all fields with pagination.
- **GET /field/{id}**: Retrieve details of a field by its ID.
- **PUT /field/{id}**: Update details of a field.
- **DELETE /field/{id}**: Remove a field.

### **Tree API**
- **POST /trees**: Add a new tree to the system.
- **GET /tree/{id}**: Retrieve details of a tree by its ID.
- **GET /trees**: List all trees with pagination.
- **PUT /tree/{id}**: Update details of a tree.
- **DELETE /tree/{id}**: Delete a tree from the system.

### **Harvest API**
- **POST /harvests**: Create a new harvest entry.
- **GET /harvests**: List all harvests with pagination.
- **GET /harvest/{id}**: Retrieve details of a specific harvest by ID.
- **PUT /harvest/{id}**: Update harvest details.
- **DELETE /harvest/{id}**: Remove a harvest entry.

### **Harvest Details API**
- **POST /api/v1/harvest/details**: Add harvest details for a specific tree.
- **GET /api/v1/harvest/details**: List all harvest details.
- **GET /harvest/{harvestId}/tree/{treeId}**: Retrieve harvest details for a specific tree in a given harvest.
- **PUT /harvest/{harvestId}/tree/{treeId}**: Update harvest details for a specific tree.
- **DELETE /harvest/{harvestId}/tree/{treeId}**: Delete harvest details for a specific tree.

### **Sales API**
- **POST /sales**: Log a sale with details.
- **GET /sales**: List all sales with pagination.
- **GET /sale/{id}**: Retrieve details of a specific sale by ID.
- **PUT /sale/{id}**: Update sale details.
- **DELETE /sale/{id}**: Remove a sale entry.

## 🛠 Setup & Installation

1. **Clone the repository**:
```bash
   git clone https://github.com/your-repo/citronix.git
```
2. **Build the project**:
```bash
  mvn clean install
```
3. **Run the application**:
```bash
   mvn spring-boot:run
```
4. **API Documentation:**:
```bash
   http://localhost:8080/swagger-ui.html
```

## 📐 Class Diagram:

![CITRONIX UML](https://github.com/hamzalamin/Citronix/blob/main/src/main/java/UML/CITRONIX.png)



