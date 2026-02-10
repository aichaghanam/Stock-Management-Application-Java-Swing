package DAO;

import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MouvementDAO {

    // 🔹 Ajouter un mouvement (ENTREE ou SORTIE) et mettre à jour le stock
    public void enregistrerMouvement(int produitId, String type, int quantite) {
        String sql = "INSERT INTO mouvements (produit_id, type_mouvement, quantite) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, produitId);
            stmt.setString(2, type); // "ENTREE" ou "SORTIE"
            stmt.setInt(3, quantite);
            stmt.executeUpdate();

            // Mise à jour du stock dans la table produits
            String updateSql = type.equalsIgnoreCase("ENTREE") ?
                    "UPDATE produits SET quantite = quantite + ? WHERE id = ?" :
                    "UPDATE produits SET quantite = quantite - ? WHERE id = ?";

            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, quantite);
                updateStmt.setInt(2, produitId);
                updateStmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer l’historique des mouvements
    public List<String[]> getHistoriqueMouvements() {
        List<String[]> liste = new ArrayList<>();
        String sql = "SELECT m.id, p.nom, m.type_mouvement, m.quantite, m.date_mouvement " +
                "FROM mouvements m JOIN produits p ON m.produit_id = p.id " +
                "ORDER BY m.date_mouvement DESC";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                liste.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("nom"),
                        rs.getString("type_mouvement"),
                        String.valueOf(rs.getInt("quantite")),
                        rs.getString("date_mouvement")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }
}
