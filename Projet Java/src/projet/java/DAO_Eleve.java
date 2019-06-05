/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import Exceptions.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author Guillaume
 */
public class DAO_Eleve extends DAO<Eleve> {

    private ArrayList<Eleve> collection_eleve = new ArrayList<>();

    public DAO_Eleve() {
        super();
        chargement();
    }

    @Override
    public void creer(Eleve obj, int classe) {
        try {

            PreparedStatement stmt = conn.prepareStatement("insert into tab_personne(personne_nom,personne_prenom,personne_type) values(?,?,?)");

            stmt.setObject(1, obj.getNom(), Types.VARCHAR);
            stmt.setObject(2, obj.getPrenom(), Types.VARCHAR);
            stmt.setObject(3, obj.getType(), Types.VARCHAR);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("SELECT personne_id FROM tab_personne WHERE personne_nom = ? and personne_prenom = ?");
//           System.out.println("1");//Test
            stmt.setString(1, obj.getNom());
            stmt.setString(2, obj.getPrenom());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id = rs.getInt("personne_id");
            System.out.println("IDD:" + id + "| Classe:" + classe);

            stmt = conn.prepareStatement("insert into tab_inscription(inscription_classe_id,inscription_personne_id) values(?,?)");
            stmt.setObject(1, classe, Types.INTEGER);
            stmt.setObject(2, id, Types.INTEGER);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("SELECT inscription_id FROM tab_inscription WHERE inscription_personne_id = ?");
//           System.out.println("1");//Test
            stmt.setObject(1, id);
            rs = stmt.executeQuery();
            rs.next();
            int id2 = rs.getInt("inscription_id");
            System.out.println("IDD2:" + id2 + "| Classe:" + classe);

            for (int i = 1; i <= 3; i++) {
                stmt = conn.prepareStatement("insert into tab_bulletin(bulletin_trimestre_id,bulletin_inscription_id,bulletin_appreciation) values(?,?,'A renseigner')");
                stmt.setObject(1, i, Types.INTEGER);
                System.out.println("1");
                stmt.setObject(2, id2, Types.INTEGER);
                System.out.println("2");
                stmt.executeUpdate();
            }

            insert_tab_detailbulletin(id2, classe);

        } catch (SQLException ex) {
            System.out.println("Probleme de creation eleve");
        }

    }

    /*On crée un élement dans la BDD par la requete SQL insert into**/
    @Override
    public void supprimer(Eleve obj) {
        try {

            PreparedStatement stmt = conn.prepareStatement("SELECT inscription_id FROM tab_inscription WHERE inscription_personne_id = ?");
//           System.out.println("1");//Test
            stmt.setObject(1, obj.getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id2 = rs.getInt("inscription_id");

            stmt = conn.prepareStatement("select bulletin_id FROM tab_bulletin where bulletin_inscription_id = ?");
            stmt.setObject(1, id2);
            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("SUPP1");
                int id3 = rs.getInt("bulletin_id");
                stmt = conn.prepareStatement("select detailbulletin_bulletin_id FROM tab_detailbulletin where detailbulletin_bulletin_id = ?");
                stmt.setObject(1, id3);
                ResultSet rs2 = stmt.executeQuery();
                System.out.println("SUPP2");
                while (rs2.next()) {
                    int id4 = rs2.getInt("detailbulletin_bulletin_id");
                    stmt = conn.prepareStatement("DELETE FROM tab_evaluation where evaluation_detailbulletin_id = ?");
                    stmt.setObject(1, id4);
                    stmt.executeUpdate();
                    System.out.println("SUPP3");
                }
                stmt = conn.prepareStatement("DELETE FROM tab_detailbulletin where detailbulletin_bulletin_id = ?");
                stmt.setObject(1, id3);
                stmt.executeUpdate();

            }
            System.out.println("AIE");
            stmt = conn.prepareStatement("DELETE FROM tab_bulletin where bulletin_inscription_id = ?");
            stmt.setObject(1, id2, Types.INTEGER);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("DELETE FROM tab_inscription where inscription_personne_id = ?");
            stmt.setObject(1, obj.getId(), Types.INTEGER);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("DELETE FROM tab_personne where personne_id = ?");
            stmt.setObject(1, obj.getId(), Types.INTEGER);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Probleme de suppression");
        }
    }

    /*On modifie un élement dans la BDD par la requete SQL Update set en utilisant where pour indiquer l'élément à modifier, on utilise l'id**/
    @Override
    public void modifier(Eleve obj) {
        try {

            // ID ave cla classe ELeve
//            id = obj.getId();
            PreparedStatement stmt = conn.prepareStatement("Update tab_personne set personne_nom = ? ,personne_prenom = ? ,personne_type = ? where personne_id = ? ");
            stmt.setObject(1, obj.getNom(), Types.VARCHAR);
            stmt.setObject(2, obj.getPrenom(), Types.VARCHAR);
            stmt.setObject(3, obj.getType(), Types.VARCHAR);
            stmt.setObject(4, obj.getId(), Types.INTEGER);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Probleme de modification");
        }
    }

