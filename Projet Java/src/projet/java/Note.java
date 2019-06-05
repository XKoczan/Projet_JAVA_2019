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
public class Note {
    int note;
    String appreciation;
    
    public Note(){};
    public Note(int note, String appreciation){
        this.note=note;
        this.appreciation=appreciation;
    };

    public int getNote() {
        return note;
    }

    public String getAppreciation() {
        return appreciation;
    }
    
    
}
