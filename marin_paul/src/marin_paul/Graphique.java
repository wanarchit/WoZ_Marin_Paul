package marin_paul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Paul
 */
public class Graphique extends JPanel implements ActionListener{
    
    private JButton arrayNPC[],arrayChest[],arrayDoor[];
    private InterfaceBattle myPanelBattle;
    private InterfaceSeller myPanelSeller;
    private InterfaceChest myPanelChest;
    private JPanel gameGrid;
    private Player player;
    private int nbNPC, nbChest, nbDoor;
    private Room roomActu;
    private ArrayList<NPC> npcRoom; // List of NPC in the actual Room
    private ArrayList<Chest> chestRoom; // List of Chests in the actual room
    private ArrayList<Room> arrayListRoom;
    private HashMap <String, Door> exitAvailable; // List of exit (door + name) in the actual room
    private boolean activeFight = false;
    private NPC npc = null;
    private Chest chest =null;
    private Boolean isSeller;
    private Door doorN,doorS,doorE,doorW;
    private boolean north,south,east,west;
    private JButton doorSouth,doorNorth,doorWest,doorEast;
    private int min=0;
    private int max=24;
    private int rdVal;
    ArrayList<Integer> listNPC;
    ArrayList<Integer> listChest;
    private Game mainFrame;
    private JPanel myPanelVide;
    
