/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import Exceptions.NonExistingElement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillaume
 */
public class DAO_Professeur extends DAO<Professeur>{

    
    private ArrayList<Professeur> collection_professeur = new ArrayList<>();

    public DAO_Professeur() {
        super();
        chargement();
    }
    @Override
    public void creer(Professeur obj, int classe,int discipline) {
        try {
           

            PreparedStatement stmt = conn.prepareStatement("insert into tab_personne(personne_nom,personne_prenom,personne_type) values(?,?,'prof')");
            stmt.setObject(1, obj.getNom(), Types.VARCHAR);
            stmt.setObject(2, obj.getPrenom(), Types.VARCHAR);
            stmt.executeUpdate();

            
            
//            System.out.println("0");//Test
            PreparedStatement stmt3 = conn.prepareStatement("SELECT personne_id FROM tab_personne WHERE personne_nom = ? and personne_prenom = ?");
//           System.out.println("1");//Test
            stmt3.setString(1, obj.getNom());
            stmt3.setString(2, obj.getPrenom());
            // on récupère l'id de l'élève créé pour l'associé à une classe
            ResultSet rs = stmt3.executeQuery();
            System.out.println("On recupère id du prof");//Test
            rs.next();

            int id = rs.getInt("personne_id");
            System.out.println("ID :" + id);//Test de la récupération de 'ID
                                    
            //On effectue la création dans les tables auxiliaires ensuite après, création de l'inscription dans une classe de niveau sinon dans un classe niveau faisant office de classe de stockage des étudiants
            PreparedStatement stmt1 = conn.prepareStatement("insert into tab_enseignement(enseignement_classe_id,enseignement_discipline_id,enseignement_personne_id) values(?,?,?)");
            stmt1.setObject(1, classe, Types.INTEGER);//On utilise l'id de la classe
            stmt1.setObject(2, discipline, Types.INTEGER);//On utilise l'id de l'eleve
            stmt1.setObject(3, id, Types.INTEGER);
            stmt1.executeUpdate();//On crée dans la table ennseignement, l'enseignement d'un prof dans une classe
            
        } catch (SQLException ex) {
            System.out.println("Probleme de creation professeur");
        }
    }

    @Override
    public void supprimer(Professeur obj) {
         try {
            
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM tab_enseignement where enseignement_personne_id = ?");
            stmt2.setObject(1, obj.getId(), Types.INTEGER);
            stmt2.executeUpdate();
            
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tab_personne where personne_id = ?");
            stmt.setObject(1, obj.getId(), Types.INTEGER);
            stmt.executeUpdate();
            
            

        } catch (SQLException ex) {
            System.out.println("Probleme de suppression");
        }
    }

    @Override
    public void modifier(Professeur obj) {
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
    public void chargement() {
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tab_personne");
            collection_professeur = new ArrayList<>();
            while (rs.next()) {

                int a = rs.getInt("personne_id");
                String b = rs.getString("personne_nom");
                String c = rs.getString("personne_prenom");
                String d = rs.getString("personne_type");
                System.out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d);

                collection_professeur.add(new Professeur(a, b, c));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Eleve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close_connection() throws SQLException {
        this.conn.close();
    }

    public ArrayList<Professeur> getCollection_professeur() {
        return collection_professeur;
    }

    public Professeur rechercher_Professeur(Professeur e) throws NonExistingElement {
        for (Professeur e1 : collection_professeur) {
            if (e1.getNom().equals(e.getNom()) && e1.getPrenom().equals(e.getPrenom())) {
                return e;
            }
        }
        throw new NonExistingElement("");
    }
    public Professeur rechercher_professeur(Professeur e) throws NonExistingElement {
        for (Professeur e1 : collection_professeur) {
            if (e1.getNom().equals(e.getNom()) && e1.getPrenom().equals(e.getPrenom())) {
                return e;
            }
        }
        throw new NonExistingElement("");
    }

    @Override
    public void creer(Professeur obj, int a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    

