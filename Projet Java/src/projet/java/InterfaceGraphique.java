
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

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
    JFrame interface_eleve = new JFrame("InterfaceEleve");
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
            // On crée un objet DAO_Eleve
            DAO_Eleve dao_eleve = new DAO_Eleve();
            // On crée la fenetre 
            fenetre_eleve(dao_eleve);
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

    public void fenetre_eleve(DAO_Eleve dao_eleve) {
        creer_fenetre(interface_eleve, 500, 500);
        interface_eleve.setLayout(new GridLayout(1, 2));
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        JTable table = new JTable(dao_eleve.collection_eleve.size() + 1, 4);
        
        table.setValueAt("ID", 0, 0);
        table.setValueAt("Nom", 0, 1);
        table.setValueAt("Prenom", 0, 2);
        table.setValueAt("Type", 0, 3);
        int ligne = 1;

        for (Eleve e : dao_eleve.collection_eleve) {
            System.out.println(e.id + ":" + e.nom + ":" + e.prenom + ":" + e.type);
            table.setValueAt(e.getId(), ligne, 0);
            table.setValueAt(e.getNom(), ligne, 1);
            table.setValueAt(e.getPrenom(), ligne, 2);
            table.setValueAt("etu", ligne, 3);
            ligne++;
        }
        JButton ajouter = new JButton("Ajouter un eleve");
        JButton supprimer = new JButton();
        JButton modifier = new JButton();
         
        ajouter.addActionListener((ActionEvent e)-> {
           Fenetre fenetre = new Fenetre();
           JTextField t1 = new JTextField(10);
           t1.setName("Nom");
           JTextField t2 = new JTextField(10);
           t1.setName("Prenom");
           
           JButton conf= new JButton("Confirmer");
           conf.addActionListener((ActionEvent evt)-> {
               Eleve obj= new Eleve(t1.getText(),t2.getText());
               dao_eleve.creer(obj);
           });
           fenetre.frame.add(t1);
           fenetre.frame.add(t2);
           
        });
        /*supprimer.addActionListener((ActionEvent e)-> {
           dao_eleve.supprimer(obj);
        });
        modifier.addActionListener((ActionEvent e)-> {
           dao_eleve.supprimer(obj);
        });*/
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
