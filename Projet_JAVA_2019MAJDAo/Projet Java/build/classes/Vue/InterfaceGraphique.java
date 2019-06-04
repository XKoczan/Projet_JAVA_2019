
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Exceptions.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import static java.lang.Integer.parseInt;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import projet.java.*;

/**
 *
 * @author xavie
 */
/**
 * Classe InterfaceGraphique
 *
 * @author xavie
 */
public class InterfaceGraphique {

    //Attributs
    JFrame frame_accueil = new JFrame("Accueil");
    JFrame interface_eleve;
    JButton button_eleve = new JButton("Fiche Eleve");
    Statement stmt;

    /**
     * Constructeur
     */
    public InterfaceGraphique() {
        // On crée la fenetre principale
        creer_fenetre(frame_accueil, 500, 500);
        // On lui associe une GridLayout 
        frame_accueil.setLayout(new GridLayout(3, 1));
        // On créera un ActionListener pour le bouton élève 
        button_eleve.addActionListener((ActionEvent e) -> {
            // On affiche une trace
            System.out.println("On rentre dans le bouton Eleve");

            // On crée la fenetre 
            fenetre_eleve();
        });
        // On set quoi faire une fois qu'on quitte la fenetre
        frame_accueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // On ajoute le bouton
        frame_accueil.add(button_eleve);

    }

    /**
     * Création de fenetre
     *
     * @param f
     * @param x
     * @param y
     */
    public final void creer_fenetre(JFrame f, int x, int y) {

        // On set la taille de la fenetre avec les valeurs en parametre
        f.setSize(x, y);
        // On centre a fenetre
        f.setLocationRelativeTo(null);
        // On l'affiche 
        f.setVisible(true);

    }

    public void fenetre_eleve() {

        DAO_Eleve dao_eleve = new DAO_Eleve();
        interface_eleve = new JFrame("InterfaceEleve");
        creer_fenetre(interface_eleve, 500, 200);
        interface_eleve.setLayout(new GridLayout(1, 2));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        JTable table = new JTable(dao_eleve.getCollection_eleve().size() + 1, 4);

        table.setValueAt("ID", 0, 0);
        table.setValueAt("Nom", 0, 1);
        table.setValueAt("Prenom", 0, 2);
        table.setValueAt("Type", 0, 3);

        int ligne = 1;

        for (Eleve e : dao_eleve.getCollection_eleve()) {
            System.out.println(e.getId() + ":" + e.getNom() + ":" + e.getPrenom() + ":" + e.getType());
            table.setValueAt(e.getId(), ligne, 0);
            table.setValueAt(e.getNom(), ligne, 1);
            table.setValueAt(e.getPrenom(), ligne, 2);
            table.setValueAt("etu", ligne, 3);
            ligne++;
        }

        JButton ajouter = new JButton("Ajouter un eleve");
        JButton supprimer = new JButton("Supprimer");
        JButton modifier = new JButton("Sauvegarder les changements");

        ajouter.addActionListener((ActionEvent e) -> {

            Fenetre f = new Fenetre();
            JTextField t1 = new JTextField("Nom", 10);
            JTextField t2 = new JTextField("Prenom", 10);
            JTextField t3 = new JTextField("Classe", 10);

            JButton conf = new JButton("Confirmer");
            conf.addActionListener((ActionEvent evt) -> {
                System.out.println(parseInt(t3.getText()));
                try {
                    dao_eleve.rechercher_eleve(new Eleve(dao_eleve.getCollection_eleve().size(), t1.getText(), t2.getText()));
                    Error err = new Error("L'element existe déjà");
                } catch (NonExistingElement aee) {
                    dao_eleve.creer(new Eleve(dao_eleve.getCollection_eleve().size(), t1.getText(), t2.getText()),parseInt(t3.getText()));

                    f.getFrame().dispose();
                    interface_eleve.dispose();
                    fenetre_eleve();
                }

            });

            f.getPanel().add(t1);
            f.getPanel().add(t2);
            f.getPanel().add(t3);
            f.getFrame().add(f.getPanel());
            f.getFrame().add(conf);

        });

        supprimer.addActionListener((ActionEvent e) -> {

            try {

                dao_eleve.supprimer(dao_eleve.getCollection_eleve().get(table.getSelectedRow() - 1));
                interface_eleve.dispose();
                fenetre_eleve();
            } catch (ArrayIndexOutOfBoundsException ai) {
                Error err = new Error("Veuillez sélectrionner un élément dans le tableau ");
            }

        });

        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e1) -> {
            System.out.println(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
            table.setValueAt(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString(), table.getSelectedRow(), table.getSelectedColumn());
        });

        modifier.addActionListener((ActionEvent evt) -> {
            int size = dao_eleve.getCollection_eleve().size() + 1;
            dao_eleve.getCollection_eleve().clear();
            System.out.println(dao_eleve.getCollection_eleve().size());
            for (int i = 1; i < size; i++) {
                dao_eleve.modifier(new Eleve((int) table.getValueAt(i, 0), (String) table.getValueAt(i, 1), (String) table.getValueAt(i, 2)));
            }
            interface_eleve.dispose();
            fenetre_eleve();
        });

        panel.add(ajouter);
        panel.add(supprimer);
        panel.add(modifier);
        interface_eleve.add(table);
        interface_eleve.add(panel);
    }

    public static void main(String[] argv) {
        InterfaceGraphique IG = new InterfaceGraphique();
    }
}
