package marin_paul;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author Paul
 */
public class InterfaceTrade extends JFrame implements ActionListener, WindowListener{
                             
    private JFrame myFrame;
    private Player myPlayer;
    private NPC myNPC;
    private int nbItemPlayer, nbItemNPC;
    private JButton exchangeB;
    private Inventory invPlayer,invNPC;
    private JPanel bodyPlayer,bodyNPC;
    
    private JButton arrayButtonPlayer[],arrayButtonNPC[];
    private JLabel arrayLabelPlayer[],arrayLabelPlayer2[],arrayLabelPlayer3[],arrayLabelPlayer4[];
    // arrayLabelPlayer : name object of player
    private JPanel arrayPanelPlayer[],arrayPanelNPC[];
    private JLabel labelAmountExch;   
    private JLabel arrayLabelNPC[],arrayLabelNPC2[],arrayLabelNPC3[],arrayLabelNPC4[];
    private ArrayList<Item> itemSellPlayer,itemSellNPC;

    private int amountExch,amountExchPlayer, amountExchNPC = 0;
    
    public InterfaceTrade(Player p, NPC npc){
        itemSellPlayer = new ArrayList<Item>();
        itemSellNPC = new ArrayList<Item>();
        myPlayer = p;
        myNPC = npc;

    // Head
        JLabel headLabel = new JLabel("Trade");
        headLabel.setHorizontalAlignment(JLabel.CENTER);
        headLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        
        //Head character
        JLabel headPlayer = new JLabel(myPlayer.getName()+" - Inventory : "+myPlayer.getInventory().getLength()+"/"+myPlayer.getInventory().getSize());
        headPlayer.setHorizontalAlignment(JLabel.CENTER);
        headPlayer.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        JLabel headNPC = new JLabel(myNPC.getName()+" - Inventory : "+myNPC.getInventory().getLength()+"/"+myNPC.getInventory().getSize());
        headNPC.setHorizontalAlignment(JLabel.CENTER);
        headNPC.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        
        JPanel headObjectPl = newPanelHeadObject();
        JPanel headObjectN = newPanelHeadObject();
        
        //Head object's player
        JPanel headObjectP = new JPanel();
        headObjectP.setBorder(BorderFactory.createLineBorder(Color.black));
        headObjectP.setLayout(new GridLayout(2,1));
        headObjectP.add(headPlayer);
        headObjectP.add(headObjectPl);
        
        //Head object's NPC
        JPanel headObjectNPC = new JPanel();
        headObjectNPC.setBorder(BorderFactory.createLineBorder(Color.black));
        headObjectNPC.setLayout(new GridLayout(2,1));
        headObjectNPC.add(headNPC);
        headObjectNPC.add(headObjectN);
        
    // Body
        // Player
        JPanel globalBodyP = new JPanel();
        globalBodyP.setLayout(new BorderLayout());

        invPlayer = myPlayer.getInventory();
        nbItemPlayer = invPlayer.getLength();
        bodyPlayer = new JPanel();
        bodyPlayer.setLayout(new GridLayout(nbItemPlayer,5));
        arrayButtonPlayer = new JButton[nbItemPlayer];
        arrayLabelPlayer = new JLabel[nbItemPlayer];
        arrayLabelPlayer2 = new JLabel[nbItemPlayer];
        arrayLabelPlayer3 = new JLabel[nbItemPlayer];
        arrayLabelPlayer4 = new JLabel[nbItemPlayer];
        arrayPanelPlayer = new JPanel[nbItemPlayer];
        
        for (int i =0;i<nbItemPlayer;i++){
            Item it = invPlayer.getItem(i);
            arrayButtonPlayer[i] = new JButton(it.getPicture());
            arrayButtonPlayer[i].setPreferredSize(new Dimension(35,35));
            arrayPanelPlayer[i] = new JPanel(new GridBagLayout());
            arrayPanelPlayer[i].add(arrayButtonPlayer[i],new GridBagConstraints());
            if (invPlayer.getItem(i).getClass() == Weapon.class && p.getWeaponEquipped()!= null && p.getWeaponEquipped().equals(invPlayer.getItem(i))){
                arrayLabelPlayer[i] = new JLabel(invPlayer.getItem(i).getName()+ " (E)");
                arrayLabelPlayer[i].setForeground(Color.red);
            }else if (invPlayer.getItem(i).getClass() == Armor.class && p.getArmorEquipped()!= null && p.getArmorEquipped().equals(invPlayer.getItem(i))){
                arrayLabelPlayer[i] = new JLabel(invPlayer.getItem(i).getName() + " (E)");
                arrayLabelPlayer[i].setForeground(Color.red);
            }else{
                arrayLabelPlayer[i] = new JLabel(invPlayer.getItem(i).getName());
            }
            setFontLabel(arrayLabelPlayer[i]);
            if (it.getClass() == Weapon.class){
                arrayLabelPlayer2[i] = new JLabel("Dam: " +Integer.toString(((Weapon)it).getDamage()));
            }else if (it.getClass() == Armor.class){
                arrayLabelPlayer2[i] = new JLabel("Def: " +Integer.toString(((Armor)it).getDefense()));
            }else if (it.getClass() == Consummable.class){
                arrayLabelPlayer2[i] = new JLabel("HP:" +Integer.toString(((Consummable)it).getBonus()));   
            }else{
                arrayLabelPlayer2[i] = new JLabel(((Key)it).getDoor().getName());
            }
            setFontLabel(arrayLabelPlayer2[i]);
            
            arrayLabelPlayer3[i] = new JLabel(Integer.toString(it.getPrice())+" gold");
            setFontLabel(arrayLabelPlayer3[i]);
            
            arrayLabelPlayer4[i] = new JLabel("");
            setFontLabel(arrayLabelPlayer4[i]);
            arrayLabelPlayer4[i].setForeground(Color.red);
        }
        
        for (int i =0;i<nbItemPlayer;i++){
            bodyPlayer.add(arrayPanelPlayer[i]);
            bodyPlayer.add(arrayLabelPlayer[i]);
            bodyPlayer.add(arrayLabelPlayer2[i]);
            bodyPlayer.add(arrayLabelPlayer3[i]);
            bodyPlayer.add(arrayLabelPlayer4[i]);
        }
        
        JScrollPane scrollPlayer = new JScrollPane(bodyPlayer,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPlayer.getVerticalScrollBar().setUnitIncrement(10);
        globalBodyP.add(headObjectP,BorderLayout.NORTH);
        globalBodyP.add(scrollPlayer,BorderLayout.CENTER);
        
        // Amount exchange
        
        JPanel panelAmoutExch = new JPanel(new BorderLayout());
        labelAmountExch = new JLabel(amountExch+" gold");
        labelAmountExch.setHorizontalAlignment(JLabel.CENTER);
        labelAmountExch.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,35));
        panelAmoutExch.add(labelAmountExch,BorderLayout.CENTER);
        
        
        
