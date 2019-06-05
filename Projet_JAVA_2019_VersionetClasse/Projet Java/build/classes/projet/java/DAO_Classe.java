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

/**
 *
 * @author Guillaume
 */
public class DAO_Classe extends DAO<Classe> {

    private ArrayList<Classe> collection_classe = new ArrayList<>();

    public DAO_Classe() {
        super();
        chargement();
    }

    @Override
    public void creer(Classe obj, int a) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into tab_classe(classe_nom,classe_niveau_id,classe_anneescolaire_id) values(?,?,?)");
            stmt.setObject(1, obj.getNom(), Types.VARCHAR);
            stmt.setObject(2, obj.getNiveau(), Types.INTEGER);
            stmt.setObject(3, obj.getAnneescolaire_id(), Types.INTEGER);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Probleme de creation de la classe");
        }
    }

    @Override
    public void supprimer(Classe obj) {
 try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tab_classe where classe_id = ?");
            stmt.setObject(1, obj.getId(), Types.INTEGER);
            stmt.executeUpdate();
                        

        } catch (SQLException ex) {
            System.out.println("Probleme de suppression de la classe");
        }    }

    @Override
    public void modifier(Classe obj) {
try {

            PreparedStatement stmt = conn.prepareStatement("Update tab_classe set classe_nom = ?,classe_niveau_id=?  where classe_id = ? ");
            stmt.setObject(1, obj.getId(), Types.INTEGER);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Probleme de modification de la classe");
        }    }

    @Override
    public void chargement() {try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tab_classe ");
            collection_classe = new ArrayList<>();
            while (rs.next()) {

                int a = rs.getInt("classe_id");
                String b = rs.getString("classe_nom");
                int c = rs.getInt("classe_niveau_id");
                int d = rs.getInt("classe_anneescolaire_id");
                System.out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d);

                collection_classe.add(new Classe(a,b,c,d));

            }
        } catch (SQLException ex) {
            System.out.println("Probleme de chargement des classes ");
        }
    }

    public void close_connection() throws SQLException {
        this.conn.close();
    }

    public ArrayList<Classe> getCollection_classe() {
        return collection_classe;
    }

    public Classe rechercher_classe(Classe e) throws NonExistingElement {
        for (Classe e1 : collection_classe) {
            if (e1.getId()==(e.getId()) && e1.getNom().equals(e.getNom())) {
                return e;
            }
        }
        throw new NonExistingElement("");
    }

}
