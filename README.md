# Stock Management Application – Java Swing

## Description

This application is a stock management system developed in Java with a Swing graphical user interface and a layered architecture based on the DAO (Data Access Object) pattern.

It allows management of products, stock movements, operation history, and report generation using JasperReports.
Data is stored in a MySQL database.

---

## Project Objectives

- Practice object-oriented programming in Java
- Implement the DAO architecture pattern
- Connect a Java application to a MySQL database using JDBC
- Generate reports using JasperReports
- Design a complete graphical user interface with Java Swing

---

## Features

- Add a product
- Update a product
- Delete a product
- Display the list of products
- Manage stock entries and exits
- View movement history
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
````markdown
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

---

## Installation and Configuration

### 1. Prerequisites

- JDK 8 or higher
- MySQL Server
- A Java IDE (IntelliJ IDEA, Eclipse, NetBeans, etc.)

### 2. Database Creation

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

### 3. Database Connection Configuration

Modify the file src/utils/DatabaseConnection.java:

String url = "jdbc:mysql://localhost:3306/gestion_stock";
String user = "root";
String password = "your_password";

### 4. Running the Application

Run the MenuPrincipal.java class.

---

## Report Generation

The file rapport_stock.jrxml located in the reports folder is used to generate stock reports using JasperReports.
Reports can be exported in PDF format.

---

## Architecture

The project follows the DAO pattern:

- DAO: Data access management
- UI: User interface
- Utils: Database connection management
- Reports: Report generation

This structure ensures clear separation of responsibilities and easier project maintenance.

---

## Author

Name: Your Name  
Type: Academic / Personal Project  
Year: 2024  

---

## License

Educational use only.
