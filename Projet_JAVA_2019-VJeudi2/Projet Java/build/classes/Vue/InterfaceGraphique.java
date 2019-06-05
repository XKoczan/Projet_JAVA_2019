
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Exceptions.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import static java.lang.Integer.parseInt;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    int trimestre = 0;
    JButton button_eleve = new JButton("Fiche Eleve");
    Statement stmt;

    JFrame interface_professeur;
    JButton button_professeur = new JButton("Fiche Professeur");
    
    
    JFrame interface_discipline;
    JButton button_discipline = new JButton("Fiche Discipline");
   

    /**
     * Constructeur
     */
    public InterfaceGraphique() {
        jmenuB();
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
        button_professeur.addActionListener((ActionEvent e) -> {
            // On affiche une trace
            System.out.println("On rentre dans le bouton Professeur");

            // On crée la fenetre 
            fenetre_professeur();

        });
        button_discipline.addActionListener((ActionEvent e) -> {
            // On affiche une trace
            System.out.println("On rentre dans le bouton Discipline");

            // On crée la fenetre 
            fenetre_discipline();

        });
        // On set quoi faire une fois qu'on quitte la fenetre
        frame_accueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // On ajoute le bouton
        frame_accueil.add(button_eleve);
        frame_accueil.add(button_professeur);
        frame_accueil.add(button_discipline);

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

        for (Personne e : dao_eleve.getCollection_eleve()) {
            System.out.println(e.getId() + ":" + e.getNom() + ":" + e.getPrenom() + ":" + e.getType());
            table.setValueAt(e.getId(), ligne, 0);
            table.setValueAt(e.getNom(), ligne, 1);
            table.setValueAt(e.getPrenom(), ligne, 2);
            table.setValueAt(e.getType(), ligne, 3);
            ligne++;
        }

        JButton ajouter = new JButton("Ajouter un eleve");
        JButton supprimer = new JButton("Supprimer");
        JButton modifier = new JButton("Sauvegarder les changements");

        ajouter.addActionListener((ActionEvent e) -> {

            try {

                Fenetre f = new Fenetre();
                JTextField t1 = new JTextField("Nom", 10);
                JTextField t2 = new JTextField("Prenom", 10);
                //Combobox pour la classe
                //https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html

                Connection conn = null;
                String url = "jdbc:mysql://localhost/";
                String dbName = "gestion_ecole";
                String driver = "com.mysql.jdbc.Driver";
                String userName = "root";
                String password = "";
                try {
                    Class.forName(driver);
                    conn = DriverManager.getConnection(url + dbName, userName, password);
                    System.out.println("Bien connecte");

                } catch (Exception eg) {
                    System.out.println("probleme de connexion a la BDD");
                }
                JComboBox t3 = new JComboBox();
                PreparedStatement stmt = conn.prepareStatement("SELECT classe_id FROM tab_classe ");
                ResultSet Rs = stmt.executeQuery();
                while (Rs.next()) {

                    //Pour affecter une valeur de base de données à un Combobox
                    t3.addItem(Rs.getString("classe_id"));
                } //Fin de la comboBox

                t3.setSelectedIndex(2);

                JButton conf = new JButton("Confirmer");
                conf.addActionListener((ActionEvent evt) -> {
                    System.out.println(parseInt((String) t3.getSelectedItem()));
                    try {
                        dao_eleve.rechercher_eleve(new Eleve(dao_eleve.getCollection_eleve().size(), t1.getText(), t2.getText()));
                        Error err = new Error("L'element existe déjà");
                    } catch (NonExistingElement aee) {

                        dao_eleve.creer(new Eleve(dao_eleve.getCollection_eleve().size(), t1.getText(), t2.getText()), parseInt((String) t3.getSelectedItem()));

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

            } catch (SQLException ex) {
                Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }

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

    public void fenetre_professeur() {

        DAO_Professeur dao_professeur = new DAO_Professeur();
        interface_professeur = new JFrame("InterfaceProfesseur");
        creer_fenetre(interface_professeur, 500, 200);
        interface_professeur.setLayout(new GridLayout(1, 2));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        JTable table = new JTable(dao_professeur.getCollection_professeur().size() + 1, 4);

        table.setValueAt("ID", 0, 0);
        table.setValueAt("Nom", 0, 1);
        table.setValueAt("Prenom", 0, 2);
        table.setValueAt("Type", 0, 3);

        int ligne = 1;

        for (Professeur e : dao_professeur.getCollection_professeur()) {
            System.out.println(e.getId() + ":" + e.getNom() + ":" + e.getPrenom() + ":" + e.getType());
            table.setValueAt(e.getId(), ligne, 0);
            table.setValueAt(e.getNom(), ligne, 1);
            table.setValueAt(e.getPrenom(), ligne, 2);
            table.setValueAt(e.getType(), ligne, 3);
            ligne++;
        }

        JButton ajouter = new JButton("Ajouter un professeur");
        JButton supprimer = new JButton("Supprimer");
        JButton modifier = new JButton("Sauvegarder les changements");

        ajouter.addActionListener((ActionEvent e) -> {

            Fenetre f = new Fenetre();

            JTextField t1 = new JTextField("Nom", 10);
            JTextField t2 = new JTextField("Prenom", 10);
            JTextField t3 = new JTextField("Classe", 10);
            JTextField t4 = new JTextField("Discipline", 10);

            JButton conf = new JButton("Confirmer");
            conf.addActionListener((ActionEvent evt) -> {
                System.out.println(parseInt(t3.getText()));
                try {
                    dao_professeur.rechercher_professeur(new Professeur(dao_professeur.getCollection_professeur().size(), t1.getText(), t2.getText()));
                    Error err = new Error("L'element existe déjà");
                } catch (NonExistingElement aee) {
                    dao_professeur.creer(new Professeur(dao_professeur.getCollection_professeur().size(), t1.getText(), t2.getText()), parseInt(t3.getText()), parseInt(t4.getText()));

                    f.getFrame().dispose();
                    interface_professeur.dispose();
                    fenetre_professeur();
                }

            });

            f.getPanel().add(t1);
            f.getPanel().add(t2);
            f.getPanel().add(t3);
            f.getPanel().add(t4);
            f.getFrame().setSize(275, 300);
            f.getFrame().add(f.getPanel());
            f.getFrame().add(conf);

        });

        supprimer.addActionListener((ActionEvent e) -> {

            try {

                dao_professeur.supprimer(dao_professeur.getCollection_professeur().get(table.getSelectedRow() - 1));
                interface_professeur.dispose();
                fenetre_professeur();
            } catch (ArrayIndexOutOfBoundsException ai) {
                Error err = new Error("Veuillez sélectrionner un élément dans le tableau ");
            }

        });

        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e1) -> {
            System.out.println(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
            table.setValueAt(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString(), table.getSelectedRow(), table.getSelectedColumn());
        });

        modifier.addActionListener((ActionEvent evt) -> {
            int size = dao_professeur.getCollection_professeur().size() + 1;
            dao_professeur.getCollection_professeur().clear();
            System.out.println(dao_professeur.getCollection_professeur().size());
            for (int i = 1; i < size; i++) {
                dao_professeur.modifier(new Professeur((int) table.getValueAt(i, 0), (String) table.getValueAt(i, 1), (String) table.getValueAt(i, 2)));
            }
            interface_professeur.dispose();
            fenetre_professeur();
        });

        panel.add(ajouter);
        panel.add(supprimer);
        panel.add(modifier);
        interface_professeur.add(table);
        interface_professeur.add(panel);
    }

    public void fenetre_discipline() {

        DAO_Discipline dao_discipline = new DAO_Discipline();
        interface_discipline = new JFrame("InterfaceDiscipline");
        creer_fenetre(interface_discipline, 500, 200);
        interface_discipline.setLayout(new GridLayout(1, 2));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        JTable table = new JTable(dao_discipline.getCollection_discipline().size() + 1, 2);

        table.setValueAt("ID", 0, 0);
        table.setValueAt("Nom", 0, 1);

        int ligne = 1;

        for (Discipline e : dao_discipline.getCollection_discipline()) {
            System.out.println(e.getId() + ":" + e.getNom() );
            table.setValueAt(e.getId(), ligne, 0);
            table.setValueAt(e.getNom(), ligne, 1);
            ligne++;
        }

        JButton ajouter = new JButton("Ajouter une discipline");
        JButton supprimer = new JButton("Supprimer");
        JButton modifier = new JButton("Sauvegarder les changements");

        ajouter.addActionListener((ActionEvent e) -> {

            Fenetre f = new Fenetre();
            JTextField t2 = new JTextField("Matiere", 10);
            JButton conf = new JButton("Confirmer");
            conf.addActionListener((ActionEvent evt) -> {
//                    System.out.println(parseInt((String) t2.getText()));
try {
    dao_discipline.rechercher_discipline(new Discipline(t2.getText()));
    Error err = new Error("L'element existe déjà");
} catch (NonExistingElement aee) {
    
    dao_discipline.creer(new Discipline(t2.getText()),0);
    
    f.getFrame().dispose();
    interface_discipline.dispose();
    fenetre_discipline();
}

            });
            f.getPanel().add(t2);
            f.getFrame().add(f.getPanel());
            f.getFrame().add(conf);

        });

        supprimer.addActionListener((ActionEvent e) -> {

            try {

                dao_discipline.supprimer(dao_discipline.getCollection_discipline().get(table.getSelectedRow() - 1));
                interface_discipline.dispose();
                fenetre_discipline();
            } catch (ArrayIndexOutOfBoundsException ai) {
                Error err = new Error("Veuillez sélectrionner un élément dans le tableau ");
            }

        });

        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e1) -> {
            System.out.println(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
            table.setValueAt(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString(), table.getSelectedRow(), table.getSelectedColumn());
        });

        modifier.addActionListener((ActionEvent evt) -> {
            int size = dao_discipline.getCollection_discipline().size() + 1;
            dao_discipline.getCollection_discipline().clear();
            System.out.println(dao_discipline.getCollection_discipline().size());
            for (int i = 1; i < size; i++) {
                dao_discipline.modifier(new Discipline((String) table.getValueAt(i, 1)));
            }
            interface_discipline.dispose();
            fenetre_discipline();
        });

        panel.add(ajouter);
        panel.add(supprimer);
        panel.add(modifier);
        interface_discipline.add(table);
        interface_discipline.add(panel);
    }

    public void jmenuB() {
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Trimestre");
        JMenuItem mi = new JMenuItem("Trimestre1");
        mi.addActionListener((ActionEvent e) -> {
            trimestre = 1;
        });
        JMenuItem mi2 = new JMenuItem("Trimestre2");
        mi2.addActionListener((ActionEvent e) -> {
            trimestre = 2;
        });
        JMenuItem mi3 = new JMenuItem("Trimestre3");
        mi3.addActionListener((ActionEvent e) -> {
            trimestre = 3;
        });
        mi.setBackground(Color.magenta.darker());
        mi2.setBackground(Color.yellow.darker());
        mi3.setBackground(Color.yellow.darker());

        menu.add(mi);
        menu.add(mi2);
        menu.add(mi3);

        mb.add(menu);

        frame_accueil.setJMenuBar(mb);
    }
}
