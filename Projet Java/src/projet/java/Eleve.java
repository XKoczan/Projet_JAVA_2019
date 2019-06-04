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
public class Eleve {

    int id;
    String nom;
    String prenom;
    String type;

    public Eleve() {

    }

    public Eleve(String nom, String prenom) {
        this.nom=nom;
        this.prenom=prenom;
    }

   

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

   
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }




}
