/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author guifl
 */
public abstract class DAO<T> {

    protected Connection conn = null;

    public DAO(Connection conn) {
        conn = null;
        String url = "jdbc:mysql://localhost/";
        String dbName = "gestion_ecole";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
        this.conn = conn;
        }catch(Exception e){
        System.out.println("probleme de connexion a la BDD");
        }
        
    }

    /**
     * Méthode de création
     *
     * @param obj
     * @return boolean
     */
    public abstract void creer(Connection conn);

    /**
     * Méthode pour effacer
     *
     * @param obj
     * @return boolean
     */
    public abstract void supprimer(Connection conn);

    /**
     * Méthode de mise à jour
     *
     * @param obj
     * @return boolean
     */
    public abstract void modifier(Connection conn);

    /**
     * Méthode de recherche des informations
     *
     * @param id
     * @return T
     */
    public abstract void afficher(int id);
}
