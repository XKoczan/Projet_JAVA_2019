/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import javax.swing.*;

/**
 *
 * @author xavie
 */
public class Error {
    public Error(String message){
        JFrame frame = new JFrame();
        JLabel lab = new JLabel(message);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.add(lab);
        frame.setVisible(true);
    }
}