    public Graphique(Player myPlayer, ArrayList<Room> listRoom, Game main){
        mainFrame = main;
        player = myPlayer;
        roomActu = player.getActualRoom();
        npcRoom = roomActu.getNPCRoom();
        chestRoom = roomActu.getChestRoom();
        arrayListRoom = listRoom;
        exitAvailable = roomActu.getHashMap();
        nbNPC = npcRoom.size();
        nbChest = chestRoom.size();
        nbDoor = exitAvailable.size();
        
        arrayNPC = new JButton[nbNPC];
        arrayChest = new JButton[nbChest];
        arrayDoor = new JButton[nbDoor];
        
        listNPC = new ArrayList<Integer>();
        listChest = new ArrayList<Integer>();

        //----------------------------------------------------------------------
        ArrayList<Integer> listPos = new ArrayList<Integer>();
        listPos.add(22);
        listPos.add(2);
        listPos.add(10);
        listPos.add(14);
        //----------------------------------------------------------------------
        //-----------------------------GAME GRID--------------------------------
        gameGrid = new JPanel();
        gameGrid.setLayout(new GridLayout(5,5));
        exitAvailable=roomActu.getHashMap();
        if(exitAvailable.containsKey("South")){
            south=true;
        }
        if(exitAvailable.containsKey("North")){
            north=true;
        }
        if(exitAvailable.containsKey("West")){
            west=true;
        }
        if(exitAvailable.containsKey("East")){
            east=true;
        }        

        //----------------------------------------------------------------------
        for(int cpt=0; cpt<nbNPC;cpt++){
            rdVal=ThreadLocalRandom.current().nextInt(min, max + 1);
            while(listPos.contains(rdVal)){
                    rdVal=ThreadLocalRandom.current().nextInt(min, max + 1);
            }
            listNPC.add(rdVal);
            listPos.add(rdVal);
        }
        
        for(int cpt=0; cpt<nbChest;cpt++){
            rdVal=ThreadLocalRandom.current().nextInt(min, max + 1);
            while(listPos.contains(rdVal)){
                    rdVal=ThreadLocalRandom.current().nextInt(min, max + 1);
            }
            listChest.add(rdVal);
            listPos.add(rdVal);
        }
        //----------------------------------------------------------------------
        int i =0; int j =0; int k=0;
        for(int nbButt=0; nbButt <25; nbButt++){
        //----------------------------------------------------------------------
        //-----------------------------Adding Doors-----------------------------

                if(south==true && nbButt==22)
                {
                    for (HashMap.Entry<String, Door> entry : exitAvailable.entrySet()) {
                        if (entry.getKey().equals("South")) {doorS = entry.getValue();}}
                    doorSouth = new JButton(doorS.getPicture());
                    doorSouth.setBorderPainted(false);
                    doorSouth.setPreferredSize(new Dimension(100,100));
                    doorSouth.setMinimumSize(new Dimension(100,100));
                    gameGrid.add(doorSouth);
                    //arrayDoor[k].add(doorSouth);
                    doorSouth.addActionListener(this);
                    k++;
                }
                else if(north==true && nbButt==2)
                {
                    for (HashMap.Entry<String, Door> entry : exitAvailable.entrySet()) {
                        if (entry.getKey().equals("North")) {doorN = entry.getValue();}}
                    doorNorth = new JButton(doorN.getPicture());
                    doorNorth.setBorderPainted(false);
                    doorNorth.setPreferredSize(new Dimension(100,100));
                    doorNorth.setMinimumSize(new Dimension(100,100));
                    gameGrid.add(doorNorth);
                    //arrayDoor[k].add(doorNorth);
                    doorNorth.addActionListener(this);
                    k++;
                }
                else if(west==true && nbButt==10)
                {
                    for (HashMap.Entry<String, Door> entry : exitAvailable.entrySet()) {
                        if (entry.getKey().equals("West")) {doorW = entry.getValue();}}
                    doorWest = new JButton(doorW.getPicture());
                    doorWest.setBorderPainted(false);
                    doorWest.setPreferredSize(new Dimension(100,100));
                    doorWest.setMinimumSize(new Dimension(100,100));
                    gameGrid.add(doorWest);
                    //arrayDoor[k].add(doorWest);
                    doorWest.addActionListener(this);
                    k++;
                }
                else if(east==true && nbButt==14)
                {
                    for (HashMap.Entry<String, Door> entry : exitAvailable.entrySet()) {
                        if (entry.getKey().equals("East")) {doorE = entry.getValue();}}
                    doorEast = new JButton(doorE.getPicture());
                    doorEast.setBorderPainted(false);
                    doorEast.setPreferredSize(new Dimension(100,100));
                    doorEast.setMinimumSize(new Dimension(100,100));
                    gameGrid.add(doorEast);
                    //arrayDoor[k].add(doorEast);
                    doorEast.addActionListener(this);
                    k++;
                }
                
        //----------------------------------------------------------------------

                
        //-----------------------------Adding NPC-------------------------------
                else if(listNPC.contains(nbButt)){             
                        arrayNPC[i] = new JButton(npcRoom.get(i).getIconChar());
                        arrayNPC[i].setBorderPainted(false);
                        arrayNPC[i].setPreferredSize(new Dimension(100,100));
                        arrayNPC[i].setMinimumSize(new Dimension(100,100));
                        gameGrid.add(arrayNPC[i]);
                        arrayNPC[i].addActionListener(this);
                        i++;
                    }
        //----------------------------------------------------------------------
        //-----------------------------Adding Chests----------------------------        
                else if(listChest.contains(nbButt)){
                    arrayChest[j] = new JButton(chestRoom.get(j).getIconChest());
                    arrayChest[j].setBorderPainted(false);
                    arrayChest[j].setPreferredSize(new Dimension(100,100));
                    arrayChest[j].setMinimumSize(new Dimension(100,100));
                    gameGrid.add(arrayChest[j]);
                    arrayChest[j].addActionListener(this);
                    j++;
                }
        //----------------------------------------------------------------------
        //-----------------------------Adding Floor-----------------------------        
                else{   
                    ImageIcon dalle1 = new ImageIcon("images/dalleFinal.png");
                    JButton button2 = new JButton(dalle1);
                    button2.setBorderPainted(false);
                   button2.setPreferredSize(new Dimension(100,100));
                    button2.setMinimumSize(new Dimension(100,100));
                    gameGrid.add(button2);
                }
                
            }
    }
    
