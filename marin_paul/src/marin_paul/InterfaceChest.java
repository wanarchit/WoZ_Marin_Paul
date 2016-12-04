package marin_paul;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Paul
 */
public class InterfaceChest extends JPanel implements ActionListener{
    
    //private JPanel ? ;
    private JLabel labelName, labelText;
    private JButton end, search;
    private Player p;
    private Chest myChest;
    private Game mainFrame;
    private Graphique mainPanel;
    private JPanel ssP,ssP1,ssP2;
    
    public InterfaceChest(Player myPlayer, Chest chest, Graphique panel){
        p = myPlayer;
        myChest = chest;
        mainPanel = panel;
        mainFrame = mainPanel.getMainFrame();
        
        
       // myPanelBattle.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridLayout(3,1));
        GridBagConstraints gbc = new GridBagConstraints();
        labelName = new JLabel("Chest name : ");
        labelName.setHorizontalAlignment(JLabel.CENTER);  
        labelName.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        labelText = new JLabel(myChest.getNameChest());
        labelText.setHorizontalAlignment(JLabel.CENTER);
        labelText.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        ssP = new JPanel(new GridLayout(2,1));
        ssP.add(labelName);
        ssP.add(labelText);
        
        search = new JButton("Search");
        mainPanel.defaultButton2(search,"Search");
        ssP1 = new JPanel();
        ssP1.setLayout(new GridBagLayout());
        ssP1.add(search,gbc);
        end = new JButton();
        mainPanel.defaultButton2(end,"Close");
        ssP2 = new JPanel();
        ssP2.setLayout(new GridBagLayout());
        ssP2.add(end,gbc);

       // this.add(labelName);
        this.add(ssP);
        this.add(ssP1);
        this.add(ssP2);
        this.setPreferredSize(new Dimension(200,200));
        //this.setVisible(false);
        
        search.addActionListener(this);
        end.addActionListener(this);
    }
    
    public void actionPerformed (ActionEvent e){
        if (e.getSource().equals(search)){
            if (myChest.getMoney()!=0){
                JFrame messFrame = new JFrame();
                JOptionPane d = new JOptionPane();
                d.showMessageDialog(messFrame, "You earn "+myChest.getMoney()+" gold!", 
                "You earn money !", JOptionPane.INFORMATION_MESSAGE);
                p.setMoney(p.getMoney()+myChest.getMoney());
                myChest.setMoney(0);
            }
            new InterfaceTrade2(p,myChest);
    
        }else if (e.getSource().equals(end)){
            this.setVisible(false);
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
                
        }
    }
}

    

