/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import Exceptions.NonExistingElement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author Guillaume
 */
public class DAO_Discipline extends DAO<Discipline> {
/*Méthode pour Créer une discipline**/
    private ArrayList<Discipline> collection_discipline = new ArrayList<>();
    
    public DAO_Discipline(){
    super();
    chargement();
    }

    public DAO_Discipline(Connection conn) {
        this.conn=conn;
        chargement();
    }
    
    @Override
    public void creer(Discipline obj, int a) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into tab_discipline(discipline_nom) values(?)");
            stmt.setObject(1, obj.getNom(), Types.VARCHAR);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Probleme de creation de discipline");
        }
    }

    @Override
    public void supprimer(Discipline obj) {
        
        try {
            System.out.println(obj.id);
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM tab_enseignement where enseignement_discipline_id = ?");
            stmt2.setObject(1, obj.getId(), Types.INTEGER);
            stmt2.executeUpdate();
            
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tab_discipline where discipline_id = ?");
            stmt.setObject(1, obj.getId(), Types.INTEGER);
            stmt.executeUpdate();
            
            

        } catch (SQLException ex) {
            System.out.println("Probleme de suppression de discipline");
        }
    }

    @Override
    public void modifier(Discipline obj) {
         try {

            // ID ave cla classe ELeve
//            id = obj.getId();
            PreparedStatement stmt = conn.prepareStatement("Update tab_disicpline set discipline_nom = ?  where discipline_id = ? ");
            stmt.setObject(1, obj.getId(), Types.INTEGER);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Probleme de modification de la discipline");
        }
    }

    @Override
    public void chargement() {try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tab_discipline ");
            collection_discipline = new ArrayList<>();
            while (rs.next()) {

                int a = rs.getInt("discipline_id");
                String b = rs.getString("discipline_nom");
                System.out.println("a: " + a + " b: " + b );

                collection_discipline.add(new Discipline(a,b));

            }
        } catch (SQLException ex) {
            System.out.println("Probleme de chargement des discipline ");
        }
    }

    public void close_connection() throws SQLException {
        this.conn.close();
    }

    public ArrayList<Discipline> getCollection_discipline() {
        return collection_discipline;
    }

    public Discipline rechercher_discipline(Discipline e) throws NonExistingElement {
        for (Discipline e1 : collection_discipline) {
            if (e1.getId()==(e.getId()) && e1.getNom().equals(e.getNom())) {
                return e;
            }
        }
        throw new NonExistingElement("");
    }

   

}
