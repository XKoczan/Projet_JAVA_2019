/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

/**
 *
 * @author Guillaume
 */
public class Discipline {
    int id;
    String nom;
    
    Discipline(){}

    public Discipline(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
    
   
    public String toString()
    {
        return "ID:"+this.getId()+"|"+"NOM:"+this.getNom();
    }
}
