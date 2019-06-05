/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

/**
 *
 * @author xavie
 */
public class Bulletin {
    private int id;
    private String appreciation;
    private Eleve eleve;
    
    public Bulletin(){};
    public Bulletin(String appreciation, int id, Eleve e){
        this.id=id;
        this.appreciation=appreciation;        
        this.eleve=e;
    };

    public int getId() {
        return id;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public Eleve getEleve() {
        return eleve;
    }
    
}
