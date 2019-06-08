/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author xavie
 */
public class Fenetre {

    private JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private final String name="";

    public Fenetre(String name) {
        frame=new JFrame(name);
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(2,1));
        frame.setVisible(true);
        
    }

    Fenetre() {
       
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }
    
    
}