    @Override
    public final void chargement() {

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tab_personne where personne_type like 'etu'");
            collection_eleve = new ArrayList<>();
            while (rs.next()) {

                int a = rs.getInt("personne_id");
                String b = rs.getString("personne_nom");
                String c = rs.getString("personne_prenom");
                String d = rs.getString("personne_type");
                System.out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d);

                collection_eleve.add(new Eleve(a, b, c));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Eleve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close_connection() throws SQLException {
        this.conn.close();
    }

    public ArrayList<Eleve> getCollection_eleve() {
        return collection_eleve;
    }

    public Eleve rechercher_eleve(Eleve e) throws NonExistingElement {
        for (Eleve e1 : collection_eleve) {
            if (e1.getNom().equals(e.getNom()) && e1.getPrenom().equals(e.getPrenom())) {
                return e;
            }
        }
        throw new NonExistingElement("");
    }

    public void creer_combobox(JComboBox t3) {
        try {
            //Combobox pour la classe
            //https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html

            JLabel lab = new JLabel("Classes");
            PreparedStatement stmt = conn.prepareStatement("SELECT classe_id FROM tab_classe ");
            ResultSet Rs = stmt.executeQuery();
            while (Rs.next()) {

                //Pour affecter une valeur de base de données à un Combobox
                t3.addItem(Rs.getString("classe_id"));
            } //Fin de la comboBox
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Eleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        t3.setSelectedIndex(0);
    }

    public Bulletin consulter_bulletin(Eleve e, int i) {
        try {

            PreparedStatement stmt = conn.prepareStatement("SELECT inscription_id FROM tab_inscription WHERE inscription_personne_id = ?");
//           System.out.println("1");//Test
            stmt.setObject(1, e.getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id2 = rs.getInt("inscription_id");

            stmt = conn.prepareStatement("SELECT bulletin_appreciation, bulletin_id FROM tab_bulletin WHERE bulletin_inscription_id = ? and bulletin_trimestre_id = ?");
//           System.out.println("1");//Test
            stmt.setObject(1, id2);
            stmt.setObject(2, i);
            rs = stmt.executeQuery();
            rs.next();
            String id3 = rs.getString("bulletin_appreciation");
            int id = rs.getInt("bulletin_id");

            System.out.println(id2 + ":" + id);
            return new Bulletin(id3, id, e);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Eleve.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public DetailBulletin details_bulletin(Bulletin bulletin) throws SQLException {
        ArrayList<Note> Array = new ArrayList<>();
        HashMap<String,ArrayList<Note>> hm= new HashMap();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tab_detailbulletin WHERE detailbulletin_bulletin_id = ?");
//           System.out.println("1");//Test
        stmt.setObject(1, bulletin.getId());
        ResultSet rs = stmt.executeQuery();
         while(rs.next()){
             
        int id2 = rs.getInt("detailbulletin_id");
        int id3 = rs.getInt("detailbulletin_enseignement_id");
        String comm = rs.getString("detailbulletin_appreciation");
        System.out.println("TEST1");
        stmt = conn.prepareStatement("SELECT discipline_nom FROM tab_discipline c ,tab_enseignement d WHERE d.enseignement_id = ? and d.enseignement_discipline_id = c.discipline_id ");
//           System.out.println("1");//Test
        stmt.setObject(1, id3);
        rs = stmt.executeQuery();
        System.out.println("TEST2");
       rs.next();
            String result = rs.getString("discipline_nom");

            stmt = conn.prepareStatement("SELECT evaluation_note FROM tab_evaluation WHERE evaluation_detailbulletin_id = ?");
    //           System.out.println("1");//Test
            stmt.setObject(1, id2);
            rs = stmt.executeQuery();
            while(rs.next()){
                int i=rs.getInt("evaluation_note");
                Array.add(new Note(i,"à renseigner"));
            }
            hm.put(result, Array);
        }
        
        return new DetailBulletin(hm);

    }

    private void insert_tab_detailbulletin(int id2, int classe) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT bulletin_id FROM tab_bulletin WHERE bulletin_inscription_id = ?");
//           System.out.println("1");//Test
        stmt.setObject(1, id2);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {

            int id4 = rs.getInt("bulletin_id");
            System.out.println("IDD4:" + id4);

            stmt = conn.prepareStatement("SELECT enseignement_id FROM tab_enseignement WHERE enseignement_classe_id = ?");

            stmt.setObject(1, classe);
            ResultSet rs3 = stmt.executeQuery();
            System.out.println("SELECTED");//Test
            while (rs3.next()) {
                int id3 = rs3.getInt("enseignement_id");

                stmt = conn.prepareStatement("insert into tab_detailbulletin(detailbulletin_bulletin_id,detailbulletin_enseignement_id,detailbulletin_appreciation) values(?,?,'A renseigner')");
                stmt.setObject(1, id4, Types.INTEGER);
                System.out.println("1");
                stmt.setObject(2, id3, Types.INTEGER);
                System.out.println("2");
                stmt.executeUpdate();
                System.out.println("SELECTED2");

                insert_tab_evaluation(id4);

            }

        }
    }

    private void insert_tab_evaluation(int id4) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT detailbulletin_id FROM tab_detailbulletin WHERE detailbulletin_bulletin_id = ?");
        System.out.println("SELECTED3");
        stmt.setObject(1, id4);
        ResultSet rs4 = stmt.executeQuery();
        System.out.println("SELECTED5");
        while (rs4.next()) {
            int id5 = rs4.getInt("detailbulletin_id");
            System.out.println(id5);
            stmt = conn.prepareStatement("insert into tab_evaluation(evaluation_detailbulletin_id,evaluation_note,evaluation_appreciation) values(?,?,'A renseigner')");
            stmt.setObject(1, id5, Types.INTEGER);
            stmt.setObject(2, null, Types.INTEGER);
            stmt.executeUpdate();
            System.out.println("SELECTED4");

        }
    }
}
