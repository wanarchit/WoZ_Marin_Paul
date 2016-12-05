package marin_paul;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author Paul
 */
public class InterfaceDialogue2 extends JFrame { 
    
    public InterfaceDialogue2(Player p, NPC npc,Game frame){
        Icon erreurIcon = new ImageIcon("images/no.PNG");
        Icon coeurIcon = new ImageIcon("images/vie.PNG");
        Icon interogation = new ImageIcon("images/enigme.PNG");
        Icon triangle = new ImageIcon("images/enigmeTriangle.PNG");
        
        if(npc.getName().equals("Server")){
            String inputValue = JOptionPane.showInputDialog(
                    "Hello! What is your name?");
            if(inputValue.toUpperCase().equals(p.getName().toUpperCase())){
                JFrame messFrame = new JFrame();
                JOptionPane d = new JOptionPane();
                d.showMessageDialog(messFrame, "OK, it is a good name ! Tries to remember it...", 
                "", JOptionPane.INFORMATION_MESSAGE);
                
            }else{
                JFrame messFrame = new JFrame();
                JOptionPane d = new JOptionPane();
                d.showMessageDialog(messFrame, "What strange name ... ", 
                "", JOptionPane.INFORMATION_MESSAGE);
                Object[]options = {"Accept !","Refuse !"};
                
            int option = JOptionPane.showOptionDialog(this,
            "You lose 10 karma because you do not know your name ",
            "",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            erreurIcon,   
            options,
            options[1]);
            if(option == JOptionPane.YES_OPTION){
                p.del10Karma();
                JFrame messFrame2 = new JFrame();
                JOptionPane d2 = new JOptionPane();
                d2.showMessageDialog(messFrame2, "You lose 10 karma", 
                "", JOptionPane.INFORMATION_MESSAGE);
                frame.getKarmaBar().setValueBar(p.getKarma());
                frame.revalidate();
            }else if(option == JOptionPane.NO_OPTION){
                JFrame messFrame2 = new JFrame();
                JOptionPane d2 = new JOptionPane();
                d2.showMessageDialog(messFrame2, "You should had accepted. You lose 20 karma", 
                "", JOptionPane.INFORMATION_MESSAGE);
                p.del10Karma();p.del10Karma();
                frame.getKarmaBar().setValueBar(p.getKarma());
                frame.revalidate();
            }
        }
            
        }else if(npc.getName().equals("Father Fouras")){
            Object[]options = {"Accept !","Refuse !"};
            int option = JOptionPane.showOptionDialog(this,
            "Let me ask you a question ?",
            "",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            interogation,   
            options,
            options[1]);
            if(option == JOptionPane.YES_OPTION){
                String[] answers = {"Feet","Breath","Srussels Sprouts","Cheese","Socks","Shoes","Toilets","Sewers","Manure"};
                JFrame frame2 = new JFrame("Enigma");
                String answer = (String) JOptionPane.showInputDialog(frame2,
                "<html> I am never far from my other twin,<br>"
                        + "I am often associated with the vomiting fragrance,<br>"
                        + "To a part of the body which is not really beautiful ,<br>"
                        + "Located far from the olfactory organ,<br>"
                        + "<br>"
                        + "I am ?</html>",
                "",
                JOptionPane.QUESTION_MESSAGE,
                interogation,
                answers,
                answers[0]);
                if (answer == "Socks"){
                    JFrame messFrame2 = new JFrame();
                    JOptionPane d2 = new JOptionPane();
                    d2.showMessageDialog(messFrame2, "<html>Right answer,<br>"
                            + "The password is \"BCJMMP\",<br>"
                            + "It allows opening the Cellar Door in the Dark Corridor.</html>",
                    "", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JFrame messFrame2 = new JFrame();
                    JOptionPane d2 = new JOptionPane();
                    d2.showMessageDialog(messFrame2, "<html>Wrong answer,<br>"
                            + "You lose 10 HP,</html>",
                    "", JOptionPane.INFORMATION_MESSAGE);
                    p.loseHP(-10);
                    frame.getLifeBar().setValueBar(p.getHP());
                    frame.revalidate();
                }

            }else if(option == JOptionPane.NO_OPTION){
                p.del10Karma();
                JFrame messFrame2 = new JFrame();
                JOptionPane d2 = new JOptionPane();
                d2.showMessageDialog(messFrame2, "You lose 10 karma", 
                "", JOptionPane.INFORMATION_MESSAGE);
                frame.getKarmaBar().setValueBar(p.getKarma());
                frame.revalidate();
            }
        }else if(npc.getName().equals("Keys Keeper")){
                JFrame messFrame = new JFrame();
                JOptionPane d = new JOptionPane();
                d.showMessageDialog(messFrame, "I have plenty of stuff to sell, even a key ...", 
                "", JOptionPane.INFORMATION_MESSAGE);
                
        }else if(npc.getName().equals("Julien Lepers")){
            int lifeLost=0;
            Object[]options = {"Yes!","No."};
            int option = JOptionPane.showOptionDialog(this,
            "<html>I will ask you three questions<br> "
                    + "If you answer correctly each question you will have a clue to find the password<br>"
                    + "Are you ready?",
            "",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            interogation,   
            options,
            options[1]);
            if(option == JOptionPane.YES_OPTION){
                Object[]options2 = {"8 triangles","9 triangles","10 triangles","11 triangles"};
                int options3 = JOptionPane.showOptionDialog(this,
                "<html>Top! <br>"
                        + "Qustion 1 :<br>"
                        + "How many triangle do you see?</html>",
                "Question one",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                triangle,   
                options2,
                options2[1]);
                if (options3 != 2){lifeLost-=5;}
                
                String inputValue = JOptionPane.showInputDialog(
                    "<html> Question 2:<br>"
                        + "I am 42 years old,<br>" 
                        + "in 20 years I will be twice the age of my son.<br>"
                        + "<br>"
                        + "How old is my son?</html>");
                String[] answers = {"11","11ans","11 ans","11 years","11 years old","eleven"};
                boolean validate = false;
                for (String str: answers){
                    if(inputValue.toUpperCase().equals(str.toUpperCase())){
                        validate = true;
                        break;}}
                if (!validate){lifeLost-=5;}
                
                String[] answers2 = {"0","1","25, 49","50"};
                JFrame frame2 = new JFrame("Question 3:");
                String answer = (String) JOptionPane.showInputDialog(frame2,
                "<html> Question 3 and last:<br>"
                        + "How many days of birthdays has a person who has lived 50 years?<br>"
                        + "<br>",
                "",
                JOptionPane.QUESTION_MESSAGE,
                interogation,
                answers2,
                answers2[0]);
                if (answer != "1"){lifeLost-=5;}
                
                if(lifeLost==0){
                    JFrame messFrame = new JFrame();
                    JOptionPane d = new JOptionPane();
                    d.showMessageDialog(messFrame, "<html>Congratulation!<br>"
                            + "All your answers are correct.<br>"
                            + "One of the three answers is a password...</html>",
                    "", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    
                }
                
            }
        }else if(npc.getName().equals("Smuggler")){
            JFrame messFrame = new JFrame();
            JOptionPane d = new JOptionPane();
            d.showMessageDialog(messFrame, "<html>I have full \"legal\" object for sale<br>Look, everything comes from the treasures room ..", 
            "", JOptionPane.INFORMATION_MESSAGE);
            
            
        }else if(npc.getName().equals("Princess")){
            if (npc.getInventory().getLength()==1){
                Object[]options = {"Accept !","Refuse !"};
                int option = JOptionPane.showOptionDialog(this,
                "<html>Oh thank you, you saved me!<br>Accpet this kiss!",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                coeurIcon,   
                options,
                options[1]);
                if(option == JOptionPane.YES_OPTION){
                    if ((p.getInventory().getLength()+1) > p.getInventory().getSize()){
                        p.getInventory().setSize(p.getInventory().getSize()+1);
                        Item obj=npc.getInventory().getItem(0);
                        p.getInventory().addObject(obj);
                        npc.getInventory().deleteObject(obj);
                    }else{
                        Item obj=npc.getInventory().getItem(0);
                        p.getInventory().addObject(obj);
                        npc.getInventory().deleteObject(obj);
                    }

                    for (int i=0;i<10;i++){p.add10Karma();}
                    JFrame messFrame2 = new JFrame();
                    JOptionPane d2 = new JOptionPane();
                    d2.showMessageDialog(messFrame2, "You won a kiss and your karma is full!", 
                    "", JOptionPane.INFORMATION_MESSAGE);
                    frame.getKarmaBar().setValueBar(p.getKarma());
                    frame.revalidate();
                }else if(option == JOptionPane.NO_OPTION){
                    for (int i=0;i<5;i++){p.del10Karma();}
                    JFrame messFrame2 = new JFrame();
                    JOptionPane d2 = new JOptionPane();
                    d2.showMessageDialog(messFrame2, "You should had accepted. You lose 50 karma", 
                    "", JOptionPane.INFORMATION_MESSAGE);
                    frame.getKarmaBar().setValueBar(p.getKarma());
                    frame.revalidate();
                }
            }else{
                JFrame messFrame2 = new JFrame();
                JOptionPane d2 = new JOptionPane();
                d2.showMessageDialog(messFrame2, "<html>Thanks again,<br> But you already have kissed me", 
                "", JOptionPane.INFORMATION_MESSAGE);
            }
        }        
    }
}
