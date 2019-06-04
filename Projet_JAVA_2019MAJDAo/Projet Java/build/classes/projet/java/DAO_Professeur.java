/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/**
 *
 * @author Guillaume
 */
public class DAO_Professeur extends DAO<Professeur>{

    @Override
    public void creer(Professeur obj, int a) {
        try {
            String nom;
            String prenom;
            
            int id;
            int classe_id;
            int discipline_id;
            
            Scanner sc = new Scanner(System.in);
            System.out.println("nom");
            nom = sc.nextLine();
            
            System.out.println("prenom");
            prenom = sc.nextLine();

//            int id= obj.getId();
            Scanner sc1 = new Scanner(System.in);

            PreparedStatement stmt = conn.prepareStatement("insert into tab_personne(personne_nom,personne_prenom,personne_type) values(?,?,'prof')");
            stmt.setObject(1, nom, Types.VARCHAR);
            stmt.setObject(2, prenom, Types.VARCHAR);
            stmt.executeUpdate();

            
            
//            System.out.println("0");//Test
            PreparedStatement stmt3 = conn.prepareStatement("SELECT personne_id FROM tab_personne WHERE personne_nom = ? and personne_prenom = ?");
//           System.out.println("1");//Test
            stmt3.setString(1, nom);
            stmt3.setString(2, prenom);
            // on récupère l'id de l'élève créé pour l'associé à une classe
            ResultSet rs = stmt3.executeQuery();
            System.out.println("On recupère id du prof");//Test
            rs.next();

            id = 0;//On reset l'id
            id = rs.getInt("personne_id");
            System.out.println("ID :" + id);//Test de la récupération de 'ID
            
            System.out.println("Le professeur a au moins une matière. Entrer l'id de la classe à la laquelle il enseigne:");
            classe_id = sc1.nextInt();
            
            System.out.println("Entrer l'id de la discipline enseignée:");
            discipline_id = sc1.nextInt();
            
            //On effectue la création dans les tables auxiliaires ensuite après, création de l'inscription dans une classe de niveau sinon dans un classe niveau faisant office de classe de stockage des étudiants
            PreparedStatement stmt1 = conn.prepareStatement("insert into tab_enseignement(enseignement_classe_id,enseignement_discipline_id,enseignement_personne_id) values(?,?,?)");
            stmt1.setObject(1, classe_id, Types.INTEGER);//On utilise l'id de la classe
            stmt1.setObject(2, discipline_id, Types.INTEGER);//On utilise l'id de l'eleve
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
    }
    
}
