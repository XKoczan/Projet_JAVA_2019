/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import Vue.Connect;
import Vue.InterfaceGraphique;
import java.sql.*;

/**
 *
 * @author guifl
 */
public class ProjetJava {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        Connect conn = new Connect();
        conn.fenetre_conn();
      
    }

}
