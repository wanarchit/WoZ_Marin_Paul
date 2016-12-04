package marin_paul;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Paul
 */
public class InterfaceBattle extends JPanel implements ActionListener{
    
    
    private JButton beat, escape, end, search;
    private JLabel myLabel,myLabelMsg,myLabelRound,myLabelDegP,myLabelDegE;
    private JPanel ssP,ssP1,ssP2,ssP3,ssP4,ssP5,ssP6;
    private Player p;
    private NPC enemy;
    private LifeBar lifeB,lifeB2;
    private KarmaBar karmaB;
    private int round;
    private Icon imgRunAway = new ImageIcon("images/fuite2.PNG");
    //private JFrame mainFrame;
    private Graphique mainPanel;
    private Game mainFrame;
    
    public InterfaceBattle(Player myPlayer, NPC enemyNPC, Graphique panel){
        p = myPlayer;
        enemy = enemyNPC;
        mainPanel = panel;
        mainFrame = mainPanel.getMainFrame();
        lifeB = mainFrame.getLifeBar();
        karmaB = mainFrame.getKarmaBar();
        // my Button
        
        
       // myPanelBattle.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridLayout(6,1));
        GridBagConstraints gbc = new GridBagConstraints();
        myLabel = new JLabel("Enemy life : ");
        myLabel.setHorizontalAlignment(JLabel.CENTER);
        myLabel.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        lifeB2 = new LifeBar();
        lifeB2.setValueBar(enemy.getHP());
        lifeB2.getLifeBar().setForeground(Color.red);
        ssP = new JPanel();
        ssP.setLayout(new GridLayout(2,1));
        ssP.setBorder(BorderFactory.createLineBorder(Color.black));
        ssP.add(myLabel);
        ssP.add(lifeB2.getLifeBar());
        ssP1 = new JPanel();
        ssP1.setLayout(new GridBagLayout());
        ssP1.add(ssP,gbc);
        
        if (enemy.getHP() == 0){
            myLabelMsg = new JLabel(enemy.getName()+ " is already dead !");
        }else{
            myLabelMsg = new JLabel("You fight ");
        }
        myLabelRound = new JLabel(enemy.getName());
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
        
        beat = new JButton();
        mainPanel.defaultButton2(beat,"HIT HIM");
        ssP3 = new JPanel();
        ssP3.setLayout(new GridBagLayout());
        ssP3.add(beat,gbc);
        escape = new JButton("Run away");
        mainPanel.defaultButton2(escape,"Run away");
        ssP4 = new JPanel();
        ssP4.setLayout(new GridBagLayout());
        ssP4.add(escape,gbc);
        search = new JButton("Search");
        mainPanel.defaultButton2(search,"Search");
        ssP5 = new JPanel();
        ssP5.setLayout(new GridBagLayout());
        ssP5.add(search,gbc);
        end = new JButton("Close");
        mainPanel.defaultButton2(end,"Close");
        ssP6 = new JPanel();
        ssP6.setLayout(new GridBagLayout());
        ssP6.add(end,gbc);
        
        if (enemy.getHP() == 0){
            beat.setEnabled(false);
            escape.setEnabled(false);
            search.setEnabled(true);
            end.setEnabled(true);
        }else{
            beat.setEnabled(true);
            escape.setEnabled(true);
            search.setEnabled(false);
            end.setEnabled(false);
        }

        this.add(ssP1);
        this.add(ssP2);
        this.add(ssP3);
        this.add(ssP4);
        this.add(ssP5);
        this.add(ssP6);
        this.setPreferredSize(new Dimension(200,200));
        //this.setVisible(false);
        


        beat.addActionListener(this);
        escape.addActionListener(this);
        end.addActionListener(this);
        search.addActionListener(this);
    }
    
    public void actionPerformed (ActionEvent e){
       
       
         if (e.getSource().equals(beat)){
            round += 1;
            Battle duel = new Battle(p,enemy);
            if (round != 1){
                myLabelMsg.setText("");
            }else{myLabelMsg.setText("Battle Go !!");}
            
            myLabelRound.setText("Round: "+round);
            int hpP = p.getHP();
            int hpE = enemy.getHP();
            duel.beat();
            lifeB.setValueBar(p.getHP());
            lifeB2.setValueBar(enemy.getHP());
            myLabelDegE.setText(enemy.getName()+" loses "+(hpE-enemy.getHP())+"HP");
            myLabelDegP.setText(p.getName()+" loses "+(hpP-p.getHP())+"HP");
            if (enemy.getHP() == 0){
                myLabelDegE.setText(enemy.getName()+" is dead !");
                myLabelDegP.setText(p.getName()+" win !");
                end.setEnabled(true);
                beat.setEnabled(false);
                search.setEnabled(true);
                escape.setEnabled(false);
                mainPanel.setActiveFight(false);
                if (mainPanel.isSeller()){
                    p.del10Karma();
                }else{
                    p.add10Karma();
                    p.gainHP(10);
                }
                
                karmaB.setValueBar(p.getKarma());
                lifeB.setValueBar(p.getHP());
        }
        }else if (e.getSource().equals(escape)){
            
            //JOptionPane option2 = new JOptionPane(); //Creation d'un nouveau message
            Object[]options = {"Run away..","Stay in battle!"};
                
            int option = JOptionPane.showOptionDialog(this,
            "Are you sure you want to run away, you will lose 10 HP?", //Question de confirmation
            "Confirmation :",//nom de la boîte d'affichage de la question
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            imgRunAway,   
            options,
            options[1]); // par default précliqué sur 0=yes, 1 = no
            
            if(option == JOptionPane.YES_OPTION){
                p.loseHP(-10);
                lifeB.setValueBar(p.getHP());
                //lifeB.setValueBar(p.getHP());
                escape.setEnabled(false);
                end.setEnabled(true);
                beat.setEnabled(false);
                myLabelMsg.setText("You run away ...");
                myLabelRound.setText("At round: "+round);
                myLabelDegE.setText("");
                myLabelDegP.setText(p.getName()+" loses 10 HP");
                mainPanel.setActiveFight(false);
            }
            
            
        }else if (e.getSource().equals(end)){
            this.setVisible(false);
            //mainFrame.setPanelVide();
            
            JButton[] arrayButtons = mainPanel.getNPCButton();
            for (int j =0;j<mainPanel.getNbNPC();j++){
                arrayButtons[j].setEnabled(true);  
            }
            JButton[] arrayButtons2 = mainPanel.getChestButton();
            for (int j =0;j<mainPanel.getNbChest();j++){
                arrayButtons2[j].setEnabled(true);  
            }
            mainPanel.activateDoorBut();
            mainPanel.setActiveFight(false);
            
        
        
        }else if (e.getSource().equals(search)){
            
            if (enemy.getHP()==0 && enemy.getMoney()!=0){
                JFrame messFrame = new JFrame();
                JOptionPane d = new JOptionPane();
                d.showMessageDialog(messFrame, "You earn "+enemy.getMoney()+" gold!", 
                "You earn money !", JOptionPane.INFORMATION_MESSAGE);
                p.setMoney(p.getMoney()+enemy.getMoney());
                enemy.setMoney(0);
            }
            new InterfaceTrade(p,enemy);
        }   
    }

    public LifeBar getLifeBarE(){
        return lifeB2;
    }
    
}
