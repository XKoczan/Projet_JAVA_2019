
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Exceptions.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
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
public final class InterfaceGraphique {

    //Attributs
    JFrame frame_accueil = new JFrame("Accueil");
    JFrame interface_eleve = new JFrame("InterfaceEleve");
    Fenetre f = new Fenetre();
    Fenetre f2 = new Fenetre();
    Connection conn = null;

    Error err = new Error();
    int trimestre = 0;
    JButton button_eleve = new JButton("Fiche Eleve");

    JFrame interface_professeur;
    JButton button_professeur = new JButton("Fiche Professeur");

    JFrame interface_discipline;
    JButton button_discipline = new JButton("Fiche Discipline");
    JMenu menu = new JMenu("");

    JFrame interface_classe;
    JButton button_classe = new JButton("Fiche Classe");

    String matiere = "";
    Note note = new Note();
    private int status;

    /**
     * Constructeur
     */
    public InterfaceGraphique(Connection conn) {
        this.conn = conn;
        // On crée la fenetre principale

        creer_fenetre(frame_accueil, 500, 500);
        // On lui associe une GridLayout 
        frame_accueil.setLayout(new GridLayout(4, 1));
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

        button_classe.addActionListener((ActionEvent e) -> {
            // On affiche une trace
            System.out.println("On rentre dans le bouton Classe");

            // On crée la fenetre 
            fenetre_classe();

        });
        // On set quoi faire une fois qu'on quitte la fenetre
        frame_accueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // On ajoute le bouton
        frame_accueil.add(button_eleve);
        frame_accueil.add(button_professeur);
        frame_accueil.add(button_discipline);
        frame_accueil.add(button_classe);

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

        DAO_Eleve dao_eleve = new DAO_Eleve(conn);
        interface_eleve = new JFrame("InterfaceEleve");
        creer_fenetre(interface_eleve, 500, 200);
        interface_eleve.setLayout(new GridLayout(1, 2));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
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
        JButton consulter = new JButton("Consulter le bulletin de l'élève");

        ajouter.addActionListener((ActionEvent e) -> {
            f.getFrame().dispose();
            f = new Fenetre("AjoutEleve");
            JTextField t1 = new JTextField("Nom", 10);
            JTextField t2 = new JTextField("Prenom", 10);
            JLabel lab = new JLabel("Classe");
            JComboBox t3 = new JComboBox();
            dao_eleve.creer_combobox(t3);
            JButton conf = new JButton("Confirmer");
            conf.addActionListener((ActionEvent evt) -> {
                System.out.println(parseInt((String) t3.getSelectedItem()));
                try {
                    dao_eleve.rechercher_eleve(new Eleve(dao_eleve.getCollection_eleve().size(), t1.getText(), t2.getText()));
                    err.getFrame().dispose();
                    err = new Error("L'element existe déjà");
                } catch (NonExistingElement aee) {

                    dao_eleve.creer(new Eleve(dao_eleve.getCollection_eleve().size(), t1.getText(), t2.getText()), parseInt((String) t3.getSelectedItem()));

                    f.getFrame().dispose();
                    interface_eleve.dispose();
                    fenetre_eleve();
                }

            });
            f.getPanel().setLayout(new BoxLayout(f.getPanel(), BoxLayout.Y_AXIS));
            f.getPanel().add(t1);
            f.getPanel().add(t2);
            JPanel panel2 = new JPanel();
            panel2.add(lab);
            panel2.add(t3);
            f.getPanel().add(panel2);
            f.getFrame().add(f.getPanel());
            f.getFrame().add(conf);

        });

        supprimer.addActionListener((ActionEvent e) -> {

            try {

                dao_eleve.supprimer(dao_eleve.getCollection_eleve().get(table.getSelectedRow() - 1));
                interface_eleve.dispose();
                fenetre_eleve();
            } catch (ArrayIndexOutOfBoundsException ai) {
                err.getFrame().dispose();
                err = new Error("Veuillez sélectrionner un élément dans le tableau ");
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

        consulter.addActionListener((ActionEvent evt) -> {
            System.out.println(trimestre);
            try {
                if (trimestre != 0) {
                    Bulletin bulletin = dao_eleve.consulter_bulletin(dao_eleve.getCollection_eleve().get(table.getSelectedRow() - 1), trimestre);
                    afficher_bulletin(bulletin, dao_eleve);

                    interface_eleve.dispose();
                } else {
                    err.getFrame().dispose();
                    err = new Error("Veuillez selectrionner un trismestre");
                }

            } catch (Exception e) {
                System.out.println("element non renseigné");
                err.getFrame().dispose();
                err = new Error("Veuillez selectrionner un element dans le tableau");
            }

        });

        panel.add(ajouter);
        panel.add(supprimer);
        panel.add(modifier);
        panel.add(consulter);
        interface_eleve.add(table);
        interface_eleve.add(panel);
        jmenuB();
    }

    public void fenetre_professeur() {

        DAO_Professeur dao_professeur = new DAO_Professeur(conn);
        interface_professeur = new JFrame("InterfaceProfesseur");
        creer_fenetre(interface_professeur, 500, 200);
        interface_professeur.setVisible(true);
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

            f = new Fenetre("AjoutProfesseur");

            JTextField t1 = new JTextField("Nom", 10);
            JTextField t2 = new JTextField("Prenom", 10);
            JTextField t3 = new JTextField("Classe", 10);
            JTextField t4 = new JTextField("Discipline", 10);

            JButton conf = new JButton("Confirmer");
            conf.addActionListener((ActionEvent evt) -> {
                try {
                    
                    System.out.println(parseInt(t3.getText()));
                    dao_professeur.rechercher_professeur(new Professeur(dao_professeur.getCollection_professeur().size(), t1.getText(), t2.getText()));
                    err.getFrame().dispose();
                    err = new Error("L'element existe déjà");
                } catch (NonExistingElement aee) {
                    
                    try{
                        dao_professeur.creer(new Professeur(dao_professeur.getCollection_professeur().size(), t1.getText(), t2.getText()), parseInt(t3.getText()), parseInt(t4.getText()));
                    
                    
                    f.getFrame().dispose();
                    interface_professeur.dispose();
                    fenetre_professeur();
                    }catch (Exception nfe) {
                        System.out.println("Error");
                        err.getFrame().dispose();
                        err = new Error("Discipline : l'id renseigné n'existe pas");
                    }
                } catch (Exception nfe) {
                        System.out.println("Error");
                        err.getFrame().dispose();
                        err = new Error("Classe : l'id renseigné n'existe pas");
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
                err.getFrame().dispose();
                err = new Error("Veuillez sélectrionner un élément dans le tableau ");
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

        DAO_Discipline dao_discipline = new DAO_Discipline(conn);
        interface_discipline = new JFrame("InterfaceDiscipline");
        creer_fenetre(interface_discipline, 500, 200);
        interface_discipline.setVisible(false);
        interface_discipline.setLayout(new GridLayout(1, 2));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        JTable table = new JTable(dao_discipline.getCollection_discipline().size() + 1, 2);

        table.setValueAt("ID", 0, 0);
        table.setValueAt("Nom", 0, 1);

        int ligne = 1;

        for (Discipline e : dao_discipline.getCollection_discipline()) {
            System.out.println(e.getId() + ":" + e.getNom());
            table.setValueAt(e.getId(), ligne, 0);
            table.setValueAt(e.getNom(), ligne, 1);
            ligne++;
        }

        JButton ajouter = new JButton("Ajouter une discipline");
        JButton supprimer = new JButton("Supprimer");
        JButton modifier = new JButton("Sauvegarder les changements");

        ajouter.addActionListener((ActionEvent e) -> {

            f = new Fenetre("AjoutDiscipline");
            JTextField t2 = new JTextField("Matiere", 10);
            JButton conf = new JButton("Confirmer");
            conf.addActionListener((ActionEvent evt) -> {
//                    System.out.println(parseInt((String) t2.getText()));
                try {
                    dao_discipline.rechercher_discipline(new Discipline(t2.getText()));
                    err = new Error("L'element existe déjà");
                } catch (NonExistingElement aee) {

                    dao_discipline.creer(new Discipline(t2.getText()), 0);

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
                err.getFrame().dispose();
                err = new Error("Veuillez sélectrionner un élément dans le tableau ");
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
        menu = new JMenu("Trimestre");
        JMenuItem mi = new JMenuItem("Trimestre1");
        mi.addActionListener((ActionEvent e) -> {
            menu.setText("Trimestre1");
            trimestre = 1;
        });
        JMenuItem mi2 = new JMenuItem("Trimestre2");
        mi2.addActionListener((ActionEvent e) -> {
            menu.setText("Trimestre2");
            trimestre = 2;
        });
        JMenuItem mi3 = new JMenuItem("Trimestre3");
        mi3.addActionListener((ActionEvent e) -> {
            menu.setText("Trimestre3");
            trimestre = 3;
        });
        mi.setBackground(Color.magenta.darker());
        mi2.setBackground(Color.yellow.darker());
        mi3.setBackground(Color.yellow.darker());

        menu.add(mi);
        menu.add(mi2);
        menu.add(mi3);

        mb.add(menu);

        interface_eleve.setJMenuBar(mb);
    }

    private void afficher_bulletin(Bulletin bulletin, DAO_Eleve dao_eleve) {
        f2.getFrame().dispose();
        f2 = new Fenetre("Bulletin :" + bulletin.getEleve().getNom() + " " + bulletin.getEleve().getPrenom());
        f2.getFrame().setSize(400, 200);
        f2.getPanel().setLayout(new BoxLayout(f2.getPanel(), BoxLayout.PAGE_AXIS));
        JLabel lab2 = new JLabel("Eleve : " + bulletin.getEleve().getNom() + " " + bulletin.getEleve().getPrenom());
        JLabel lab = new JLabel("Appreciation : " + bulletin.getAppreciation());
        JButton detail = new JButton("Voir le détail trimestre :" + trimestre);
        detail.addActionListener((ActionEvent evt) -> {
            try {
                dao_eleve.details_bulletin(bulletin);
                afficher_details(bulletin, dao_eleve);
            } catch (SQLException ex) {
                err = new Error("Probleme d'affichage");
            }
        });
        f2.getPanel().add(lab2);
        f2.getPanel().add(lab);
        f2.getPanel().add(detail);
        f2.getFrame().add(f2.getPanel());

    }

    public void afficher_details(Bulletin bulletin, DAO_Eleve dao_eleve) throws SQLException {
        matiere = null;

        Fenetre f3 = new Fenetre("Bulletin :" + bulletin.getEleve().getNom() + " " + bulletin.getEleve().getPrenom());
        f3.getFrame().setSize(400, 200);
        f3.getFrame().setLayout(new GridLayout(1, 2));
        f3.getPanel().setLayout(new BoxLayout(f3.getPanel(), BoxLayout.PAGE_AXIS));
        System.out.println("TOURDEBOULCE:" + bulletin.getDetails_collection().size());
        for (DetailBulletin db : bulletin.getDetails_collection()) {
            System.out.println("TOURDEBOULCE:" + db.getBulletin_data().size());
            for (Map.Entry<String, ArrayList<Note>> e : db.getBulletin_data().entrySet()) {

                int ligne = 0;
                System.out.println("SIZE:" + e.getValue().size());
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                JTable table = new JTable(1, 1);
                JTable table2 = new JTable(e.getValue().size(), 1);
                table.setValueAt(e.getKey(), 0, 0);
                panel.add(table, BorderLayout.CENTER);

                System.out.println(e.getKey());
                for (Note e2 : e.getValue()) {

                    table2.setValueAt(e2.getNote(), ligne, 0);
                    System.out.println(e2.getNote() + "|" + e2.getAppreciation());

                    table2.getSelectionModel().addListSelectionListener((ListSelectionEvent e1) -> {
                        if (table2.getSelectedRow() == e.getValue().indexOf(e2)) {

                            table2.setValueAt(table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn()).toString(), table2.getSelectedRow(), table2.getSelectedColumn());

                            e2.setNote(parseInt(table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn()).toString()));
                            System.out.println("TABLE2.GETVALUE:" + table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn()) + "|" + e2.getNote());

                            note = new Note(e2.getNote(), e2.getAppreciation(), e2.getId());
                        }

                    });
                    System.out.println("NOTE222 : " + note.getNote());
                    ligne++;
                }

                panel.add(table2, BorderLayout.EAST);
                f3.getPanel().add(panel);

                table.getSelectionModel().addListSelectionListener((ListSelectionEvent e1) -> {
                    System.out.println(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
                    matiere = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
                });
            }

        }
        JButton ajouter = new JButton("Ajouter une note");
        JButton supprimer = new JButton("Supprimer");
        JButton modifier = new JButton("Sauvegarder les changements");

        ajouter.addActionListener((ActionEvent evt) -> {
            f.getFrame().dispose();
            f = new Fenetre("AjoutNote");
            JTextField txt1 = new JTextField("Note");
            JButton conf = new JButton("Confirmer");

            conf.addActionListener((ActionEvent evt2) -> {
                try {
                    dao_eleve.creer_note(bulletin, parseInt(txt1.getText()), matiere);
                } catch (SQLException ex) {
                    err.getFrame().dispose();
                    err = new Error("Probleme de creation");
                }

                f.getFrame().dispose();
                f3.getFrame().dispose();
                try {
                    dao_eleve.details_bulletin(bulletin);
                    afficher_details(bulletin, dao_eleve);

                } catch (SQLException ex) {
                    err = new Error("Probleme d'affichage");
                }
            });
            f.getFrame().add(txt1);
            f.getFrame().add(conf);

        });

        supprimer.addActionListener((ActionEvent evt) -> {

            try {
                dao_eleve.supprimer_note(note.getId());

            } catch (SQLException ex) {
                err = new Error("Probleme d'affichage");
            }

            f.getFrame().dispose();
            f3.getFrame().dispose();
            try {
                dao_eleve.details_bulletin(bulletin);
                afficher_details(bulletin, dao_eleve);
            } catch (SQLException ex) {
                err = new Error("Probleme d'affichage");
            }

        });

        modifier.addActionListener((ActionEvent evt) -> {
            dao_eleve.modifier(note);
            try {
                dao_eleve.details_bulletin(bulletin);
                afficher_details(bulletin, dao_eleve);
            } catch (SQLException ex) {
                err = new Error("Probleme d'affichage");
            }
        });

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 1));
        panel2.add(ajouter);
        panel2.add(supprimer);
        panel2.add(modifier);

        f3.getFrame().add(f3.getPanel());
        f3.getFrame().add(panel2);

    }

    public void fenetre_classe() {
        DAO_Classe dao_classe = new DAO_Classe(conn);
        interface_classe = new JFrame("InterfaceClasse");
        creer_fenetre(interface_classe, 500, 200);
        interface_classe.setLayout(new GridLayout(1, 2));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        JTable table = new JTable(dao_classe.getCollection_classe().size() + 1, 4);

        table.setValueAt("ID", 0, 0);
        table.setValueAt("Nom", 0, 1);
        table.setValueAt("Niveau", 0, 2);
        table.setValueAt("Année Scolaire", 0, 3);
        int ligne = 1;

        for (Classe e : dao_classe.getCollection_classe()) {
            System.out.println(e.getId() + ":" + e.getNom());
            table.setValueAt(e.getId(), ligne, 0);
            table.setValueAt(e.getNom(), ligne, 1);
            table.setValueAt(e.getNiveau(), ligne, 2);
            table.setValueAt(e.getAnneescolaire_id(), ligne, 3);
            ligne++;
        }

        JButton ajouter = new JButton("Ajouter une classe");
        JButton supprimer = new JButton("Supprimer");
        JButton modifier = new JButton("Sauvegarder les changements");

        ajouter.addActionListener((ActionEvent e) -> {

            f = new Fenetre("CreerClasse");
            JTextField t2 = new JTextField("Classe", 10);
            JTextField t3 = new JTextField("Niveau", 10);
            JTextField t4 = new JTextField("Annee scolaire", 10);
            JButton conf = new JButton("Confirmer");
            conf.addActionListener((ActionEvent evt) -> {
//                    System.out.println(parseInt((String) t2.getText()));
                try {
                    dao_classe.rechercher_classe(new Classe(t2.getText(), parseInt(t3.getText()), parseInt(t4.getText())));
                    Error err = new Error("L'element existe déjà");
                } catch (NonExistingElement aee) {

                    dao_classe.creer(new Classe(t2.getText(), parseInt(t3.getText()), parseInt(t4.getText())), 0);

                    f.getFrame().dispose();
                    interface_classe.dispose();
                    fenetre_classe();
                }

            });
            f.getPanel().add(t2);
            f.getPanel().add(t3);
            f.getPanel().add(t4);
            f.getFrame().add(f.getPanel());
            f.getFrame().add(conf);

        });

        supprimer.addActionListener((ActionEvent e) -> {

            try {

                dao_classe.supprimer(dao_classe.getCollection_classe().get(table.getSelectedRow() - 1));
                interface_classe.dispose();
                fenetre_classe();
            } catch (ArrayIndexOutOfBoundsException ai) {
                Error err = new Error("Veuillez sélectrionner un élément dans le tableau ");
            }

        });

        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e1) -> {
            System.out.println(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());
            table.setValueAt(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString(), table.getSelectedRow(), table.getSelectedColumn());
        });

        modifier.addActionListener((ActionEvent evt) -> {
            int size = dao_classe.getCollection_classe().size() + 1;
            dao_classe.getCollection_classe().clear();
            System.out.println(dao_classe.getCollection_classe().size());
            for (int i = 1; i < size; i++) {
                dao_classe.modifier(new Classe((int) table.getValueAt(i, 0), (String) table.getValueAt(i, 1), (int) table.getValueAt(i, 2), (int) table.getValueAt(i, 3)));
            }
            interface_classe.dispose();
            fenetre_classe();
        });

        panel.add(ajouter);
        panel.add(supprimer);
        panel.add(modifier);
        interface_classe.add(table);
        interface_classe.add(panel);
    }

}
