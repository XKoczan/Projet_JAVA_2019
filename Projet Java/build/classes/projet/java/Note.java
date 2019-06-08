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
    private int note;
    private int id;
    private String appreciation;
    
    public Note(){};
    public Note(int note, String appreciation,int id){
        this.id=id;
        this.note=note;
        this.appreciation=appreciation;
    };

    public int getNote() {
        return note;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getId() {
        return this.id;
    }
    
    
}
