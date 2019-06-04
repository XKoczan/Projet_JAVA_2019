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
public class Professeur extends Personne {
    
    public Professeur(){}
    
    public Professeur(int id, String nom, String prenom) {
        super(id,nom,prenom,"prof");
    }
    @Override
    public String toString()
    {
        return "ID:"+this.getId()+"|"+"NOM:"+this.getNom()+"|Prenom:"+this.getPrenom()+"|Type:"+this.getType();
    }
}