        JPanel globalBodyNPC = new JPanel();
        globalBodyNPC.setLayout(new BorderLayout());

        invNPC = myNPC.getInventory();
        nbItemNPC = invNPC.getLength();
        bodyNPC = new JPanel();
        bodyNPC.setLayout(new GridLayout(nbItemNPC,5));
        arrayButtonNPC = new JButton[nbItemNPC];
        arrayLabelNPC = new JLabel[nbItemNPC];
        arrayLabelNPC2 = new JLabel[nbItemNPC];
        arrayLabelNPC3 = new JLabel[nbItemNPC];
        arrayLabelNPC4 = new JLabel[nbItemNPC];
        arrayPanelNPC = new JPanel[nbItemNPC];
        
        for (int i =0;i<nbItemNPC;i++){
            Item it = invNPC.getItem(i);
            arrayButtonNPC[i] = new JButton(it.getPicture());
            arrayButtonNPC[i].setPreferredSize(new Dimension(35,35));
            arrayPanelNPC[i] = new JPanel(new GridBagLayout());
            arrayPanelNPC[i].add(arrayButtonNPC[i],new GridBagConstraints());
            arrayLabelNPC[i] = new JLabel(it.getName());
            setFontLabel(arrayLabelNPC[i]);
            if (it.getClass() == Weapon.class){
                arrayLabelNPC2[i] = new JLabel("Dam: " +Integer.toString(((Weapon)it).getDamage()));
            }else if (it.getClass() == Armor.class){
                arrayLabelNPC2[i] = new JLabel("Def: " +Integer.toString(((Armor)it).getDefense()));
            }else if (it.getClass() == Consummable.class){
                arrayLabelNPC2[i] = new JLabel("HP:" +Integer.toString(((Consummable)it).getBonus()));   
            }else{
                arrayLabelNPC2[i] = new JLabel(((Key)it).getDoor().getName());
            }
            setFontLabel(arrayLabelNPC2[i]);
            
            arrayLabelNPC3[i] = new JLabel(Integer.toString(it.getPrice())+" gold");
            setFontLabel(arrayLabelNPC3[i]);
            arrayLabelNPC4[i] = new JLabel("");
            setFontLabel(arrayLabelNPC4[i]);
            arrayLabelNPC4[i].setForeground(Color.red);
        }
        
