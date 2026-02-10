package DAO;

import utils.DatabaseConnection;

import java.sql.*;
import java.util.*;

public class ProduitDAO {

    public void ajouterProduit(String nom, int quantite, double prix) {
        String sql = "INSERT INTO produits (nom, quantite, prix) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.setInt(2, quantite);
            stmt.setDouble(3, prix);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getTousLesProduits() {
        List<String[]> liste = new ArrayList<>();
        String sql = "SELECT * FROM produits";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                liste.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("nom"),
                        String.valueOf(rs.getInt("quantite")),
                        String.valueOf(rs.getDouble("prix"))
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public void modifierProduit(int id, String nom, int quantite, double prix) {
        String sql = "UPDATE produits SET nom=?, quantite=?, prix=? WHERE id=?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.setInt(2, quantite);
            stmt.setDouble(3, prix);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerProduit(int id) {
        String sql = "DELETE FROM produits WHERE id=?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Nouvelle méthode : calcul des statistiques simples
    public Map<String, Double> calculerStatistiques() {
        Map<String, Double> stats = new HashMap<>();
        String sql = "SELECT COUNT(*) AS total_produits, SUM(quantite) AS total_quantite, " +
                "AVG(prix) AS moyenne_prix, SUM(quantite * prix) AS valeur_stock FROM produits";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                stats.put("total_produits", rs.getDouble("total_produits"));
                stats.put("total_quantite", rs.getDouble("total_quantite"));
                stats.put("moyenne_prix", rs.getDouble("moyenne_prix"));
                stats.put("valeur_stock", rs.getDouble("valeur_stock"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stats;
    }
}
