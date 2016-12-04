package marin_paul;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author Paul
 */
public class InterfaceMap extends JFrame implements ActionListener {
    
    private JFrame myFrame;
    private ArrayList<Room> listRoom;
    private JButton butClose,roomActu;
    private JButton arrayButton[];
    private JPanel panRoomActu;
    private JLabel labelExt;
    private JLabel labelNameR,labelClear,labelNPC1,labelNPC2,labelNPC3,labelChest;
    
    public InterfaceMap(Player p,ArrayList<Room> list){
        listRoom = list;
        arrayButton = new JButton[25];
        int i = 0;
        
        for (Room r : listRoom){
            arrayButton[i] = new JButton(r.getPicture());
            arrayButton[i].setPreferredSize(new Dimension(140,140));
            arrayButton[i].setBorderPainted(false);
            if (!r.isVisited()){
                arrayButton[i].setEnabled(false);
            }
            i++;
        }
        
        //labelExt = new JLabel(new ImageIcon("images/picRoomExt.PNG"));
        for (int j =21;j<25;j++){
            arrayButton[j] = new JButton(new ImageIcon("images/picRoomExtb.PNG"));
            arrayButton[j].setPreferredSize(new Dimension(140,140));
            arrayButton[j].setBorderPainted(false);
        }
        
        JPanel myPanelButton = new JPanel(new GridLayout(5,5));
        myPanelButton.add(arrayButton[4]);
        myPanelButton.add(arrayButton[5]);
        myPanelButton.add(arrayButton[12]);
        myPanelButton.add(arrayButton[13]);
        myPanelButton.add(arrayButton[20]);
        myPanelButton.add(arrayButton[3]);
        myPanelButton.add(arrayButton[6]);
        myPanelButton.add(arrayButton[11]);
        myPanelButton.add(arrayButton[14]);
        myPanelButton.add(arrayButton[19]);
        myPanelButton.add(arrayButton[2]);
        myPanelButton.add(arrayButton[7]);
        myPanelButton.add(arrayButton[10]);
        myPanelButton.add(arrayButton[15]);
        myPanelButton.add(arrayButton[18]);
        myPanelButton.add(arrayButton[1]);
        myPanelButton.add(arrayButton[8]);
        myPanelButton.add(arrayButton[9]);
        myPanelButton.add(arrayButton[16]);
        myPanelButton.add(arrayButton[17]);
        myPanelButton.add(arrayButton[0]);
        myPanelButton.add(arrayButton[21]);
        myPanelButton.add(arrayButton[22]);
        myPanelButton.add(arrayButton[23]);
        myPanelButton.add(arrayButton[24]);
        
////////////////////////TITLE/////////////////////////////////
        
        JPanel myPanelTitle = new JPanel();
        JLabel myLabelTitle = new JLabel("MAP");
        myPanelTitle.add(myLabelTitle);
        
/////////////////////////INFORMATION ROOM////////////////////////////////
            // PICTURE ROOM
        panRoomActu = new JPanel();
        roomActu = new JButton();
        roomActu.setBorderPainted(false);
        roomActu.setPreferredSize(new Dimension(300,300));
        roomActu.setIcon(new ImageIcon("images/picRoomEmpty.PNG"));
        roomActu.setText("Click on visited room");
        roomActu.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        roomActu.setVerticalTextPosition(SwingConstants.CENTER);
        roomActu.setHorizontalTextPosition(SwingConstants.CENTER); 
        panRoomActu.add(roomActu);
        
            //INFORMATION
        JLabel labName = new JLabel("Room Name: ");
        labName.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        labName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNameR = new JLabel("");
        labelNameR.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        labelNameR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JPanel subPan1 = new JPanel(new GridLayout(2,1));
        subPan1.add(labName);
        subPan1.add(labelNameR);
        labelClear = new JLabel("Clean ? ");
        labelClear.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        labelClear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabel labNPC = new JLabel("NPC: ");
        labNPC.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        labNPC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNPC1 = new JLabel("");
        labelNPC1.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        labelNPC1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNPC2 = new JLabel("");
        labelNPC2.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        labelNPC2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNPC3 = new JLabel("");
        labelNPC3.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        labelNPC3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JPanel subPan2 = new JPanel(new GridLayout(4,1));
        subPan2.add(labNPC);
        subPan2.add(labelNPC1);
        subPan2.add(labelNPC2);
        subPan2.add(labelNPC3);
        labelChest = new JLabel("Chest: ");
        labelChest.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        labelChest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        JPanel myPanelBody = new JPanel(new GridLayout(4,1));
        myPanelBody.add(subPan1);
        myPanelBody.add(labelClear);
        myPanelBody.add(subPan2);
        myPanelBody.add(labelChest);

        // myPanelBodyG
        JPanel myPanelBodyG = new JPanel(new BorderLayout());
        myPanelBodyG.add(panRoomActu,BorderLayout.NORTH);
        myPanelBodyG.add(myPanelBody,BorderLayout.CENTER);
        
////////////////////////CLOSE BUTTON/////////////////////////////////
        butClose = new JButton("Close MAP");
        JPanel panButClose = new JPanel();
        panButClose.add(butClose);
        
/////////////////////////Global EAST Panel////////////////////////////        
        JPanel globalPan = new JPanel(new BorderLayout());
        globalPan.add(myPanelTitle,BorderLayout.NORTH);
        globalPan.add(myPanelBodyG,BorderLayout.CENTER);
        globalPan.add(panButClose,BorderLayout.SOUTH);
        
        
        JPanel finalPan = new JPanel(new BorderLayout());
        finalPan.add(myPanelButton,BorderLayout.CENTER);
        finalPan.add(globalPan,BorderLayout.EAST);
        
        // Frame
        myFrame = new JFrame();
        
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame.add(finalPan);
        myFrame.setUndecorated(true);
        myFrame.setResizable(false);
        //myFrame.setPreferredSize(new Dimension(1280,700));
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        
        for (int k =0;k<21;k++){
            arrayButton[k].addActionListener(this);
        }
        butClose.addActionListener(this);
    }
    
