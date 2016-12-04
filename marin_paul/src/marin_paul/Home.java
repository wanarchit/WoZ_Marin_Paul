
package marin_paul;

 import java.awt.*;
 import java.awt.GridLayout;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 import javax.swing.*;

///////////////////////////////////////////////////////////////

public class Home extends JFrame implements ActionListener {
    
   private final JButton but1;
   private final JButton but2;
   private final JButton but3;
   private final JButton but4;
   private final JButton but5;
   private final JButton but6;
   private final JButton butStart = new JButton ("Start game");
   private final JButton butChange =new JButton ("Change player");
   //////////////////
   private final JPanel panelBut1 = new JPanel();
   private final JLabel labBut1 = new JLabel ("Marion",JLabel.CENTER);
   private final JPanel pBut1 = new JPanel();
   private final JPanel panelBut2 = new JPanel();
   private final JLabel labBut2 = new JLabel ("Marin",JLabel.CENTER);
   private final JPanel pBut2 = new JPanel();
   private final JPanel panelBut3 = new JPanel();
   private final JLabel labBut3 = new JLabel ("Paul",JLabel.CENTER);
   private final JPanel pBut3 = new JPanel();
   private final JPanel panelBut4 = new JPanel();
   private final JLabel labBut4 = new JLabel ("Babujhi",JLabel.CENTER);
   private final JPanel pBut4 = new JPanel();
   private final JPanel panelBut5 = new JPanel();
   private final JLabel labBut5 = new JLabel ("Jonathan",JLabel.CENTER);
   private final JPanel pBut5 = new JPanel();
   private final JPanel panelBut6 = new JPanel();
   private final JLabel labBut6 = new JLabel ("Clément",JLabel.CENTER);
   private final JPanel pBut6 = new JPanel();
   //////////////////
   private final JPanel panelJoueur = new JPanel();
   private final JPanel panelStart2 = new JPanel();
   private final JPanel myMainPanel = new JPanel();
   private final JPanel panelStart1 = new JPanel();
   private final JPanel panelChange = new JPanel ();

    //////////////////
   private final JFrame myFrame = new JFrame();
    //////////////////
   private final JLabel label;
    //////////////////
   private final Icon pic1,pic2,pic3,pic4,pic5,pic6;
   
    //////////////////
   private int varForce=0;
   private int varDefense=0;
   //////////////////
   

   
///////////////////////////////////////////////////////////////
   
