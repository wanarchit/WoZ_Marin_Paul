package marin_paul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.text.*;

/**
 *
 * @author Paul
 */
public class Game extends JFrame implements ActionListener{
    
    private JButton myButton,myButton2;
    private JPanel myPanel, myPanelBars, myPanelG,gamePanel;
    private Player player;
    //private int nbNPC, nbChest;
    private KarmaBar karmaB;
    private LifeBar lifeB;
    private Room roomActu;
    private ArrayList<NPC> npcRoom; // List of NPC in the actual Room
    private ArrayList<Chest> chestRoom; // List of Chests in the actual room
    private ArrayList<Room> arrayListRoom;
    private boolean activeFight = false;
    private NPC npc = null;
    private Chest chest =null;
    private Boolean isSeller;
    private JPanel myPanelVide,myPanelMess;
    private Graphique gameGrid;
    private JLabel myLabelMess;
    private TextArea myTextArea;
    
    public Game(Player myPlayer, ArrayList<Room> listRoom){
        player = myPlayer;
        roomActu = player.getActualRoom();
        npcRoom = roomActu.getNPCRoom();
        chestRoom = roomActu.getChestRoom();
        arrayListRoom = listRoom;
        
        // my Button
        myButton = new JButton();
        //roomActu = new JButton();
        //myButton.setBorderPainted(false);
        myButton.setPreferredSize(new Dimension(200,62));
        myButton.setIcon(new ImageIcon("images/boutton_game.PNG"));
        myButton.setText("Inventory");
        myButton.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        myButton.setVerticalTextPosition(SwingConstants.CENTER);
        myButton.setHorizontalTextPosition(SwingConstants.CENTER); 
        myButton2 = new JButton();
        myButton2.setPreferredSize(new Dimension(200,62));
        myButton2.setIcon(new ImageIcon("images/boutton_game.PNG"));
        myButton2.setText("Map");
        myButton2.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        myButton2.setVerticalTextPosition(SwingConstants.CENTER);
        myButton2.setHorizontalTextPosition(SwingConstants.CENTER); 
        myPanel = new JPanel();
        myPanel.setLayout(new BorderLayout());
        myPanel.add(myButton,BorderLayout.EAST);
        myPanel.add(myButton2,BorderLayout.WEST);
        
        myPanelBars = new JPanel();
        myPanelBars.setLayout(new BorderLayout());
        lifeB = new LifeBar();
        lifeB.setValueBar(player.getHP());
        karmaB = new KarmaBar();
        karmaB.setValueBar(player.getKarma());
        myPanelBars.add(lifeB.getLifeBar(),BorderLayout.EAST);
        myPanelBars.add(karmaB.getKarmaBar(),BorderLayout.WEST);
        

        gameGrid = new Graphique(player, arrayListRoom, this);
        gamePanel = gameGrid.getPanel();

        myPanelVide = new JPanel(new BorderLayout());
        myPanelVide.setPreferredSize(new Dimension(200,200));
        myPanelMess = new JPanel(new BorderLayout());
        myPanelMess.setBorder(BorderFactory.createLineBorder(Color.black));
        
       
        myLabelMess = new JLabel("");
        myLabelMess.setHorizontalAlignment(JLabel.CENTER);
        myLabelMess.setVerticalAlignment(JLabel.CENTER);
        myLabelMess.setBorder(BorderFactory.createLineBorder(Color.black));
        myLabelMess.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        myPanelMess.add(myLabelMess,BorderLayout.CENTER);
        myPanelVide.add(myPanelMess,BorderLayout.CENTER);
        myPanelVide.setBorder(BorderFactory.createLineBorder(Color.black));
        
        /////////////////////////// Global Panel
        myPanelG= new JPanel();
        myPanelG.setLayout(new BorderLayout());
        myPanelG.add(myPanel,BorderLayout.SOUTH);
        myPanelG.add(myPanelBars,BorderLayout.NORTH);
        
        myPanelG.add(myPanelVide,BorderLayout.EAST);
        myPanelG.add(gamePanel,BorderLayout.CENTER);
        
        // myFrame
        this.setTitle("My Interface Test2");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(900,700));
        
        this.add(myPanelG);
        this.pack();
        this.setVisible(true);
        
