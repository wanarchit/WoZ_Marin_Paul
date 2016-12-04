/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marin_paul;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author saspaanithy
 */
public class InterfaceTheEndPanel extends JPanel {
    
    Player pl;
    
    /*
    public InterfaceTheEndPanel(){
    karma_character = pl.getKarma(); 
    }
    */

    @Override
     
    
    public void paintComponent(Graphics g){
            
        try{
            if( pl.getKarma() <= 50){
            try{
                Image img = ImageIO.read(new File("/images/TheEndBad.png"));              
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
            
            catch (IOException e) {
                System.out.println("sa marche pas");
            }
            
            }
            
            else if (pl.getKarma() >= 50){
                try{  
        Image img = ImageIO.read(new File("/images/TheEndGood.png"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        
        catch (IOException e) {
        System.out.println("sa marche pas");
        }
            }
            
            
        }
        
        catch (Exception e) {
        System.out.println("sa marche pas");
        }
}
    }
    
      
        
  
    

     
       

                 
    
 


