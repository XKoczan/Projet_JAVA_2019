/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author xavie
 */
public class DetailBulletin {
    private HashMap <String, ArrayList<Note>> bulletin_data = new HashMap();
    
    public DetailBulletin(){
        bulletin_data= new HashMap();
    }
     public DetailBulletin(HashMap<String, ArrayList<Note>> bulletin_data){
        this.bulletin_data= bulletin_data;
    }
    
    public HashMap<String, ArrayList<Note>> getBulletin_data() {
        return bulletin_data;
    }
    
}
