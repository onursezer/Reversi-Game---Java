/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Onur Sezer
 */
public class main extends JFrame{
    
    private static JPanel pnlLeft;
    
    public main() {
        super("Reversi - Developed by Onur Sezer");
        setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        setSize(width/2, height/2);

        // center the jframe on screen
        setLocationRelativeTo(null);
        
        pnlLeft = new ReversiGui();
        add(pnlLeft, BorderLayout.CENTER);
        
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new main();
    }
    
}
