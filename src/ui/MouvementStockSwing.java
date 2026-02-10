package ui;

import DAO.MouvementDAO;
import DAO.ProduitDAO;

import javax.swing.*;
import java.util.List;

public class MouvementStockSwing extends JFrame {
    private JComboBox<String> produitCombo;
    private JTextField qteField;

    public MouvementStockSwing() {
        setTitle("Entrée / Sortie de Stock");
        setSize(420, 250);
        setLayout(null);
        setLocationRelativeTo(null); // Centrer la fenêtre

        initUI();
        chargerProduits();
        setVisible(true);
    }

    private void initUI() {
        JLabel produitLabel = new JLabel("Produit :");
        produitCombo = new JComboBox<>();
        JLabel qteLabel = new JLabel("Quantité :");
        qteField = new JTextField();

        JButton entreeBtn = new JButton("Entrée");
        JButton sortieBtn = new JButton("Sortie");

        produitLabel.setBounds(30, 30, 100, 25);
        produitCombo.setBounds(130, 30, 220, 25);
        qteLabel.setBounds(30, 70, 100, 25);
        qteField.setBounds(130, 70, 220, 25);
        entreeBtn.setBounds(90, 130, 100, 30);
        sortieBtn.setBounds(210, 130, 100, 30);

        add(produitLabel); add(produitCombo);
        add(qteLabel); add(qteField);
        add(entreeBtn); add(sortieBtn);

        entreeBtn.addActionListener(e -> enregistrerMouvement("ENTREE"));
        sortieBtn.addActionListener(e -> enregistrerMouvement("SORTIE"));
    }

    private void chargerProduits() {
        List<String[]> produits = new ProduitDAO().getTousLesProduits();
        produitCombo.removeAllItems();

        if (produits.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun produit disponible.");
            dispose();
            return;
        }

        for (String[] p : produits) {
            produitCombo.addItem(p[0] + " - " + p[1]); // "id - nom"
        }
    }

    private void enregistrerMouvement(String type) {
        try {
            String selection = (String) produitCombo.getSelectedItem();
            if (selection == null || qteField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un produit et une quantité.");
                return;
            }

            int produitId = Integer.parseInt(selection.split(" - ")[0]);
            int quantite = Integer.parseInt(qteField.getText().trim());

            if (quantite <= 0) {
                JOptionPane.showMessageDialog(this, "Quantité invalide.");
                return;
            }

            // Vérification du stock si sortie
            if (type.equals("SORTIE")) {
                List<String[]> produits = new ProduitDAO().getTousLesProduits();
                for (String[] p : produits) {
                    if (Integer.parseInt(p[0]) == produitId) {
                        int stockActuel = Integer.parseInt(p[2]);
                        if (quantite > stockActuel) {
                            JOptionPane.showMessageDialog(this,
                                    "Stock insuffisant. Quantité disponible : " + stockActuel,
                                    "Erreur", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
            }

            new MouvementDAO().enregistrerMouvement(produitId, type, quantite);
            JOptionPane.showMessageDialog(this, "Mouvement enregistré !");
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantité invalide : doit être un nombre.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
        }
    }
}
