package marin_paul;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author Paul
 */
public class InterfaceDialogue2 extends JFrame implements ActionListener {
    private JFrame myFrame;
    private JPanel myPanelText,myPanelFinal;
    private JLabel labelNPC,labelNPC2,labelNameNPC;
    private JTextField textEnter;
    private JButton closeButton,validationButton;
    private NPC npcSpeak;
    private boolean answerWait;
    
    public InterfaceDialogue2(NPC npc){
        npcSpeak=npc;
        if (npcSpeak.getName().equals("Server")){
            labelNameNPC = new JLabel(npcSpeak.getName()+":");
            labelNPC = new JLabel("Hello, Welcome in WoZGame!");
            labelNPC2 = new JLabel("Do you want the password ?");
            answerWait=true;
        }else if(npcSpeak.getName().equals("Father Fouras")){
            
        }else if(npcSpeak.getName().equals("Keys Keeper")){
            
        }else if(npcSpeak.getName().equals("Grandfather Fouras")){
            
        }else if(npcSpeak.getName().equals("Smuggler")){
            
        }else if(npcSpeak.getName().equals("Princess")){
            
        }
        //labelNPC = new JLabel("Veux tu le mot de passe ?");
        labelNameNPC.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
        labelNPC.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        labelNPC2.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,20));
        JPanel panelDiagNPC = new JPanel(new BorderLayout(3,1));
        panelDiagNPC.add(labelNameNPC);
        panelDiagNPC.add(labelNPC);
        panelDiagNPC.add(labelNPC2);
        
        if(answerWait){
            
        }
        textEnter = new JTextField();
        textEnter.setPreferredSize( new Dimension( 200, 24 ) );
        myPanelText = new JPanel(new GridLayout(2,1));
        myPanelText.add(panelDiagNPC);
        myPanelText.add(textEnter);
        
        // Button
        JPanel panelBut = new JPanel(new GridLayout(1,2));
        validationButton = new JButton("Validate");
        closeButton = new JButton("Close");
        panelBut.add(validationButton);
        panelBut.add(closeButton);
        
        myPanelFinal = new JPanel(new GridLayout(2,1));
        myPanelFinal.add(myPanelText);
        myPanelFinal.add(panelBut);
        
        myFrame = new JFrame("Dialogue with "+npc.getName());
        myFrame.add(myPanelFinal);
        //myFrame.setSize(1000, 1000);
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //myFrame.setUndecorated(true);
        //myFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);
        myFrame.pack();
        myFrame.setVisible(true);
        
        closeButton.addActionListener(this);
        validationButton.addActionListener(this);
    }
    
    public void actionPerformed (ActionEvent e){
        if (e.getSource().equals(closeButton)){
            myFrame.dispose();
        }else{
            String text = textEnter.getText();
            if (npcSpeak.getName().equals("Server")){
                if (text.equals("oui")){
                    labelNPC.setText("Eh bah non !!!");
                }else if(text.equals("non")){
                    labelNPC.setText("Dommage...");
                }else{
                    labelNPC.setText("\"oui\" ou \"non\"?");}
                labelNPC2.setText("");
            }else if(npcSpeak.getName().equals("Father Fouras")){
            
            }else if(npcSpeak.getName().equals("Keys Keeper")){
            
            }else if(npcSpeak.getName().equals("Grandfather Fouras")){
            
            }else if(npcSpeak.getName().equals("Smuggler")){
            
            }else if(npcSpeak.getName().equals("Princess")){
            
        }
            
            
        }
    }
}
