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

    public DAO() {
         }

    /**
     * Méthode de création
     *
     * @param obj
     * @param a
     * @return boolean
     */
    public abstract void creer(T obj, int a);
    
    /**
     * Méthode pour effacer
     *
     * @param obj
     * @return boolean
     * @throws Exceptions.NonSelectedElement
     */
    public abstract void supprimer(T obj);

    /**
     * Méthode de mise à jour
     *
     * @param obj
     * @return boolean
     */
    public abstract void modifier(T obj);

    /**
     * Méthode de recherche des informations
     *
     * @param obj
     * @return T
     */
    public abstract void chargement();
}
