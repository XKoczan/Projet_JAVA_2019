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
    private JFrame frame= new JFrame();
    public Error(String message){
        frame = new JFrame("Error");
        JLabel lab = new JLabel(message);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.add(lab);
        frame.setVisible(true);
    }

    Error() {
    }

    public JFrame getFrame() {
        return frame;
    }
    
}
