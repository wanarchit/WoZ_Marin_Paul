package marin_paul;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saspaanithy
 */


public class InterfaceDialogue extends JFrame implements ActionListener{
    
            JPanel myPanel = new JPanel();
            JButton myButton = new JButton();
            JFrame myFrame = new JFrame("GridLayout");
            JLabel myLabel = new JLabel("The game start in a donjon", SwingConstants.CENTER);
            String change = "1";
            int cpt = 0;

            

    public InterfaceDialogue (){
      
        myPanel.setLayout( new BorderLayout());

        myLabel.setHorizontalAlignment(JLabel.CENTER);
        myLabel.setVerticalAlignment(JLabel.CENTER);

        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(new BorderLayout());
        
     //myPanel.setLayout(new GridLayout(5,5));
        myButton.setLayout(new GridLayout(3,3));
        myButton.add(myLabel);
        
        
        myButton.addActionListener(this);
        
        

        myPanel.add(myButton);
        myFrame.add(myPanel, BorderLayout.SOUTH);
        
        
        Font font = new Font("Arial",Font.BOLD,15);
        myLabel.setFont(font);
        myFrame.pack();
        myFrame.setSize(500, 500);
        
        myFrame.setVisible(true);        
        
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
        if(cpt == 0){
            myLabel.setText("<html>The player is looking <br> for going out of the donjon</html>");
            cpt = 1;
        }
        
        else if(cpt == 1){
            myLabel.setText("YOLOOOOOOO");
            cpt = 2;
        }
            else if(cpt == 2){
                   
                myLabel.setVisible(false);
                myButton.setVisible(false);
                    }
        }

    }
   
