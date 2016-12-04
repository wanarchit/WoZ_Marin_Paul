/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marin_paul;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Paul
 */
public class InterfaceInventory extends JFrame implements ActionListener, WindowListener  {
    
    private JLabel myLabelTitle,myLabelUse,myLabelName,myLabelEffect,myLabelPrice,myLabelGold;
    private JFrame myFrame;
    private JPanel myPanelTitle,myPanelHeading,myPanelBody,myPanelGold,myPanelInter,myGlobalPanel;
    
    private Icon pic;
    private Inventory inv;
    private int nbItem;
    private JButton arrayButton[];
    //private JButton closeInventory;
    private JLabel arrayLabel[];
    private JLabel arrayLabel2[];
    private JLabel arrayLabel3[];
    private JPanel arrayPanel[];
    private Player p;
    private LifeBar hpBar;
    private KarmaBar kBar;
    private int weaponEq = -1;
    private int armorEq = -1;
    private Game mainFrame;
    
    public InterfaceInventory(Player pl, NPC enemy, Game intTest){
        mainFrame = intTest;
        p = pl;
        // Title
        myPanelTitle = new JPanel();
        myLabelTitle = new JLabel("Inventory  "+p.getInventory().getLength()+"/"+p.getInventory().getSize());
        myLabelTitle.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        myPanelTitle.add(myLabelTitle);
        
        // Heading
        myPanelHeading = new JPanel(new GridLayout(1,4));
        myLabelUse = new JLabel("Use");
        myLabelUse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myLabelUse.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        myLabelName = new JLabel("Name");
        myLabelName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myLabelName.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        myLabelEffect = new JLabel("Effect");
        myLabelEffect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myLabelEffect.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        myLabelPrice = new JLabel("Price");
        myLabelPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myLabelPrice.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        
        myPanelHeading.add(myLabelUse);
        myPanelHeading.add(myLabelName);
        myPanelHeading.add(myLabelEffect);
        myPanelHeading.add(myLabelPrice);
        
        // Intermediate
        myPanelInter = new JPanel();
        myPanelInter.setLayout(new BorderLayout());
        myPanelInter.add(myPanelTitle,BorderLayout.NORTH);
        myPanelInter.add(myPanelHeading,BorderLayout.CENTER);
        
        // Body
        inv = p.getInventory();
        nbItem = inv.getLength();
        
        arrayButton = new JButton[nbItem];
        arrayLabel = new JLabel[nbItem];
        arrayLabel2 = new JLabel[nbItem];
        arrayLabel3 = new JLabel[nbItem];
        arrayPanel = new JPanel[nbItem];
        
        for (int i =0;i<nbItem;i++){
            arrayButton[i] = new JButton(inv.getItem(i).getPicture());
            arrayButton[i].setPreferredSize(new Dimension(35,35));
            arrayPanel[i] = new JPanel(new GridBagLayout());
            //GridBagConstraints gbc = new GridBagConstraints();
            arrayPanel[i].add(arrayButton[i],new GridBagConstraints());
           
            
            
            if (inv.getItem(i).getClass() == Weapon.class && p.getWeaponEquipped()!= null && p.getWeaponEquipped().equals(inv.getItem(i))){
                weaponEq = i;
                arrayLabel[i] = new JLabel(inv.getItem(i).getName()+ " (E)");
                arrayButton[i].setEnabled(false);
                arrayLabel[i].setForeground(Color.red);
            }else if (inv.getItem(i).getClass() == Armor.class && p.getArmorEquipped()!= null && p.getArmorEquipped().equals(inv.getItem(i))){
                armorEq = i;
                arrayLabel[i] = new JLabel(inv.getItem(i).getName() + " (E)");
                arrayButton[i].setEnabled(false);
                arrayLabel[i].setForeground(Color.red);
            }else{
                arrayLabel[i] = new JLabel(inv.getItem(i).getName());
            }
            arrayLabel[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            arrayLabel[i].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
            
            if (inv.getItem(i).getClass() == Weapon.class){
                arrayLabel2[i] = new JLabel("Damage : " +Integer.toString(((Weapon)inv.getItem(i)).getDamage()));
            }else if (inv.getItem(i).getClass() == Armor.class){
                arrayLabel2[i] = new JLabel("Armor : " +Integer.toString(((Armor)inv.getItem(i)).getDefense()));
            }else if (inv.getItem(i).getClass() == Consummable.class){
                if (((Consummable)inv.getItem(i)).getBonus()== 10){
                    arrayLabel2[i] = new JLabel("Bonus : Bag+" +Integer.toString(((Consummable)inv.getItem(i)).getBonus()));
                }else if (((Consummable)inv.getItem(i)).getBonus()>0){
                    arrayLabel2[i] = new JLabel("Bonus : HP+" +Integer.toString(((Consummable)inv.getItem(i)).getBonus()));
                }else{
                    arrayLabel2[i] = new JLabel("Damage enemy: HP" +Integer.toString(((Consummable)inv.getItem(i)).getBonus()));
                    arrayLabel2[i].setForeground(Color.red);
                    if (mainFrame.getActiveFight()){
                        if (enemy.getHP()==0){
                            arrayButton[i].setEnabled(false);
                        }else{
                        arrayButton[i].setEnabled(true);
                        }
                    }else{
                        arrayButton[i].setEnabled(false);
                    }
                }
                
            }else{
                arrayLabel2[i] = new JLabel("Door : " +(((Key)inv.getItem(i)).getDoor().getName()));
            }
            arrayLabel2[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            arrayLabel2[i].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
            
            arrayLabel3[i] = new JLabel(Integer.toString(inv.getItem(i).getPrice())+" gold");
            arrayLabel3[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            arrayLabel3[i].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        }
        
        
        myPanelBody = new JPanel(new GridLayout(nbItem,4));
        
        for (int i =0;i<nbItem;i++){
            myPanelBody.add(arrayPanel[i]);
            myPanelBody.add(arrayLabel[i]);
            myPanelBody.add(arrayLabel2[i]);
            myPanelBody.add(arrayLabel3[i]);
        }
        
        // Scroll Bar
        JScrollPane scrollPane = new JScrollPane(myPanelBody,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        
        // Gold + bars
        pic = new ImageIcon("images/gold.PNG");
        myPanelGold = new JPanel();
        myPanelGold.setLayout(new GridLayout(2,3));
        hpBar = new LifeBar();
        hpBar.setValueBar(p.getHP());
        kBar = new KarmaBar();
        kBar.setValueBar(p.getKarma());
        JLabel life = new JLabel("Life : ");
        life.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        life.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myPanelGold.add(life);
        JLabel gold = new JLabel("Gold : ");
        gold.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        gold.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myPanelGold.add(gold);
        JLabel karma = new JLabel("Karma : ");
        karma.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        karma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myPanelGold.add(karma);
        myPanelGold.add(hpBar.getLifeBar());
        myLabelGold = new JLabel(Integer.toString(p.getMoney()));
        myLabelGold.setIcon(pic);
        myLabelGold.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        myLabelGold.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myPanelGold.add(myLabelGold);
        myPanelGold.add(kBar.getKarmaBar());
        
        
        
      
        
        myGlobalPanel = new JPanel();
        myGlobalPanel.setLayout(new BorderLayout());
        myGlobalPanel.add(myPanelInter,BorderLayout.PAGE_START);
        myGlobalPanel.add(scrollPane,BorderLayout.CENTER);
        myGlobalPanel.add(myPanelGold,BorderLayout.SOUTH);
               
        
        // myFrame
        myFrame = new JFrame("My Inventory");
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
        myFrame.add(myGlobalPanel);
        myFrame.setResizable(false);
        myFrame.setPreferredSize(new Dimension(650,400));
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        
        for (int i =0;i<nbItem;i++){
            arrayButton[i].addActionListener(this);
        }
        
        myFrame.addWindowListener(this);
    }
    
    public void actionPerformed (ActionEvent e){
        
        Item it = null;
        for (int i =0;i<nbItem;i++){
            if (e.getSource() == arrayButton[i]){
                it = inv.getItem(i);
                if (it.getClass() == Weapon.class){ 
                    it.use(p);
                    arrayButton[i].setEnabled(false);
                    arrayLabel[i].setText(it.getName()+ " (E)");
                    arrayLabel[i].setForeground(Color.red);
                    if (weaponEq != -1){
                        arrayButton[weaponEq].setEnabled(true);
                        arrayLabel[weaponEq].setText(inv.getItem(weaponEq).getName());
                        arrayLabel[weaponEq].setForeground(Color.black);
                    }
                    weaponEq = i;
                }else if(it.getClass() == Armor.class){
                    it.use(p);
                    arrayButton[i].setEnabled(false);
                    arrayLabel[i].setText(it.getName()+ " (E)");
                    arrayLabel[i].setForeground(Color.red);
                    if(armorEq != -1){
                        arrayLabel[armorEq].setForeground(Color.black);
                        arrayButton[armorEq].setEnabled(true);
                        arrayLabel[armorEq].setText(inv.getItem(armorEq).getName());
                    }
                    armorEq = i;
                }else if(it.getClass() == Consummable.class){
                    if (((Consummable)inv.getItem(i)).getBonus()== 10){
                        System.out.println(" taille : "+p.getInventory().getSize());
                        it.use(p);
                        System.out.println("ok"+((Consummable)inv.getItem(i)).getBonus()+" taille : "+p.getInventory().getSize());
                    }else if (((Consummable)inv.getItem(i)).getBonus()>0){
                        it.use(p);
                        mainFrame.getLifeBar().setValueBar(p.getHP());
                    }else{
                        NPC enemy = mainFrame.getEnemyActive();
                        it.use(enemy);
                        mainFrame.getInterfaceBattle().getLifeBarE().setValueBar(enemy.getHP());
                        
                    }
                    inv.deleteObject(it);
                    mainFrame.getMainFrame().setEnabled(true);
                    myFrame.dispose();
                }
            }
        }
    }
    
    public void windowActivated(WindowEvent e){}
    public void windowClosed(WindowEvent e){mainFrame.getMainFrame().setEnabled(true);}
    public void windowClosing(WindowEvent e){mainFrame.getMainFrame().setEnabled(true);}
    public void windowDeactivated(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowOpened(WindowEvent e){mainFrame.getMainFrame().setEnabled(false);} 
}
