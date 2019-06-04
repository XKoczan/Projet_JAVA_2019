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
import java.util.logging.Level;
import java.util.logging.Logger;

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
            System.out.println("IDD:"+id+"| Classe:"+classe);
            
            PreparedStatement stmt2 = conn.prepareStatement("insert into tab_inscription(inscription_classe_id,inscription_personne_id) values(?,?)");
            stmt2.setObject(1, classe, Types.INTEGER);
            stmt2.setObject(2, id, Types.INTEGER);
            stmt2.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Probleme de creation");
        }

    }

    /*On crée un élement dans la BDD par la requete SQL insert into**/
    @Override
    public void supprimer(Eleve obj) {
        try {
            
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM tab_inscription where inscription_personne_id = ?");
            stmt2.setObject(1, obj.getId(), Types.INTEGER);
            stmt2.executeUpdate();
            
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tab_personne where personne_id = ?");
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
            ResultSet rs = stmt.executeQuery("select * from tab_personne");
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
}
