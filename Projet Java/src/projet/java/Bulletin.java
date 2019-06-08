/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import java.util.ArrayList;

/**
 *
 * @author xavie
 */
public class Bulletin {
    
    public ArrayList <DetailBulletin> details_collection = new ArrayList<DetailBulletin>();  
    private int id;
    private String appreciation;
    private Eleve eleve;
    
    public Bulletin(){};
    
    public Bulletin(String appreciation, int id, Eleve e,ArrayList <DetailBulletin> details_collection){
        this.id=id;
        this.appreciation=appreciation;        
        this.eleve=e;
        this.details_collection= details_collection;
    };

    public ArrayList<DetailBulletin> getDetails_collection() {
        return details_collection;
    }

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
