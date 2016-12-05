package marin_paul;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Paul
 */
public class InterfaceSeller extends JPanel implements ActionListener{
    
    //private JPanel ? ;
    private JLabel labelName, labelText;
    private JButton trade, end, attack, speak;
    private Player p;
    private NPC seller;
    private Icon imgAttack = new ImageIcon("images/attack.PNG");;
    private Graphique mainPanel;
    private Game mainFrame;
    private JLabel myLabel,myLabelMsg,myLabelRound,myLabelDegP,myLabelDegE;
    private JPanel ssP,ssP1,ssP2,ssP3,ssP4,ssP5,ssP6;
    private NPC enemy;
    private LifeBar lifeB,lifeB2;
    private KarmaBar karmaB;
    private int round;
    private Icon imgRunAway = new ImageIcon("images/fuite2.PNG");
    //private JFrame mainFrame;
    
    public InterfaceSeller(Player myPlayer, NPC enemyNPC, Graphique panel){
        p = myPlayer;
        seller = enemyNPC;
        mainPanel = panel;
        mainFrame = mainPanel.getMainFrame();
        lifeB = mainFrame.getLifeBar();
        karmaB = mainFrame.getKarmaBar();
        imgAttack = new ImageIcon("images/attack.PNG");

        
       this.setLayout(new GridLayout(6,1));
        GridBagConstraints gbc = new GridBagConstraints();
        myLabel = new JLabel("NPC life : ");
        myLabel.setHorizontalAlignment(JLabel.CENTER);
        myLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        lifeB2 = new LifeBar();
        lifeB2.setValueBar(seller.getHP());
        lifeB2.getLifeBar().setForeground(Color.red);
        ssP = new JPanel();
        ssP.setLayout(new GridLayout(2,1));
        ssP.setBorder(BorderFactory.createLineBorder(Color.black));
        ssP.add(myLabel);
        ssP.add(lifeB2.getLifeBar());
        ssP1 = new JPanel();
        ssP1.setLayout(new GridBagLayout());
        ssP1.add(ssP,gbc);
        
        if (seller.getHP() == 0){
            myLabelMsg = new JLabel(seller.getName()+ " is dead !");
        }else{
            myLabelMsg = new JLabel("You trade with ");
        }
        myLabelRound = new JLabel(seller.getName());
        myLabelDegP = new JLabel("");
        myLabelDegE = new JLabel("");
        myLabelMsg.setHorizontalAlignment(JLabel.CENTER);
        myLabelMsg.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        myLabelRound.setHorizontalAlignment(JLabel.CENTER);
        myLabelRound.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        myLabelDegP.setHorizontalAlignment(JLabel.CENTER);
        myLabelDegP.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        myLabelDegE.setHorizontalAlignment(JLabel.CENTER);
        myLabelDegE.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        
        ssP2 = new JPanel();
        ssP2.setLayout(new GridLayout(4,1));
        ssP2.add(myLabelMsg);
        ssP2.add(myLabelRound);
        ssP2.add(myLabelDegP);
        ssP2.add(myLabelDegE);
        
        speak = new JButton();
        mainPanel.defaultButton2(speak,"Speak");
        ssP3 = new JPanel();
        ssP3.setLayout(new GridBagLayout());
        ssP3.add(speak,gbc);
        trade = new JButton();
        mainPanel.defaultButton2(trade,"Trade");
        ssP4 = new JPanel();
        ssP4.setLayout(new GridBagLayout());
        ssP4.add(trade,gbc);
        attack = new JButton("");
        mainPanel.defaultButton2(attack,"Attack!");
        ssP5 = new JPanel();
        ssP5.setLayout(new GridBagLayout());
        ssP5.add(attack,gbc);
        end = new JButton("Close");
        mainPanel.defaultButton2(end,"Close");
        ssP6 = new JPanel();
        ssP6.setLayout(new GridBagLayout());
        ssP6.add(end,gbc);


        this.add(ssP1);
        this.add(ssP2);
        this.add(ssP3);
        this.add(ssP4);
        this.add(ssP5);
        this.add(ssP6);
        this.setPreferredSize(new Dimension(200,200));
        //this.setVisible(false);
        
        speak.addActionListener(this);
        attack.addActionListener(this);
        trade.addActionListener(this);
        //beat.addActionListener(this);
        //escape.addActionListener(this);
        end.addActionListener(this);
        //search.addActionListener(this);
    }
    
    public void actionPerformed (ActionEvent e){
        if (e.getSource().equals(speak)){
            InterfaceDialogue2 dialog = new InterfaceDialogue2(p,seller,mainFrame);
        
        }else if (e.getSource().equals(attack)){
            Object[]options = {"Attack !","Stay friendly !"};
                
            int option = JOptionPane.showOptionDialog(this,
            "Are you sure you want to attack "+seller.getName(), //Question de confirmation
            "Confirmation :",//nom de la boîte d'affichage de la question
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            imgAttack,   
            options,
            options[1]); // par default précliqué sur 0=yes, 1 = no
            
            if(option == JOptionPane.YES_OPTION){
                seller.setHostile(true);
                this.setVisible(false);
                mainFrame.setActiveFight(true);
                mainPanel.setInterfaceBattle(seller);
                mainFrame.revalidate();
            }
    
        }else if (e.getSource().equals(end)){
            this.setVisible(false);
            //mainFrame.setPanelVide();
            //mainFrame.getPanelEast().add(mainFrame.getPanelVide(),BorderLayout.EAST);
            JButton[] arrayButtons = mainPanel.getNPCButton();
            for (int j =0;j<mainPanel.getNbNPC();j++){
                arrayButtons[j].setEnabled(true);  
            }
            JButton[] arrayButtons2 = mainPanel.getChestButton();
            for (int j =0;j<mainPanel.getNbChest();j++){
                arrayButtons2[j].setEnabled(true);  
            }
            mainPanel.activateDoorBut();
            mainFrame.setActiveFight(false);
            
        
        
        }else if (e.getSource().equals(trade)){
            if(seller.getName().equals("Princess")){
                JFrame messFrame = new JFrame();
                JOptionPane d = new JOptionPane();
                d.showMessageDialog(messFrame, "I have nothing to sell <3", 
                "", JOptionPane.INFORMATION_MESSAGE);
            }else{
            new InterfaceTrade(p,seller);}
        }
                
                
    }
}

    