        for (int i =0;i<nbItemNPC;i++){
            bodyNPC.add(arrayPanelNPC[i]);
            bodyNPC.add(arrayLabelNPC[i]);
            bodyNPC.add(arrayLabelNPC2[i]);
            bodyNPC.add(arrayLabelNPC3[i]);
            bodyNPC.add(arrayLabelNPC4[i]);
        }
        
        JScrollPane scrollNPC = new JScrollPane(bodyNPC,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollNPC.getVerticalScrollBar().setUnitIncrement(10);
        globalBodyNPC.add(headObjectNPC,BorderLayout.NORTH);
        globalBodyNPC.add(scrollNPC,BorderLayout.CENTER);
        
        JPanel globalBody = new JPanel();
        globalBody.setLayout(new BorderLayout());
        globalBody.add(globalBodyP,BorderLayout.WEST);
        globalBody.add(panelAmoutExch,BorderLayout.CENTER);
        globalBody.add(globalBodyNPC,BorderLayout.EAST);
        

    // Footer
        JLabel goldPlayer = new JLabel(myPlayer.getMoney()+" gold");
        setFontLabel(goldPlayer);
        exchangeB = new JButton("EXCHANGE");
        JPanel exchange = new JPanel();
        exchange.add(exchangeB);
        JLabel goldNPC = new JLabel(myNPC.getMoney()+" gold");
        setFontLabel(goldNPC);
        JPanel footer = new JPanel();
        footer.setBorder(BorderFactory.createLineBorder(Color.black));
        footer.setLayout(new GridLayout(1,3));
        footer.add(goldPlayer);
        footer.add(exchange);
        footer.add(goldNPC);

        
    // Global Panel
        JPanel globalPanel = new JPanel();
        globalPanel.setLayout(new BorderLayout());
        globalPanel.add(headLabel,BorderLayout.NORTH);
        globalPanel.add(globalBody,BorderLayout.CENTER);
        globalPanel.add(footer,BorderLayout.SOUTH);
        
        
    // Frame
        myFrame = new JFrame("Exchange interface");
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame.add(globalPanel);
        myFrame.setResizable(false);
        myFrame.setPreferredSize(new Dimension(1100,500));
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        

        exchangeB.addActionListener(this);
        for (int i =0;i<nbItemPlayer;i++){
            arrayButtonPlayer[i].addActionListener(this);
        }
        for (int i =0;i<nbItemNPC;i++){
            arrayButtonNPC[i].addActionListener(this);
        }
    }
    
