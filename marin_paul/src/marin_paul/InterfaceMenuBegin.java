package marin_paul;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Paul
 */
public class InterfaceMenuBegin extends JFrame implements ActionListener{
    
    private JButton paul,babu,clement,marion,marin,jo,start;
    private JFrame myFrame;
    private String choixPlayer;
    public InterfaceMenuBegin(){
        
        JPanel myPanelG1 = new JPanel();
        paul = new JButton("Paul");
        jo = new JButton("Jonathan");
        marin = new JButton("Marin");
        marion = new JButton("Marion");
        babu = new JButton("Babujhi");
        clement = new JButton("Clément");
        myPanelG1.add(paul);
        myPanelG1.add(jo);
        myPanelG1.add(marion);
        myPanelG1.add(clement);
        myPanelG1.add(marin);
        myPanelG1.add(babu);
        
        JPanel myPanelG2=new JPanel();
        start = new JButton("Start Game");
        start.setEnabled(false);
        myPanelG2.add(start);
        
        JPanel myPanelG = new JPanel();
        myPanelG.setLayout(new GridLayout(2,1));
        myPanelG.add(myPanelG1);
        myPanelG.add(myPanelG2);
        
        // myFrame
        myFrame = new JFrame("My Interface Test2");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setPreferredSize(new Dimension(750,550));
        
        myFrame.add(myPanelG);
        myFrame.pack();
        myFrame.setVisible(true);
        
        paul.addActionListener(this);
        babu.addActionListener(this);
        jo.addActionListener(this);
        marin.addActionListener(this);
        marion.addActionListener(this);
        clement.addActionListener(this);
        start.addActionListener(this);
        
        
        
    }
    
    public void actionPerformed (ActionEvent e){
        if (e.getSource().equals(babu)){
            choixPlayer = "Babujhi";
            start.setEnabled(true);
        }else if (e.getSource().equals(paul)){
            choixPlayer = "Paul";
            start.setEnabled(true);
        }else if (e.getSource().equals(marin)){
            choixPlayer = "Marin";
            start.setEnabled(true);
        }else if (e.getSource().equals(marion)){
            choixPlayer = "Marion";
            start.setEnabled(true);
        }else if (e.getSource().equals(clement)){
            choixPlayer = "Clément";
            start.setEnabled(true);
        }else if (e.getSource().equals(jo)){
            choixPlayer = "Jonathan";
            start.setEnabled(true);
        }else{  // start game
            InitializeGame2 obj_game = new InitializeGame2(choixPlayer);
            myFrame.dispose();
        }
    }
}
