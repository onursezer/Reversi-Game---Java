/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.applet.Applet;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

/**
 *
 * @author Onur Sezer
 */
public class ReversiGui extends JPanel{

    JPanel panel;
    JPanel boardPanel;
    static JLabel score1;
    static JLabel score2;
    static JButton newGame;
    static JButton undo;
    static JButton [] cell;
    static Reversi board;
    static ArrayList<Reversi>  arrReversi= new ArrayList<Reversi>();
    static int countUndo = 0;
    
    static public int playerScore = 2; 
    static public int pcScore = 2;
    private static Reversi start;
    private static int rows = 8;
    private static int cols = 8;
    private static Color col = Color.green;
    
  
    public ReversiGui()
    {
        super(new BorderLayout());    
        setPreferredSize(new Dimension(800,700));
        setLocation(0, 0);
        
        board = new Reversi();
        start = board;
        arrReversi.add(new Reversi(board));

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,60));
        
        newGame = new JButton();
        newGame.setPreferredSize(new Dimension(80,50));
        try 
        {
            Image img = ImageIO.read(getClass().getResource("images/start.png"));
            newGame.setIcon(new ImageIcon(img));
        } catch (IOException ex) {}
        newGame.addActionListener(new Action());
        
        undo = new JButton();
        undo.setPreferredSize(new Dimension(80,50));
        try 
        {
            Image img2 = ImageIO.read(getClass().getResource("images/undo.png"));
            undo.setIcon(new ImageIcon(img2));
        } catch (IOException ex) {}
        undo.addActionListener(new Action());
        JLabel name = new JLabel();
        name.setText("Developed by Onur Sezer");
        name.setLocation(750, 680);
        panel.add(newGame);
        panel.add(undo);
        
        add(panel, BorderLayout.SOUTH);
        
        // Board Panel
        boardPanel = new JPanel(new GridLayout(8,8));
        cell = new JButton[64];
        int k=0;
        for(int row = 0; row < rows; row++) 
        {
            for(int colum=0; colum < cols; colum++) 
            {
                cell[k] = new JButton();
                cell[k].setSize(50, 50);
                cell[k].setBackground(Color.GREEN);
                cell[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if(board.gameCells[row][colum].getCh() == 'X')
                {
                    try 
                    {
                        Image img = ImageIO.read(getClass().getResource("images/dark.png"));
                        cell[k].setIcon(new ImageIcon(img));
                    } catch (IOException ex) {}
                }
                else if(board.gameCells[row][colum].getCh() == 'O')
                {
                    try 
                    {
                        Image img = ImageIO.read(getClass().getResource("images/light.png"));
                        cell[k].setIcon(new ImageIcon(img));
                    } catch (IOException ex) {}                    
                }
                else if(row == 2 && colum == 4 || row == 3 && colum == 5 || 
                        row == 4 && colum == 2 || row == 5 && colum == 3 )
                {
                    try 
                    {
                        Image img = ImageIO.read(getClass().getResource("images/legalMoveIcon.png"));
                        cell[k].setIcon(new ImageIcon(img));
                    } catch (IOException ex) {} 
                }
                cell[k].addActionListener(new Action());
                boardPanel.add(cell[k]);
                k++;
            }
        }
        add(boardPanel, BorderLayout.CENTER);
        
        
        JPanel scorePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        scorePanel.setPreferredSize(new Dimension(210,800));
        
        JLabel dark = new JLabel();
        try 
        {
            Image img = ImageIO.read(getClass().getResource("images/dark.png"));
            dark.setIcon(new ImageIcon(img));
        } catch (IOException ex) {}
        JLabel light = new JLabel();
        try 
        {
            Image img = ImageIO.read(getClass().getResource("images/light.png"));
            light.setIcon(new ImageIcon(img));
        } catch (IOException ex) {}
        score1 = new JLabel();
        score1.setText(" Player : " + playerScore + "  ");
        score1.setFont(new Font("Serif", Font.BOLD, 22));
        
        score2 = new JLabel();   
        score2.setText(" Computer : " + pcScore + "  ");
        score2.setFont(new Font("Serif", Font.BOLD, 22));        
               
        c.gridx = 0;
        c.gridy = 1;
        scorePanel.add(dark, c);  
        c.gridx = 1;
        c.gridy = 1;
        scorePanel.add(score1,c);
        
        
        c.gridx = 0;
        c.gridy = 2;
        scorePanel.add(light, c);  
        c.gridx = 1;
        c.gridy = 2;
        scorePanel.add(score2,c);
              
        add(scorePanel, BorderLayout.EAST);
        
        //scorePanel.add(light);
        //scorePanel.add(score2);
        
    }  
    static class Action implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource() == newGame)
            {
                board.reset();
                arrReversi.clear();
                arrReversi.add(new Reversi(start));
                int k = 0;
                for (int row = 0; row < rows; row++) 
                {
                    for (int colum = 0; colum < cols; colum++) 
                    {
                        cell[k].setIcon(null);
                        if(board.gameCells[row][colum].getCh() == 'X')
                        {
                            try 
                            {
                                Image img = ImageIO.read(getClass().getResource("images/dark.png"));
                                cell[k].setIcon(new ImageIcon(img));
                            } catch (IOException ex) {}
                        }
                        else if(board.gameCells[row][colum].getCh() == 'O')
                        {
                            try 
                            {
                                Image img = ImageIO.read(getClass().getResource("images/light.png"));
                                cell[k].setIcon(new ImageIcon(img));
                            } catch (IOException ex) {}                    
                        }
                        if(row == 2 && colum == 4 || row == 3 && colum == 5 || 
                        row == 4 && colum == 2 || row == 5 && colum == 3 )
                        {
                            try 
                            {
                                Image img = ImageIO.read(getClass().getResource("images/legalMoveIcon.png"));
                                cell[k].setIcon(new ImageIcon(img));
                            } catch (IOException ex) {} 
                        }
                        ++k;
                    }
                }
                playerScore = 2; pcScore = 2;
                score1.setText(" Player : " + playerScore + "  ");
                score2.setText(" Computer : " + pcScore + "  ");
            }
            else if(e.getSource() == undo)
            {
                countUndo++;
                int y,point;
                char c,x;
                int arr[] = new int[3];
                ArrayList <Integer> arrList = new ArrayList <Integer>();
                
                if(arrReversi.size() - countUndo - 1 >= 0)
                {
                    for (int i = 0; i < rows; i++) 
                    {
                        for (int j = 0; j < cols; j++) 
                        {
                            c = arrReversi.get(arrReversi.size() - countUndo - 1).gameCells[i][j].getCh();
                            x = arrReversi.get(arrReversi.size() - countUndo - 1).gameCells[i][j].getCorX();
                            y = arrReversi.get(arrReversi.size() - countUndo - 1).gameCells[i][j].getCorY();
                            board.gameCells[i][j].setPosition(x, c, y); 
                        }
                    }
                    int k = 0;
                    for (int row = 0; row < rows; row++) 
                    {
                        for (int colum = 0; colum < cols; colum++) 
                        {
                            cell[k].setIcon(null);
                            if(board.gameCells[row][colum].getCh() == 'X')
                            {
                                try 
                                {
                                    Image img = ImageIO.read(getClass().getResource("images/dark.png"));
                                    cell[k].setIcon(new ImageIcon(img));
                                } catch (IOException ex) {}
                            }
                            else if(board.gameCells[row][colum].getCh() == 'O')
                            {
                                try 
                                {
                                    Image img = ImageIO.read(getClass().getResource("images/light.png"));
                                    cell[k].setIcon(new ImageIcon(img));
                                } catch (IOException ex) {}                    
                            }
                            ++k;
                        }
                    }
                    board.findLegalMove(arrList);
                    for (int j = 0; j < arrList.size(); j += 2) 
                    {
                        try 
                        {
                            Image img = ImageIO.read(getClass().getResource("images/legalMoveIcon.png"));
                            cell[arrList.get(j)*rows + arrList.get(j + 1)].setIcon(new ImageIcon(img));
                        } catch (IOException ex) {}  
                    }
                    board.controlElements(arr);
                    playerScore = arr[0]; pcScore = arr[1]; point = arr[2];
                    score1.setText("Player : " + playerScore + "  ");
                    score2.setText("Computer : " + pcScore + "  "); 
                }
                
            }
            else
            {
                for (int i = 0; i < 64; i++) 
                {
                    if(e.getSource() == cell[i])  
                    {
                        int xCor, yCor;
                        int ct = -100, point;
                        int arr[] = new int[3];
                        System.out.println("button : "+ i);
                        if(i==0)
                        {
                            xCor=0;
                            yCor=0;
                        }
                        else
                        {
                            yCor =i%8;
                            xCor=i/8;
                        }
                        ct = board.play(xCor, yCor);
                        if(ct == 0)
                        {
                            arrReversi.add(new Reversi(board));
                            int k=0;
                            for(int row = 0; row < rows; row++) 
                            {
                                for(int colum=0; colum < cols; colum++) 
                                {
                                    if(board.gameCells[row][colum].getCh() == 'X')
                                    {
                                        try 
                                        {
                                            Image img = ImageIO.read(getClass().getResource("images/dark.png"));
                                            cell[k].setIcon(new ImageIcon(img));
                                        } catch (IOException ex) {}
                                    }
                                    else if(board.gameCells[row][colum].getCh() == 'O')
                                    {
                                        try 
                                        {
                                            Image img = ImageIO.read(getClass().getResource("images/light.png"));
                                            cell[k].setIcon(new ImageIcon(img));
                                        } catch (IOException ex) {}                    
                                    }
                                    k++;
                                }
                            }
                            board.controlElements(arr);
                            playerScore = arr[0]; pcScore = arr[1]; point = arr[2];
                            score1.setText("Player : " + playerScore + "  ");
                            score2.setText("Computer : " + pcScore + "  "); 
                        }
                        if(ct == 0 || ct == -1)
                        {    
                            board.play();
                            arrReversi.add(new Reversi(board));
                            ArrayList <Integer> arrList = new ArrayList <Integer>();
                            int k=0;
                            for(int row = 0; row < rows; row++) 
                            {
                                for(int colum=0; colum < cols; colum++) 
                                {
                                    if(board.gameCells[row][colum].getCh() == 'X')
                                    {
                                        try 
                                        {
                                            Image img = ImageIO.read(getClass().getResource("images/dark.png"));
                                            cell[k].setIcon(new ImageIcon(img));
                                        } catch (IOException ex) {}
                                    }
                                    else if(board.gameCells[row][colum].getCh() == 'O')
                                    {
                                        try 
                                        {
                                            Image img = ImageIO.read(getClass().getResource("images/light.png"));
                                            cell[k].setIcon(new ImageIcon(img));
                                        } catch (IOException ex) {}                    
                                    }
                                    else if(board.gameCells[row][colum].getCh() == '.')
                                    {
                                        cell[k].setIcon(null);
                                    }
                                    k++;
                                }
                            }
                            board.findLegalMove(arrList);
                            for (int j = 0; j < arrList.size(); j += 2) 
                            {
                                try 
                                {
                                    Image img = ImageIO.read(getClass().getResource("images/legalMoveIcon.png"));
                                    cell[arrList.get(j)*rows + arrList.get(j + 1)].setIcon(new ImageIcon(img));
                                } catch (IOException ex) {}  
                            }
                            board.controlElements(arr);
                            playerScore = arr[0]; pcScore = arr[1]; point = arr[2];
                            score1.setText("Player : " + playerScore + "  ");
                            score2.setText("Computer : " + pcScore + "  ");  
                        }
                    }

                }
                int st = board.endOfGame();
                if(st == 0)
                {
                    if(playerScore > pcScore)
                        JOptionPane.showMessageDialog(null,"No legal move!\nPlayer Win!","Game Over",JOptionPane.PLAIN_MESSAGE);   
                    else
                        JOptionPane.showMessageDialog(null,"No legal move!\nComputer Win!","Game Over",JOptionPane.PLAIN_MESSAGE);
                }
                else if(st == 1 || st == 3)
                {
                    JOptionPane.showMessageDialog(null,"Computer Win!","Game Over",JOptionPane.PLAIN_MESSAGE);
                }
                else if(st == 2 || st == 4)
                {
                    JOptionPane.showMessageDialog(null,"Player Win!","Game Over",JOptionPane.PLAIN_MESSAGE); 
                }
                else if(st == 3)
                {
                    JOptionPane.showMessageDialog(null,"Scoreless!","Game Over",JOptionPane.PLAIN_MESSAGE); 
                }
            }
        }
        
    }
    
}