   public Home (){
       
       this.setTitle("Home page ! ");// Titre
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       //1er label de la frame 
       label = new JLabel ("Choose your player and click on start to begin your game!",JLabel.CENTER);
       label.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,35));
       /////////////////////
       labBut1.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
       labBut2.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
       labBut3.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
       labBut4.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
       labBut5.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
       labBut6.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
      
       
       ///////////////////
       pic1 = new ImageIcon("images/marion.png");
       pic2 = new ImageIcon("images/marin.PNG");
       pic3 = new ImageIcon("images/paul.PNG");
       pic4 = new ImageIcon("images/babujhi.PNG");
       pic5 = new ImageIcon("images/jonathan.PNG");
       pic6 = new ImageIcon("images/clement.PNG");
       
       
       ///////////////////
       but1 = new JButton (pic1);
       but1.setPreferredSize(new Dimension(128,128));
       but2 = new JButton (pic2);
       but2.setPreferredSize(new Dimension(128,128));
       but3 = new JButton (pic3);
       but3.setPreferredSize(new Dimension(128,128));
       but4 = new JButton (pic4);
       but4.setPreferredSize(new Dimension(128,128));
       but5 = new JButton (pic5);
       but5.setPreferredSize(new Dimension(128,128));
       but6 = new JButton (pic6);
       but6.setPreferredSize(new Dimension(128,128));
       ///////////////////
       
       //Panel joueur 1
       panelBut1.setLayout(new GridLayout(2,1));
       pBut1.add(but1);
       pBut1.setOpaque(false);
       panelBut1.add(pBut1);
       panelBut1.add(labBut1);
       panelBut1.setOpaque(false);
       ///////////////////
       //Panel joueur 2
       panelBut2.setLayout(new GridLayout(2,1));
       pBut2.add(but2);
       pBut2.setOpaque(false);
       panelBut2.add(pBut2);
       panelBut2.add(labBut2);
       panelBut2.setOpaque(false);
       ///////////////////
       //Panel joueur 3
       panelBut3.setLayout(new GridLayout(2,1));
       pBut3.add(but3);
       pBut3.setOpaque(false);
       panelBut3.add(pBut3);
       panelBut3.add(labBut3);
       panelBut3.setOpaque(false);
       ///////////////////
       //Panel joueur 4
       panelBut4.setLayout(new GridLayout(2,1));
       pBut4.add(but4);
       pBut4.setOpaque(false);
       panelBut4.add(pBut4);
       panelBut4.add(labBut4);
       panelBut4.setOpaque(false);
       ///////////////////
       //Panel joueur 5
       panelBut5.setLayout(new GridLayout(2,1));
       pBut5.add(but5);
       pBut5.setOpaque(false);
       panelBut5.add(pBut5);
       panelBut5.add(labBut5);
       panelBut5.setOpaque(false);
       ///////////////////
       //Panel joueur 6
       panelBut6.setLayout(new GridLayout(2,1));
       pBut6.add(but6);
       pBut6.setOpaque(false);
       panelBut6.add(pBut6);
       panelBut6.add(labBut6);
       panelBut6.setOpaque(false);
       
       
       /////////////////// 
       //Panel Joueur contenant tous les boutons et label des joueurs 
       panelJoueur.setLayout(new GridLayout(1,6));
       panelJoueur.add(panelBut1);
       panelJoueur.add(panelBut2);
       panelJoueur.add(panelBut3);
       panelJoueur.add(panelBut4);
       panelJoueur.add(panelBut5);
       panelJoueur.add(panelBut6);
       panelJoueur.setOpaque(false);
       
       panelStart2.setLayout(new GridLayout(1,2));
       panelStart2.add(panelStart1);
       panelStart2.add(panelChange);
       panelStart2.setOpaque(false);
       
       panelStart1.add(butStart);
       panelStart1.setOpaque(false);
       panelChange.add(butChange);
       panelChange.setOpaque(false);
       
       myMainPanel.setLayout(new GridLayout(3,1));
       myMainPanel.add(label);
       myMainPanel.add(panelJoueur);
       myMainPanel.add(panelStart2);
       myMainPanel.setOpaque(false);
        
       
       myFrame.pack();
       myFrame.add(myMainPanel);
       myFrame.setSize(1000, 700);
       myFrame.setVisible(true);
       
       //Action definition for every buttons
       but1.addActionListener(this); 
       but2.addActionListener(this);
       but3.addActionListener(this);
       but4.addActionListener(this);
       but5.addActionListener(this);
       but6.addActionListener(this);
       butStart.addActionListener(this);
       butChange.addActionListener(this);
       
       butStart.setEnabled(false);
       butChange.setEnabled(false);
               
   }
   
   //////////////////////////////////////////////////////////////////////////////
   public void actionPerformed(ActionEvent e)
   
  {
      if (e.getSource() == but1)
      {
          varForce = 5;
          varDefense  = 5; 
          butStart.setEnabled(true);
          butChange.setEnabled(true);
          but2.setEnabled(false);
          but3.setEnabled(false);
          but4.setEnabled(false);
          but5.setEnabled(false);
          but6.setEnabled(false);
      }
      else if (e.getSource() == but2)
      {
          varForce = 10;
          varDefense =1;
          butStart.setEnabled(true);
          butChange.setEnabled(true);
          but1.setEnabled(false);
          but3.setEnabled(false);
          but4.setEnabled(false);
          but5.setEnabled(false);
          but6.setEnabled(false);
      }
      else if (e.getSource() == but3)
      {
          varForce =1;
          varDefense =10;
          butStart.setEnabled(true);
          butChange.setEnabled(true);
          but1.setEnabled(false);
          but2.setEnabled(false);
          but4.setEnabled(false);
          but5.setEnabled(false);
          but6.setEnabled(false);
      }
      else if (e.getSource() == but4)
      {
          varForce = 8;
          varDefense =3;
          butStart.setEnabled(true);
          butChange.setEnabled(true);
          but1.setEnabled(false);
          but2.setEnabled(false);
          but3.setEnabled(false);
          but5.setEnabled(false);
          but6.setEnabled(false);
      }
      else if (e.getSource() == but5)
      {
         varForce =3;
         varDefense = 8;
         butStart.setEnabled(true);
         butChange.setEnabled(true);
         but1.setEnabled(false);
         but2.setEnabled(false);
         but3.setEnabled(false);
         but4.setEnabled(false);
         but6.setEnabled(false);
      }
      else if (e.getSource() == but6)
      {
          varForce =4;
          varDefense =6;
          butStart.setEnabled(true);
          butChange.setEnabled(true);
          but1.setEnabled(false);
          but2.setEnabled(false);
          but3.setEnabled(false);
          but4.setEnabled(false);
          but5.setEnabled(false);
          
      }
      else if (e.getSource() == butStart)
      {
         
      }
      else if (e.getSource() == butChange)
      {
          varForce =0;
          varDefense =0;
          butStart.setEnabled(false);
          butChange.setEnabled(false);
          but1.setEnabled(true);
          but2.setEnabled(true);
          but3.setEnabled(true);
          but4.setEnabled(true);
          but5.setEnabled(true);
          but6.setEnabled(true);
      }
  }
}