        myButton.addActionListener(this);
        myButton2.addActionListener(this);
    }
    
    public void actionPerformed (ActionEvent e){
        if (e.getSource().equals(myButton)){
            InterfaceInventory inventoryFrame = new InterfaceInventory(player,npc,this);   
        }else if (e.getSource().equals(myButton2)){
            InterfaceMap mapFrame = new InterfaceMap(player,arrayListRoom);
        }               
    }
    
    public JPanel getGraph(){
        return gamePanel;
    }
    
    public void setGraph(){
        gameGrid = new Graphique(player, arrayListRoom, this);
        gamePanel = gameGrid.getPanel();
        myPanelVide = new JPanel(new BorderLayout());
        myPanelVide.setPreferredSize(new Dimension(200,200));
        myPanelMess = new JPanel(new BorderLayout());
        myPanelMess.setBorder(BorderFactory.createLineBorder(Color.black));
        myLabelMess = new JLabel("");
        myLabelMess.setHorizontalAlignment(JLabel.CENTER);
        myLabelMess.setVerticalAlignment(JLabel.CENTER);
        myLabelMess.setBorder(BorderFactory.createLineBorder(Color.black));
        myLabelMess.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        myPanelMess.add(myLabelMess,BorderLayout.CENTER);
        myPanelVide.add(myPanelMess,BorderLayout.CENTER);
        myPanelVide.setBorder(BorderFactory.createLineBorder(Color.black));
        myPanelG.add(myPanelVide,BorderLayout.EAST);
        myPanelG.add(gamePanel,BorderLayout.CENTER);
        this.revalidate();

        
    }
    
    public LifeBar getLifeBar(){
        return lifeB;
    }
    
    
    public KarmaBar getKarmaBar(){
        return karmaB;
    }

    public JFrame getMainFrame(){
        return this;
    }
    
    public boolean getActiveFight(){
        return activeFight;
    }
    
    public void setActiveFight(boolean choice){
        activeFight=choice;
    }
    
    public NPC getEnemyActive(){
        return npc;
    }
    
    public InterfaceBattle getInterfaceBattle(){
        return gameGrid.getInterfaceBattle();
    }
    /**
    public void setInterfaceBattle(NPC enemyNPC){
        npc = enemyNPC;
        myPanelBattle = new InterfaceBattle(player,npc,this);
        myPanelG.add(myPanelBattle,BorderLayout.EAST);
        myPanelG.setVisible(true);
    }**/
    
    public JPanel getPanelEast(){
        return myPanelG;
    }
    
    public Boolean isSeller(){
        return isSeller;
    }
    
    public JPanel getPanelVide(){
        return myPanelVide;
    }
    public void setPanelVide(){
        myPanelVide = new JPanel(new BorderLayout());
        myPanelVide.setPreferredSize(new Dimension(200,200));
        myPanelMess = new JPanel(new BorderLayout());
        myPanelMess.setBorder(BorderFactory.createLineBorder(Color.black));
        myLabelMess = new JLabel("");
        myLabelMess.setHorizontalAlignment(JLabel.CENTER);
        myLabelMess.setVerticalAlignment(JLabel.CENTER);
        myLabelMess.setBorder(BorderFactory.createLineBorder(Color.black));
        myLabelMess.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        myPanelMess.add(myLabelMess,BorderLayout.CENTER);
        myPanelVide.add(myPanelMess,BorderLayout.CENTER);
        myPanelVide.setBorder(BorderFactory.createLineBorder(Color.black));
        myPanelG.add(myPanelVide,BorderLayout.EAST);
    }
    
    public void defaultButton(JButton myBut,String title){
        myBut.setPreferredSize(new Dimension(200,62));
        myBut.setIcon(new ImageIcon("images/boutton_game.PNG"));
        myBut.setText(title);
        myBut.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        myBut.setForeground(Color.black);
        myBut.setVerticalTextPosition(SwingConstants.CENTER);
        myBut.setHorizontalTextPosition(SwingConstants.CENTER); 
    }
    
    public JLabel getLabelMess(){
        return myLabelMess;
    }
    public void setLabelMess(String str){
        myLabelMess.setText(str);
    }
}
