# Stock Management Application – Java Swing

## Description

This application is a stock management system developed in Java using a Swing graphical user interface and a layered architecture based on the DAO (Data Access Object) pattern.

It allows users to manage products, stock movements, operation history, and generate reports using JasperReports. All data is stored in a MySQL database.

---

## Project Objectives

- Practice object-oriented programming in Java
- Implement the DAO architectural pattern
- Connect a Java application to a MySQL database using JDBC
- Generate professional reports using JasperReports
- Design a complete graphical user interface with Java Swing

---

## Features

- Add a product
- Update a product
- Delete a product
- Display the list of products
- Manage stock entries and exits
- View stock movement history
- Display statistics
- Generate stock reports in PDF format

---

## Technologies Used

- Java
- Java Swing
- MySQL
- JDBC (mysql-connector)
- JasperReports
- DAO Architecture

---

## Project Structure

```text
Projet_java/
├── src/
│   ├── DAO/
│   │   ├── ProduitDAO.java
│   │   └── MouvementDAO.java
│   ├── ui/
│   │   ├── MenuPrincipal.java
│   │   ├── AjouterProduitSwing.java
│   │   ├── AfficherProduitsSwing.java
│   │   ├── ModifierOuSupprimerProduit.java
│   │   ├── MouvementStockSwing.java
│   │   ├── HistoriqueMouvementsSwing.java
│   │   └── FenetreStatistiques.java
│   ├── utils/
│   │   └── DatabaseConnection.java
│   └── reports/
│       └── rapport_stock.jrxml
├── Jasper/
└── out/
```

---

## Installation and Configuration

### 1. Prerequisites

- JDK 8 or higher
- MySQL Server
- A Java IDE (IntelliJ IDEA, Eclipse, NetBeans, etc.)

### 2. Database Setup

Run the following SQL commands:

```sql
CREATE DATABASE gestion_stock;

CREATE TABLE produit (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100),
    quantite INT,
    prix DOUBLE
);

CREATE TABLE mouvement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    produit_id INT,
    type VARCHAR(50),
    quantite INT,
    date_mouvement TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (produit_id) REFERENCES produit(id)
);
```

### 3. Configure Database Connection

Edit the file:

src/utils/DatabaseConnection.java

Update your database credentials:

```java
String url = "jdbc:mysql://localhost:3306/gestion_stock";
String user = "root";
String password = "your_password";
```

### 4. Run the Application

Execute:

MenuPrincipal.java

This is the entry point of the application.

---

## Report Generation

The file:

src/reports/rapport_stock.jrxml

is used to generate stock reports using JasperReports. Reports can be exported in PDF format.

---

## Architecture

The project follows the DAO pattern:

- DAO: Handles database access
- UI: Manages the graphical interface
- Utils: Handles database connection
- Reports: Manages report generation

This structure ensures clear separation of responsibilities and easier maintenance.

---

## Author

Name: Aicha 
Type: Academic / Personal Project  
Year: 2024  

---

## License

This project is for educational purposes only.