    /**
     * 
     * @param e 
     */
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == exchangeB){
            int nbObjectsGives = itemSellPlayer.size();
            int nbObjectsTakes = itemSellNPC.size();
            int amountBuy = 0;
            int amountSale = 0;

            
// PLAYER <= NPC ///////////////////////////////////////////////////////////////////////////////////
            if (nbObjectsTakes != 0 && nbObjectsGives == 0){
                if (myNPC.getHP() != 0){    // NPC is alive
                    if (nbObjectsTakes+invPlayer.getLength() > invPlayer.getSize()){ // Items can not enter in inventory's Player
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "You do not have enough space in your inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    }else{  // Items can enter in inventory's Player
                        for (Item obj : itemSellNPC){
                            amountBuy += obj.getPrice();
                            }
                        if (amountBuy > myPlayer.getMoney()){       // Player does not have enough money to buy items
                            JFrame messFrame = new JFrame();
                            JOptionPane d = new JOptionPane();
                            d.showMessageDialog(messFrame, "You do not have enough money!", 
                            "Warning !", JOptionPane.INFORMATION_MESSAGE);
                        }else{  // buy ok
                            for (Item obj : itemSellNPC){
                                invPlayer.addObject(obj);
                                invNPC.deleteObject(obj);
                                if (myNPC.getArmorEquipped()!= null && myNPC.getArmorEquipped().equals(obj)){
                                    myNPC.setArmorEquipped(null);
                                }
                                if (myNPC.getWeaponEquipped()!= null && myNPC.getWeaponEquipped().equals(obj)){
                                    myNPC.setWeaponEquipped(null);
                                }
                            }
                            myPlayer.setMoney(myPlayer.getMoney()-amountBuy);
                            myNPC.setMoney(myNPC.getMoney()+amountBuy);
                            myFrame.dispose();
                        }
                    }
                }else{                      // NPC is dead
                    if (nbObjectsTakes+invPlayer.getLength() > invPlayer.getSize()){ // Items can not enter in inventory's Player
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "You do not have enough space in your inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    }else{  // Items can enter in inventory's Player
                        for (Item obj : itemSellNPC){
                            invPlayer.addObject(obj);
                            invNPC.deleteObject(obj);
                            if (myNPC.getArmorEquipped()!= null && myNPC.getArmorEquipped().equals(obj)){
                                    myNPC.setArmorEquipped(null);
                                }
                                if (myNPC.getWeaponEquipped()!= null && myNPC.getWeaponEquipped().equals(obj)){
                                    myNPC.setWeaponEquipped(null);
                                }
                        }
                        myFrame.dispose();
                    }
                }
// PLAYER => NPC ///////////////////////////////////////////////////////////////////////////////////
            }else if (nbObjectsTakes == 0 && nbObjectsGives != 0){
                if (myNPC.getHP() != 0){    // NPC is alive
                    if (nbObjectsGives+invNPC.getLength() > invNPC.getSize()){ // Items can not enter in inventory's seller
                        //JFrame messFrame = new JFrame();
                        JWindow messFrame = new JWindow();
                        //messFrame.setUndecorated(false);
                        System.out.println("ok");
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "Seller does not have enough space in its inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    }else{  // Items can enter in inventory's seller
                        for (Item obj : itemSellPlayer){
                            amountSale += obj.getPrice();
                            }
                        if (amountSale > myNPC.getMoney()){       // NPC does not have enough money to buy items
                            JFrame messFrame = new JFrame();
                            JOptionPane d = new JOptionPane();
                            d.showMessageDialog(messFrame, "Seller does not have enough money!", 
                            "Warning !", JOptionPane.INFORMATION_MESSAGE);
                        }else{  // sale ok
                            boolean objEq=false;    // check if there are equipped objects in sale list
                            for (Item obj : itemSellPlayer){
                                if (obj.equals(myPlayer.getArmorEquipped()) || obj.equals(myPlayer.getWeaponEquipped())){
                                    objEq=true;
                                }
                            }
                            if (objEq){     // there is equipped object(s)
                                Object[]options = {"Yes, still selling!","No, Don't sell anything!"};
                                int option = JOptionPane.showOptionDialog(this,
                                "Are you sure you want to sell equiped items ?",
                                "Confirmation :",
                                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,   
                                options,options[1]);
                                if(option == JOptionPane.YES_OPTION){
                                    myPlayer.setArmorEquipped(null);
                                    myPlayer.setWeaponEquipped(null);
                                    for (Item obj : itemSellPlayer){
                                        invNPC.addObject(obj);
                                        invPlayer.deleteObject(obj);
                                    }
                                    myFrame.dispose();
                                }
                            }else{  // There is not equipped object
                                
                                for (Item obj : itemSellPlayer){
                                    invNPC.addObject(obj);
                                    invPlayer.deleteObject(obj);
                                }
                                myNPC.setMoney(myNPC.getMoney()-amountSale);
                                myPlayer.setMoney(myPlayer.getMoney()+amountSale);
                                myFrame.dispose();
                            }
                        }
                    }
                }else{                      // NPC is dead
                    if (nbObjectsGives+invNPC.getLength() > invNPC.getSize()){ // Items can not enter in inventory's enemy
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "Enemy does not have enough space in its inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    }else{  // Items can enter in inventory's enemy
                        boolean objEq=false;    // check if there are equipped objects in sale list
                            for (Item obj : itemSellPlayer){
                                if (obj.equals(myPlayer.getArmorEquipped()) || obj.equals(myPlayer.getWeaponEquipped())){
                                    objEq=true;
                                }
                            }
                            if (objEq){     // there is equipped object(s)
                                Object[]options = {"Yes, still selling!","No, Don't sell anything!"};
                                int option = JOptionPane.showOptionDialog(this,
                                "Are you sure you want to sell equiped items ?",
                                "Confirmation :",
                                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,   
                                options,options[1]);
                                if(option == JOptionPane.YES_OPTION){
                                    myPlayer.setArmorEquipped(null);
                                    myPlayer.setWeaponEquipped(null);
                                    for (Item obj : itemSellPlayer){
                                        invNPC.addObject(obj);
                                        invPlayer.deleteObject(obj);
                                    }
                                    myNPC.setMoney(myNPC.getMoney()-amountSale);
                                    myPlayer.setMoney(myPlayer.getMoney()+amountSale);
                                    myFrame.dispose();
                                }
                            }else{  // There is not equipped object
                                
                                for (Item obj : itemSellPlayer){
                                    invNPC.addObject(obj);
                                    invPlayer.deleteObject(obj);
                                }
                                myNPC.setMoney(myNPC.getMoney()-amountSale);
                                myPlayer.setMoney(myPlayer.getMoney()+amountSale);
                                myFrame.dispose();
                            }
                    }
                }
// PLAYER <=> NPC ///////////////////////////////////////////////////////////////////////////////////
            }else if (nbObjectsTakes != 0 && nbObjectsGives != 0){  // Player <=> NPC
                if (myNPC.getHP() != 0){    // NPC is alive
                    // SI LE NOMBRE D'OBJETS ECHANGE RENTRE DANS L'INVENTAIRE DE CHACUN
                    if((invPlayer.getLength()+nbObjectsTakes-nbObjectsGives)<=invPlayer.getSize() 
                            && (invNPC.getLength()+nbObjectsGives-nbObjectsTakes)<=invNPC.getSize()){
                        for (Item obj : itemSellPlayer){
                            amountSale += obj.getPrice();
                            }
                        for (Item obj : itemSellNPC){
                            amountBuy += obj.getPrice();
                            }
                        if(amountBuy == amountSale){
                            boolean objEq=false;    // check if there are equipped objects in sale list
                        for (Item obj : itemSellPlayer){
                            if (obj.equals(myPlayer.getArmorEquipped()) || obj.equals(myPlayer.getWeaponEquipped())){
                                objEq=true;
                            }
                        }
                        if (objEq){     // there is equipped object(s)
                            Object[]options = {"Yes, still selling!","No, Don't sell anything!"};
                            int option = JOptionPane.showOptionDialog(this,
                            "Are you sure you want to sell equiped items ?",
                            "Confirmation :",
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,   
                            options,options[1]);
                            if(option == JOptionPane.YES_OPTION){
                                myPlayer.setArmorEquipped(null);
                                myPlayer.setWeaponEquipped(null);
                                for (Item obj : itemSellPlayer){
                                    invPlayer.deleteObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invPlayer.addObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invNPC.deleteObject(obj);
                                    if (myNPC.getArmorEquipped()!= null && myNPC.getArmorEquipped().equals(obj)){
                                        myNPC.setArmorEquipped(null);
                                    }
                                    if (myNPC.getWeaponEquipped()!= null && myNPC.getWeaponEquipped().equals(obj)){
                                        myNPC.setWeaponEquipped(null);
                                    }
                                }
                                for (Item obj : itemSellPlayer){
                                    invNPC.addObject(obj);
                                }
                                myFrame.dispose();
                            }
                        }else{  // There is not equipped object
                            for (Item obj : itemSellPlayer){
                                    invPlayer.deleteObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invPlayer.addObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invNPC.deleteObject(obj);
                                    if (myNPC.getArmorEquipped()!= null && myNPC.getArmorEquipped().equals(obj)){
                                        myNPC.setArmorEquipped(null);
                                    }
                                    if (myNPC.getWeaponEquipped()!= null && myNPC.getWeaponEquipped().equals(obj)){
                                        myNPC.setWeaponEquipped(null);
                                    }
                                }
                                for (Item obj : itemSellPlayer){
                                    invNPC.addObject(obj);
                                }
                            myFrame.dispose();
                        }
                        }else if(amountBuy > amountSale){
                            if(myPlayer.getMoney() < amountBuy-amountSale){    // Player does not have enough money
                                JFrame messFrame = new JFrame();
                                JOptionPane d = new JOptionPane();
                                d.showMessageDialog(messFrame, "Player does not have enough money!", 
                                "Warning !", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                boolean objEq=false;    // check if there are equipped objects in sale list
                        for (Item obj : itemSellPlayer){
                            if (obj.equals(myPlayer.getArmorEquipped()) || obj.equals(myPlayer.getWeaponEquipped())){
                                objEq=true;
                            }
                        }
                        if (objEq){     // there is equipped object(s)
                            Object[]options = {"Yes, still selling!","No, Don't sell anything!"};
                            int option = JOptionPane.showOptionDialog(this,
                            "Are you sure you want to sell equiped items ?",
                            "Confirmation :",
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,   
                            options,options[1]);
                            if(option == JOptionPane.YES_OPTION){
                                myPlayer.setArmorEquipped(null);
                                myPlayer.setWeaponEquipped(null);
                                for (Item obj : itemSellPlayer){
                                    invPlayer.deleteObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invPlayer.addObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invNPC.deleteObject(obj);
                                    if (myNPC.getArmorEquipped()!= null && myNPC.getArmorEquipped().equals(obj)){
                                        myNPC.setArmorEquipped(null);
                                    }
                                    if (myNPC.getWeaponEquipped()!= null && myNPC.getWeaponEquipped().equals(obj)){
                                        myNPC.setWeaponEquipped(null);
                                    }
                                }
                                for (Item obj : itemSellPlayer){
                                    invNPC.addObject(obj);
                                }
                                myNPC.setMoney(myNPC.getMoney()+(amountBuy-amountSale));
                                myPlayer.setMoney(myPlayer.getMoney()-(amountBuy-amountSale));
                                myFrame.dispose();
                            }
                        }else{  // There is not equipped object
                            for (Item obj : itemSellPlayer){
                                    invPlayer.deleteObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invPlayer.addObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invNPC.deleteObject(obj);
                                    if (myNPC.getArmorEquipped()!= null && myNPC.getArmorEquipped().equals(obj)){
                                        myNPC.setArmorEquipped(null);
                                    }
                                    if (myNPC.getWeaponEquipped()!= null && myNPC.getWeaponEquipped().equals(obj)){
                                        myNPC.setWeaponEquipped(null);
                                    }
                                }
                                for (Item obj : itemSellPlayer){
                                    invNPC.addObject(obj);
                                }
                            myNPC.setMoney(myNPC.getMoney()+(amountBuy-amountSale));
                            myPlayer.setMoney(myPlayer.getMoney()-(amountBuy-amountSale));
                            myFrame.dispose();
                        }
                            }
                        }else{          //amountBuy < amountSale
                            if(myNPC.getMoney() < amountSale-amountBuy){    // NPC does not have enough money
                                JFrame messFrame = new JFrame();
                                JOptionPane d = new JOptionPane();
                                d.showMessageDialog(messFrame, "Seller does not have enough money!", 
                                "Warning !", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                boolean objEq=false;    // check if there are equipped objects in sale list
                        for (Item obj : itemSellPlayer){
                            if (obj.equals(myPlayer.getArmorEquipped()) || obj.equals(myPlayer.getWeaponEquipped())){
                                objEq=true;
                            }
                        }
                        if (objEq){     // there is equipped object(s)
                            Object[]options = {"Yes, still selling!","No, Don't sell anything!"};
                            int option = JOptionPane.showOptionDialog(this,
                            "Are you sure you want to sell equiped items ?",
                            "Confirmation :",
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,   
                            options,options[1]);
                            if(option == JOptionPane.YES_OPTION){
                                myPlayer.setArmorEquipped(null);
                                myPlayer.setWeaponEquipped(null);
                                for (Item obj : itemSellPlayer){
                                    invPlayer.deleteObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invPlayer.addObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invNPC.deleteObject(obj);
                                    if (myNPC.getArmorEquipped()!= null && myNPC.getArmorEquipped().equals(obj)){
                                        myNPC.setArmorEquipped(null);
                                    }
                                    if (myNPC.getWeaponEquipped()!= null && myNPC.getWeaponEquipped().equals(obj)){
                                        myNPC.setWeaponEquipped(null);
                                    }
                                }
                                for (Item obj : itemSellPlayer){
                                    invNPC.addObject(obj);
                                }
                                myNPC.setMoney(myNPC.getMoney()-(amountSale-amountBuy));
                                myPlayer.setMoney(myPlayer.getMoney()+(amountSale-amountBuy));
                                myFrame.dispose();
                            }
                        }else{  // There is not equipped object
                            for (Item obj : itemSellPlayer){
                                    invPlayer.deleteObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invPlayer.addObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invNPC.deleteObject(obj);
                                    if (myNPC.getArmorEquipped()!= null && myNPC.getArmorEquipped().equals(obj)){
                                        myNPC.setArmorEquipped(null);
                                    }
                                    if (myNPC.getWeaponEquipped()!= null && myNPC.getWeaponEquipped().equals(obj)){
                                        myNPC.setWeaponEquipped(null);
                                    }
                                }
                                for (Item obj : itemSellPlayer){
                                    invNPC.addObject(obj);
                                }
                                myNPC.setMoney(myNPC.getMoney()-(amountSale-amountBuy));
                                myPlayer.setMoney(myPlayer.getMoney()+(amountSale-amountBuy));
                            myFrame.dispose();
                        }
                            }
                        }
                                   
                    // SI LE NOMBRE D'OBJECTS ECHANGER NE RENTRE PAS DANS L'INVENTAIRE DU PLAYER
                    }else if((invPlayer.getLength()+nbObjectsTakes-nbObjectsGives)>invPlayer.getSize()){
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "You do not have enough space in your inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    // SI LE NOMBRE D'OBJECTS ECHANGER NE RENTRE PAS DANS L'INVENTAIRE DU NPC
                    }else if((invNPC.getLength()+nbObjectsGives-nbObjectsTakes)<invNPC.getSize()){
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "Seller does not have enough space in its inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    //NE RENTRE DANS AUCUNS DES INVENTAIRES : les deux sont trop pleins
                    }else{
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "Each of you do not have enough space in your respective inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    }
                
                }else{                      // NPC is dead
                    // SI LE NOMBRE D'OBJETS ECHANGE RENTRE DANS L'INVENTAIRE DE CHACUN
                    if(((invPlayer.getLength()+nbObjectsTakes-nbObjectsGives)<=invPlayer.getSize()) 
                            && ((invNPC.getLength()+nbObjectsGives-nbObjectsTakes)<=invNPC.getSize())){
                        boolean objEq=false;    // check if there are equipped objects in sale list
                        for (Item obj : itemSellPlayer){
                            if (obj.equals(myPlayer.getArmorEquipped()) || obj.equals(myPlayer.getWeaponEquipped())){
                                objEq=true;
                            }
                        }
                        if (objEq){     // there is equipped object(s)
                            Object[]options = {"Yes, still selling!","No, Don't sell anything!"};
                            int option = JOptionPane.showOptionDialog(this,
                            "Are you sure you want to sell equiped items ?",
                            "Confirmation :",
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,   
                            options,options[1]);
                            if(option == JOptionPane.YES_OPTION){
                                myPlayer.setArmorEquipped(null);
                                myPlayer.setWeaponEquipped(null);
                                for (Item obj : itemSellPlayer){
                                    invPlayer.deleteObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invPlayer.addObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invNPC.deleteObject(obj);
                                }
                                for (Item obj : itemSellPlayer){
                                    invNPC.addObject(obj);
                                }
                                myFrame.dispose();
                            }
                        }else{  // There is not equipped object
                            for (Item obj : itemSellPlayer){
                                    invPlayer.deleteObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invPlayer.addObject(obj);
                                }
                                for (Item obj : itemSellNPC){
                                    invNPC.deleteObject(obj);
                                }
                                for (Item obj : itemSellPlayer){
                                    invNPC.addObject(obj);
                                }
                            myFrame.dispose();
                        }            
                    // SI LE NOMBRE D'OBJECTS ECHANGER NE RENTRE PAS DANS L'INVENTAIRE DU PLAYER
                    }else if((invPlayer.getLength()+nbObjectsTakes-nbObjectsGives)>invPlayer.getSize()){
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "You do not have enough space in your inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    // SI LE NOMBRE D'OBJECTS ECHANGER NE RENTRE PAS DANS L'INVENTAIRE DU NPC
                    }else if((invNPC.getLength()+nbObjectsGives-nbObjectsTakes)>invNPC.getSize()){
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "Enemy does not have enough space in its inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    //NE RENTRE DANS AUCUNS DES INVENTAIRES : les deux sont trop pleins
                    }else{
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "Each of you do not have enough space in your respective inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    }
                } 
// PLAYER =/= NPC ///////////////////////////////////////////////////////////////////////////////////
            }else{ // nbObjectsTakes == 0 && nbObjectsGives == 0
                myFrame.dispose();
            }
// END OF FUNCTION FOR "EXCHANGE BUTTON"
        }else{  // Item choices
            //amountExch
            for (int i =0;i<nbItemPlayer;i++){
                if (e.getSource()==arrayButtonPlayer[i] && arrayLabelPlayer4[i].getText().equals("")){
                    itemSellPlayer.add(invPlayer.getItem(i));
                    arrayLabelPlayer4[i].setText("YES");
                    amountExchPlayer+=invPlayer.getItem(i).getPrice();
                }else if (e.getSource()==arrayButtonPlayer[i] && arrayLabelPlayer4[i].getText().equals("YES")){
                    itemSellPlayer.remove(invPlayer.getItem(i));
                    arrayLabelPlayer4[i].setText("");
                    amountExchPlayer-=invPlayer.getItem(i).getPrice();
                }
            }
            for (int i =0;i<nbItemNPC;i++){
                if (e.getSource()==arrayButtonNPC[i] && arrayLabelNPC4[i].getText().equals("")){
                    itemSellNPC.add(invNPC.getItem(i));
                    arrayLabelNPC4[i].setText("YES");
                    amountExchNPC+=invNPC.getItem(i).getPrice();
                }else if (e.getSource()==arrayButtonNPC[i] && arrayLabelNPC4[i].getText().equals("YES")){
                    itemSellNPC.remove(invNPC.getItem(i));
                    arrayLabelNPC4[i].setText("");
                    amountExchNPC-=invNPC.getItem(i).getPrice();
                }
            }
            if (amountExchPlayer>amountExchNPC && !myNPC.isHostile()){
                amountExch=amountExchPlayer-amountExchNPC;
                labelAmountExch.setText("<= "+amountExch+" gold");
                if(myNPC.getMoney()<amountExch){
                    labelAmountExch.setForeground(Color.red);
                }else{
                    labelAmountExch.setForeground(Color.black);
                }
            }else if(amountExchPlayer<amountExchNPC && !myNPC.isHostile()){
                amountExch=amountExchNPC-amountExchPlayer;
                labelAmountExch.setText(amountExch+" gold =>");
                if(myPlayer.getMoney()<amountExch){
                    labelAmountExch.setForeground(Color.red);
                }else{
                    labelAmountExch.setForeground(Color.black);
                }
            }else{
                amountExch = 0;
                labelAmountExch.setText(amountExch+" gold");
                labelAmountExch.setForeground(Color.black);
            }
        }
    }
    
        
    
    

    public void windowActivated(WindowEvent e){}
    public void windowClosed(WindowEvent e){}
    public void windowClosing(WindowEvent e){}
    public void windowDeactivated(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowOpened(WindowEvent e){} 
    
    private JPanel newPanelHeadObject(){
        JLabel icon = new JLabel("Item");
        setFontLabel(icon);
        JLabel nameObject = new JLabel("Name");
        setFontLabel(nameObject);
        JLabel effectObject = new JLabel("Effect");
        setFontLabel(effectObject);
        JLabel priceObject = new JLabel("Price");
        setFontLabel(priceObject);
        JLabel validate = new JLabel("Sell?");
        setFontLabel(validate);
        JPanel headObj = new JPanel();
        headObj.setLayout(new GridLayout(1,5));
        headObj.add(icon);
        headObj.add(nameObject);
        headObj.add(effectObject);
        headObj.add(priceObject);
        headObj.add(validate);
        return headObj;
    }
    
    private void setFontLabel(JLabel lab){
        lab.setHorizontalAlignment(JLabel.CENTER);
        lab.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
    }
     
}
