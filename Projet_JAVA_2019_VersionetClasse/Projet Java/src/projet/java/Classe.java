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
public class Classe {
    
    int id;
    int anneescolaire_id;
    int niveau;
    String nom;

    public Classe(int id,String nom,int niveau, int anneescolaire_id ) {
        this.id = id;
        this.anneescolaire_id = anneescolaire_id;
        this.niveau = niveau;
        this.nom = nom;
    }

    public Classe(String nom,int niveau,int anneescolaire_id) {
        this.anneescolaire_id = anneescolaire_id;
        this.niveau = niveau;
        this.nom = nom;
    }

    public int getNiveau() {
        return niveau;
    }

  

    public int getId() {
        return id;
    }

    public int getAnneescolaire_id() {
        return anneescolaire_id;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Classe{" + "id=" + id + ", anneescolaire_id=" + anneescolaire_id + ", nom=" + nom + '}';
    }
    
    
}
