package ui;

import DAO.MouvementDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class HistoriqueMouvementsSwing extends JFrame {
    public HistoriqueMouvementsSwing() {
        setTitle("Historique des mouvements");
        setSize(600, 400);
        setLayout(null);

        String[] colonnes = {"ID", "Produit", "Type", "Quantité", "Date"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 20, 550, 300);
        add(scroll);

        List<String[]> mouvements = new MouvementDAO().getHistoriqueMouvements();
        for (String[] ligne : mouvements) {
            model.addRow(ligne);
        }

        setVisible(true);
    }
}
