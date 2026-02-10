package utils;

import java.sql.*;

public class DatabaseConnection {
    public static Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/stockdb";
            String user = "java_user";
            String password = "base1234567"; // Remplace par ton mot de passe
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
            return null;
        }
    }
}