    public void actionPerformed (ActionEvent e){
        if (e.getSource().equals(butClose)){
            myFrame.dispose();
        }else{ 
            for (int i =0;i<21;i++){
                arrayButton[i].setBorderPainted(false);
            }
            for (int i =0;i<21;i++){
                if (e.getSource() == arrayButton[i]){
                    arrayButton[i].setBorderPainted(true);
                    arrayButton[i].setBorder(BorderFactory.createLineBorder(Color.red,4));
                    labelNameR.setText(listRoom.get(i).getNameRoom());
                    if (listRoom.get(i).getNameRoom().equals("Exit")){
                        roomActu.setIcon(new ImageIcon("images/picRoom0.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Enter")){
                        roomActu.setIcon(new ImageIcon("images/picRoom1.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Little Room")){
                        roomActu.setIcon(new ImageIcon("images/picRoom2.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Little Corridor")){
                        roomActu.setIcon(new ImageIcon("images/picRoom3.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Dining Room")){
                        roomActu.setIcon(new ImageIcon("images/picRoom4.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Crypt")){
                        roomActu.setIcon(new ImageIcon("images/picRoom5.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Dark Corridor")){
                        roomActu.setIcon(new ImageIcon("images/picRoom6.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Treasure Room")){
                        roomActu.setIcon(new ImageIcon("images/picRoom7.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Perch of Dragons")){
                        roomActu.setIcon(new ImageIcon("images/picRoom8.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Crossroads Room")){
                        roomActu.setIcon(new ImageIcon("images/picRoom9.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Strange Room")){
                        roomActu.setIcon(new ImageIcon("images/picRoom10.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Cellar")){
                        roomActu.setIcon(new ImageIcon("images/picRoom11.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Fouras Room")){
                        roomActu.setIcon(new ImageIcon("images/picRoom12.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Torture Chamber")){
                        roomActu.setIcon(new ImageIcon("images/picRoom13.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Tavern")){
                        roomActu.setIcon(new ImageIcon("images/picRoom14.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Lair of the Lich")){
                        roomActu.setIcon(new ImageIcon("images/picRoom15.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Antechamber")){
                        roomActu.setIcon(new ImageIcon("images/picRoom16.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Bedroom Princess")){
                        roomActu.setIcon(new ImageIcon("images/picRoom17.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Laboratory")){
                        roomActu.setIcon(new ImageIcon("images/picRoom18.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Empty Dungeon Bank")){
                        roomActu.setIcon(new ImageIcon("images/picRoom19.PNG"));
                    }else if (listRoom.get(i).getNameRoom().equals("Lair of Pony")){
                        roomActu.setIcon(new ImageIcon("images/picRoom20.PNG"));
                    }
                    roomActu.setText("");
                    if (listRoom.get(i).isClear()){
                        labelClear.setIcon(new ImageIcon("images/ok.PNG"));
                        labelClear.setText(" Clean!");
                    }else{
                        labelClear.setIcon(new ImageIcon("images/no.PNG"));
                        labelClear.setText(" Not Clean!");
                    }
                    
                    String host =" (Friendly/";
                    String alive = "Alive)"; 
                    if ((listRoom.get(i).getNPCRoom().size())==0 ){
                        labelNPC1.setText("Nobody !");
                        labelNPC2.setText("");
                        labelNPC3.setText("");
                    }else if((listRoom.get(i).getNPCRoom().size())==1){
                        if(listRoom.get(i).getNPCRoom().get(0).isHostile()){host=" (Monster/";}
                        if(listRoom.get(i).getNPCRoom().get(0).getHP()==0){alive="Dead)";}
                        labelNPC1.setText(listRoom.get(i).getNPCRoom().get(0).getName()+host+alive);
                        labelNPC2.setText("");
                        labelNPC3.setText("");
                    }else if((listRoom.get(i).getNPCRoom().size())==2){
                        if(listRoom.get(i).getNPCRoom().get(0).isHostile()){host=" (Monster/";}
                        if(listRoom.get(i).getNPCRoom().get(0).getHP()==0){alive="Dead)";}
                        labelNPC1.setText(listRoom.get(i).getNPCRoom().get(0).getName()+host+alive);
                        if(listRoom.get(i).getNPCRoom().get(1).isHostile()){host=" (Monster/";}
                        if(listRoom.get(i).getNPCRoom().get(1).getHP()==0){alive="Dead)";}
                        labelNPC2.setText(listRoom.get(i).getNPCRoom().get(1).getName()+host+alive);
                        labelNPC3.setText("");
                    }else{  // 3 the max number
                        if(listRoom.get(i).getNPCRoom().get(0).isHostile()){host=" (Monster/";}
                        if(listRoom.get(i).getNPCRoom().get(0).getHP()==0){alive="Dead)";}
                        labelNPC1.setText(listRoom.get(i).getNPCRoom().get(0).getName()+host+alive);
                        if(listRoom.get(i).getNPCRoom().get(1).isHostile()){host=" (Monster/";}
                        if(listRoom.get(i).getNPCRoom().get(1).getHP()==0){alive="Dead)";}
                        labelNPC2.setText(listRoom.get(i).getNPCRoom().get(1).getName()+host+alive);
                        if(listRoom.get(i).getNPCRoom().get(2).isHostile()){host=" (Monster/";}
                        if(listRoom.get(i).getNPCRoom().get(2).getHP()==0){alive="Dead)";}
                        labelNPC3.setText(listRoom.get(i).getNPCRoom().get(2).getName()+host+alive);
                    }
                    
                    
                    
                    labelChest.setText("Chest: "+listRoom.get(i).getChestRoom().size());
                    if ((listRoom.get(i).getChestRoom().size())!=0 ){
                        String str =" (Empty!)";
                        for (Chest c : listRoom.get(i).getChestRoom()){
                            if (c.getInventory().getLength() !=0){
                                str = " (Not Empty!)";
                                break;
                            }
                        }
                    labelChest.setText("Chest: "+listRoom.get(i).getChestRoom().size()+str);   
                        
                        
                    }
                }
            }
        }
    }
}
