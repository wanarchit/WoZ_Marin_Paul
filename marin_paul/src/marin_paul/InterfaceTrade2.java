package marin_paul;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author Paul
 */
public class InterfaceTrade2 extends JFrame implements ActionListener, WindowListener{
                             
    private JFrame myFrame;
    private Player myPlayer;
    private int nbItemPlayer, nbItemChest;
    private JButton exchangeB;
    private Inventory invPlayer,invChest;
    private JPanel bodyPlayer,bodyChest;
    private Chest myChest;
    private JButton arrayButtonPlayer[],arrayButtonChest[];
    private JLabel arrayLabelPlayer[],arrayLabelPlayer2[],arrayLabelPlayer3[],arrayLabelPlayer4[];
    // arrayLabelPlayer : name object of player
    private JPanel arrayPanelPlayer[],arrayPanelChest[];
    private JLabel labelAmountExch;   
    private JLabel arrayLabelChest[],arrayLabelChest2[],arrayLabelChest3[],arrayLabelChest4[];
    private ArrayList<Item> itemSellPlayer,itemSellChest;
    
    public InterfaceTrade2(Player p, Chest chest){
        itemSellPlayer = new ArrayList<Item>();
        itemSellChest = new ArrayList<Item>();
        myPlayer = p;
        myChest = chest;

    // Head
        JLabel headLabel = new JLabel("Searching ...");
        headLabel.setHorizontalAlignment(JLabel.CENTER);
        headLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        
        //Head character
        JLabel headPlayer = new JLabel(myPlayer.getName()+" - Inventory : "+myPlayer.getInventory().getLength()+"/"+myPlayer.getInventory().getSize());
        headPlayer.setHorizontalAlignment(JLabel.CENTER);
        headPlayer.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        JLabel headChest = new JLabel(myChest.getNameChest()+" - Inventory : "+myChest.getInventory().getLength()+"/"+myChest.getInventory().getSize());
        headChest.setHorizontalAlignment(JLabel.CENTER);
        headChest.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        
        JPanel headObjectPl = newPanelHeadObject();
        JPanel headObjectC = newPanelHeadObject();
        
        //Head object's player
        JPanel headObjectP = new JPanel();
        headObjectP.setBorder(BorderFactory.createLineBorder(Color.black));
        headObjectP.setLayout(new GridLayout(2,1));
        headObjectP.add(headPlayer);
        headObjectP.add(headObjectPl);
        
        //Head object's Chest
        JPanel headObjectChest = new JPanel();
        headObjectChest.setBorder(BorderFactory.createLineBorder(Color.black));
        headObjectChest.setLayout(new GridLayout(2,1));
        headObjectChest.add(headChest);
        headObjectChest.add(headObjectC);
        
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
        

        
        JPanel globalBodyChest = new JPanel();
        globalBodyChest.setLayout(new BorderLayout());

        invChest = myChest.getInventory();
        nbItemChest = invChest.getLength();
        bodyChest = new JPanel();
        bodyChest.setLayout(new GridLayout(nbItemChest,5));
        arrayButtonChest = new JButton[nbItemChest];
        arrayLabelChest = new JLabel[nbItemChest];
        arrayLabelChest2 = new JLabel[nbItemChest];
        arrayLabelChest3 = new JLabel[nbItemChest];
        arrayLabelChest4 = new JLabel[nbItemChest];
        arrayPanelChest = new JPanel[nbItemChest];
        
        for (int i =0;i<nbItemChest;i++){
            Item it = invChest.getItem(i);
            arrayButtonChest[i] = new JButton(it.getPicture());
            arrayButtonChest[i].setPreferredSize(new Dimension(35,35));
            arrayPanelChest[i] = new JPanel(new GridBagLayout());
            arrayPanelChest[i].add(arrayButtonChest[i],new GridBagConstraints());
            arrayLabelChest[i] = new JLabel(it.getName());
            setFontLabel(arrayLabelChest[i]);
            if (it.getClass() == Weapon.class){
                arrayLabelChest2[i] = new JLabel("Dam: " +Integer.toString(((Weapon)it).getDamage()));
            }else if (it.getClass() == Armor.class){
                arrayLabelChest2[i] = new JLabel("Def: " +Integer.toString(((Armor)it).getDefense()));
            }else if (it.getClass() == Consummable.class){
                arrayLabelChest2[i] = new JLabel("HP:" +Integer.toString(((Consummable)it).getBonus()));   
            }else{
                arrayLabelChest2[i] = new JLabel(((Key)it).getDoor().getName());
            }
            setFontLabel(arrayLabelChest2[i]);
            
            arrayLabelChest3[i] = new JLabel(Integer.toString(it.getPrice())+" gold");
            setFontLabel(arrayLabelChest3[i]);
            arrayLabelChest4[i] = new JLabel("");
            setFontLabel(arrayLabelChest4[i]);
            arrayLabelChest4[i].setForeground(Color.red);
        }
        
        for (int i =0;i<nbItemChest;i++){
            bodyChest.add(arrayPanelChest[i]);
            bodyChest.add(arrayLabelChest[i]);
            bodyChest.add(arrayLabelChest2[i]);
            bodyChest.add(arrayLabelChest3[i]);
            bodyChest.add(arrayLabelChest4[i]);
        }
        
        JScrollPane scrollChest = new JScrollPane(bodyChest,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollChest.getVerticalScrollBar().setUnitIncrement(10);
        globalBodyChest.add(headObjectChest,BorderLayout.NORTH);
        globalBodyChest.add(scrollChest,BorderLayout.CENTER);
        
        JPanel globalBody = new JPanel();
        globalBody.setLayout(new BorderLayout());
        globalBody.add(globalBodyP,BorderLayout.WEST);
        globalBody.add(globalBodyChest,BorderLayout.EAST);
        

    // Footer
        JLabel space1 = new JLabel("");
        exchangeB = new JButton("EXCHANGE");
        JPanel exchange = new JPanel();
        exchange.add(exchangeB);
        JLabel space2 = new JLabel("");
        JPanel footer = new JPanel();
        footer.setBorder(BorderFactory.createLineBorder(Color.black));
        footer.setLayout(new GridLayout(1,3));
        footer.add(space1);
        footer.add(exchange);
        footer.add(space2);

        
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
        for (int i =0;i<nbItemChest;i++){
            arrayButtonChest[i].addActionListener(this);
        }
    }
    
    /**
     * 
     * @param e 
     */
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == exchangeB){
            int nbObjectsGives = itemSellPlayer.size();
            int nbObjectsTakes = itemSellChest.size();

            
// PLAYER <= Chest ///////////////////////////////////////////////////////////////////////////////////
            if (nbObjectsTakes != 0 && nbObjectsGives == 0){
                    if (nbObjectsTakes+invPlayer.getLength() > invPlayer.getSize()){ // Items can not enter in inventory's Player
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "You do not have enough space in your inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    }else{  // Items can enter in inventory's Player
                        for (Item obj : itemSellChest){
                            invPlayer.addObject(obj);
                            invChest.deleteObject(obj);
                        }
                        myFrame.dispose();
                    }
                
// PLAYER => Chest ///////////////////////////////////////////////////////////////////////////////////
            }else if (nbObjectsTakes == 0 && nbObjectsGives != 0){
                    if (nbObjectsGives+invChest.getLength() > invChest.getSize()){ // Items can not enter in inventory's chest
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "Chest is full !", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    }else{  // Items can enter in inventory's chest
                        boolean objEq=false;    // check if there are equipped objects in sale list
                            for (Item obj : itemSellPlayer){
                                if (obj.equals(myPlayer.getArmorEquipped()) || obj.equals(myPlayer.getWeaponEquipped())){
                                    objEq=true;
                                }
                            }
                            if (objEq){     // there is equipped object(s)
                                Object[]options = {"Yes, still deposit!","No, Don't deposit anything!"};
                                int option = JOptionPane.showOptionDialog(this,
                                "Are you sure you want to deposit equiped items ?",
                                "Confirmation :",
                                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,   
                                options,options[1]);
                                if(option == JOptionPane.YES_OPTION){
                                    myPlayer.setArmorEquipped(null);
                                    myPlayer.setWeaponEquipped(null);
                                    for (Item obj : itemSellPlayer){
                                        invChest.addObject(obj);
                                        invPlayer.deleteObject(obj);
                                    }
                                    myFrame.dispose();
                                }
                            }else{  // There is not equipped object
                                
                                for (Item obj : itemSellPlayer){
                                    invChest.addObject(obj);
                                    invPlayer.deleteObject(obj);
                                }
                                myFrame.dispose();
                            }
                    }
                
// PLAYER <=> Chest ///////////////////////////////////////////////////////////////////////////////////
            }else if (nbObjectsTakes != 0 && nbObjectsGives != 0){  // Player <=> Chest
                    // SI LE NOMBRE D'OBJETS ECHANGE RENTRE DANS L'INVENTAIRE DE CHACUN
                    if(((invPlayer.getLength()+nbObjectsTakes-nbObjectsGives)<=invPlayer.getSize()) 
                            && ((invChest.getLength()+nbObjectsGives-nbObjectsTakes)<=invChest.getSize())){
                        boolean objEq=false;    // check if there are equipped objects in sale list
                        for (Item obj : itemSellPlayer){
                            if (obj.equals(myPlayer.getArmorEquipped()) || obj.equals(myPlayer.getWeaponEquipped())){
                                objEq=true;
                            }
                        }
                        if (objEq){     // there is equipped object(s)
                            Object[]options = {"Yes, still depositing!","No, Don't deposit anything!"};
                            int option = JOptionPane.showOptionDialog(this,
                            "Are you sure you want to deposit equiped items ?",
                            "Confirmation :",
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,   
                            options,options[1]);
                            if(option == JOptionPane.YES_OPTION){
                                myPlayer.setArmorEquipped(null);
                                myPlayer.setWeaponEquipped(null);
                                for (Item obj : itemSellPlayer){
                                    invPlayer.deleteObject(obj);
                                }
                                for (Item obj : itemSellChest){
                                    invPlayer.addObject(obj);
                                }
                                for (Item obj : itemSellChest){
                                    invChest.deleteObject(obj);
                                }
                                for (Item obj : itemSellPlayer){
                                    invChest.addObject(obj);
                                }
                                myFrame.dispose();
                            }
                        }else{  // There is not equipped object
                            for (Item obj : itemSellPlayer){
                                    invPlayer.deleteObject(obj);
                                }
                                for (Item obj : itemSellChest){
                                    invPlayer.addObject(obj);
                                }
                                for (Item obj : itemSellChest){
                                    invChest.deleteObject(obj);
                                }
                                for (Item obj : itemSellPlayer){
                                    invChest.addObject(obj);
                                }
                            myFrame.dispose();
                        }            
                    // SI LE NOMBRE D'OBJECTS ECHANGER NE RENTRE PAS DANS L'INVENTAIRE DU PLAYER
                    }else if((invPlayer.getLength()+nbObjectsTakes-nbObjectsGives)>invPlayer.getSize()){
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "You do not have enough space in your inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    // SI LE NOMBRE D'OBJECTS ECHANGER NE RENTRE PAS DANS L'INVENTAIRE DU Chest
                    }else if((invChest.getLength()+nbObjectsGives-nbObjectsTakes)>invChest.getSize()){
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "Chest is full!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    //NE RENTRE DANS AUCUNS DES INVENTAIRES : les deux sont trop pleins
                    }else{
                        JFrame messFrame = new JFrame();
                        JOptionPane d = new JOptionPane();
                        d.showMessageDialog(messFrame, "Each of you do not have enough space in your respective inventory!", 
                        "Warning !", JOptionPane.INFORMATION_MESSAGE);
                    }
                 
// PLAYER =/= Chest ///////////////////////////////////////////////////////////////////////////////////
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
                }else if (e.getSource()==arrayButtonPlayer[i] && arrayLabelPlayer4[i].getText().equals("YES")){
                    itemSellPlayer.remove(invPlayer.getItem(i));
                    arrayLabelPlayer4[i].setText("");
                }
            }
            for (int i =0;i<nbItemChest;i++){
                if (e.getSource()==arrayButtonChest[i] && arrayLabelChest4[i].getText().equals("")){
                    itemSellChest.add(invChest.getItem(i));
                    arrayLabelChest4[i].setText("YES");
                }else if (e.getSource()==arrayButtonChest[i] && arrayLabelChest4[i].getText().equals("YES")){
                    itemSellChest.remove(invChest.getItem(i));
                    arrayLabelChest4[i].setText("");
                }
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
