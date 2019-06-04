/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillaume
 */
public class DAO_Discipline extends DAO<Discipline> {
/*Méthode pour Créer une discipline**/
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
            
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM tab_enseignement where enseignement_discipline_id = ?");
            stmt2.setObject(1, obj.getId(), Types.INTEGER);
            stmt2.executeUpdate();
            
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tab_disicpline where discipline_id = ?");
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
    public void chargement() {
    }

   

}