    public void actionPerformed (ActionEvent e){
        if (e.getSource().equals(doorSouth)){
            if (actionDoor(doorS)){
                if (myPanelVide!=null){
                    myPanelVide.setVisible(false);
                }else{
                    mainFrame.getPanelVide().setVisible(false);
                }
                mainFrame.getGraph().setVisible(false);
                player.setActualRoom(exitAvailable.get("South").getNextRoom());
                mainFrame.setGraph();
            }else{  // No key
                if (doorS.getClass() == MagicDoor.class){
                    mainFrame.setLabelMess("<html>You do not have "
                            + "the good password to open the door");
                }else{
                    mainFrame.setLabelMess("<html>You do not have "
                            + "the key to open the door");
                }
            }
        }
        else if (e.getSource().equals(doorNorth)){
            if (actionDoor(doorN)){
        if (myPanelVide!=null){
            myPanelVide.setVisible(false);
        }else{
            mainFrame.getPanelVide().setVisible(false);}
        mainFrame.getGraph().setVisible(false);
        player.setActualRoom(exitAvailable.get("North").getNextRoom());
        mainFrame.setGraph();
        }else {
            if (doorN.getClass() == MagicDoor.class){
                    mainFrame.setLabelMess("<html>You do not have "
                            + "the good password to open the door");
                }else{
                    mainFrame.setLabelMess("<html>You do not have "
                            + "the key to open the door");
                }
            }
        }
        else if (e.getSource().equals(doorEast)){
            if (actionDoor(doorE)){
        if (myPanelVide!=null){
            myPanelVide.setVisible(false);
        }else{
            mainFrame.getPanelVide().setVisible(false);}
        mainFrame.getGraph().setVisible(false);
        player.setActualRoom(exitAvailable.get("East").getNextRoom());
        mainFrame.setGraph();
        }else{  // No key
                if (doorE.getClass() == MagicDoor.class){
                    mainFrame.setLabelMess("<html>You do not have "
                            + "the good password to open the door");
                }else{
                    mainFrame.setLabelMess("<html>You do not have "
                            + "the key to open the door");
                }
            }
        }
        else if (e.getSource().equals(doorWest)){
            if(actionDoor(doorW)){
        if (myPanelVide!=null){
            myPanelVide.setVisible(false);
        }else{
            mainFrame.getPanelVide().setVisible(false);}
        mainFrame.getGraph().setVisible(false);
        player.setActualRoom(exitAvailable.get("West").getNextRoom());
        mainFrame.setGraph();
        }else {
                if (doorW.getClass() == MagicDoor.class){
                    mainFrame.setLabelMess("<html>You do not have "
                            + "the good password to open the door");
                }else{
                    mainFrame.setLabelMess("<html>You do not have "
                            + "the key to open the door");
                }
            }
        }
        for (int i =0;i<nbNPC;i++){
            if (e.getSource() == arrayNPC[i]){
                npc = npcRoom.get(i);
                if (npc.isHostile()){
                    isSeller = false;
                    activeFight = true;
                    if (myPanelVide!=null){
                        myPanelVide.setVisible(false);
                    }else{
                    mainFrame.getPanelVide().setVisible(false);}
                    myPanelBattle = new InterfaceBattle(player,npc,this);
                    mainFrame.getPanelEast().add(myPanelBattle,BorderLayout.EAST);
                    for (int j =0;j<nbNPC;j++){
                         arrayNPC[j].setEnabled(false);  
                    }
                    for (int j =0;j<nbChest;j++){
                         arrayChest[j].setEnabled(false);  
                    }
                    disableDoorBut();
                    mainFrame.revalidate();
                }else{
                    isSeller = true;
                    if (myPanelVide!=null){
                        myPanelVide.setVisible(false);
                    }else{
                    mainFrame.getPanelVide().setVisible(false);}
                    myPanelSeller = new InterfaceSeller(player,npc,this);
                    mainFrame.getPanelEast().add(myPanelSeller,BorderLayout.EAST);
                    mainFrame.revalidate();
                    for (int j =0;j<nbNPC;j++){
                         arrayNPC[j].setEnabled(false);  
                    }
                    for (int j =0;j<nbChest;j++){
                         arrayChest[j].setEnabled(false);  
                    }
                    disableDoorBut();
                }
            }
        }
            for (int i =0;i<nbChest;i++){
                if (e.getSource() == arrayChest[i]){
                    chest = chestRoom.get(i);
                    if (myPanelVide!=null){
                        myPanelVide.setVisible(false);
                    }else{
                    mainFrame.getPanelVide().setVisible(false);}
                    myPanelChest = new InterfaceChest(player,chest,this);
                    mainFrame.getPanelEast().add(myPanelChest,BorderLayout.EAST);
                    for (int j =0;j<nbNPC;j++){
                         arrayNPC[j].setEnabled(false);  
                    }
                    for (int j =0;j<nbChest;j++){
                         arrayChest[j].setEnabled(false);  
                    }
                    disableDoorBut();

                    mainFrame.revalidate();
                }
            }
            
                   
    }

