package ui;

import DAO.ProduitDAO;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class FenetreStatistiques extends JFrame {

    public FenetreStatistiques() {
        setTitle("Statistiques du Stock");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        Map<String, Double> stats = new ProduitDAO().calculerStatistiques();

        JLabel totalProduits = new JLabel("Total des produits : " + stats.get("total_produits").intValue());
        JLabel totalQuantite = new JLabel("Quantité totale : " + stats.get("total_quantite").intValue());
        JLabel moyennePrix = new JLabel("Prix moyen : " + String.format("%.2f", stats.get("moyenne_prix")) + " $");
        JLabel valeurStock = new JLabel("Valeur totale du stock : " + String.format("%.2f", stats.get("valeur_stock")) + " $");

        totalProduits.setHorizontalAlignment(SwingConstants.CENTER);
        totalQuantite.setHorizontalAlignment(SwingConstants.CENTER);
        moyennePrix.setHorizontalAlignment(SwingConstants.CENTER);
        valeurStock.setHorizontalAlignment(SwingConstants.CENTER);

        add(totalProduits);
        add(totalQuantite);
        add(moyennePrix);
        add(valeurStock);

        JButton fermerBtn = new JButton("Fermer");
        fermerBtn.addActionListener(e -> dispose());
        add(fermerBtn);

        setVisible(true);
    }
}