    public Game getMainFrame(){
        return mainFrame;
    }
    
    public void activateDoorBut(){
        if(north){doorNorth.setEnabled(true);}
        if(south){doorSouth.setEnabled(true);}
        if(east){doorEast.setEnabled(true);}
        if(west){doorWest.setEnabled(true);}
        mainFrame.setPanelVide();
    }
    
    public void disableDoorBut(){
        if(north){doorNorth.setEnabled(false);}
        if(south){doorSouth.setEnabled(false);}
        if(east){doorEast.setEnabled(false);}
        if(west){doorWest.setEnabled(false);}
    }
    
    public JButton[] getNPCButton(){
        return arrayNPC;
    }
    
    public JButton[] getChestButton(){
        return arrayChest;
    }
    
    public int getNbNPC(){
        return nbNPC;
    }
    
    public int getNbChest(){
        return nbChest;
    }
    
    public int getNbDoor(){
        return nbDoor;
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
        return myPanelBattle;
    }
    
    public void setInterfaceBattle(NPC enemyNPC){
        npc = enemyNPC;
        myPanelBattle = new InterfaceBattle(player,npc,this);
        mainFrame.getPanelEast().add(myPanelBattle,BorderLayout.EAST);
        mainFrame.getPanelEast().setVisible(true);
    }
    
    public JPanel getPanelEast(){
        return mainFrame.getPanelEast();
    }
    
    public Boolean isSeller(){
        return isSeller;
    }
    
    public JPanel getPanel(){
        return gameGrid;
    }
    
    public void move(String s){
        if (myPanelVide!=null){
            myPanelVide.setVisible(false);
        }else{
            mainFrame.getPanelVide().setVisible(false);}
        mainFrame.getGraph().setVisible(false);
        player.setActualRoom(exitAvailable.get(s).getNextRoom());
        mainFrame.setGraph();
    }
    
    public void defaultButton2(JButton myBut,String title){
        myBut.setPreferredSize(new Dimension(145,42));
        myBut.setIcon(new ImageIcon("images/button.PNG"));
        myBut.setText(title);
        myBut.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        myBut.setForeground(new Color(209,0,0));
        myBut.setVerticalTextPosition(SwingConstants.CENTER);
        myBut.setHorizontalTextPosition(SwingConstants.CENTER); 
    }
    
    public Boolean actionDoor(Door d){
        if (d.getClass() == LockedDoor.class){
            if(player.getInventory().checkInv(((LockedDoor)d).getKey())){
                return true;
            }else{return false;}
        }else if (d.getClass() == MagicDoor.class){
             String inputValue = JOptionPane.showInputDialog("It is a Magic Door ! You must enter the correct password ...");
             if(inputValue.equals(((MagicDoor)d).getPwd())){
                 return true;
             }else{
                 return false;
             }
        }else{
            return true;
        }
    }
    /**public boolean isClean(){
        for (NPC n : npcRoom){
            if (n.isHostile()){
                return false;
            }else{=true;}
        }
    }**/
}
