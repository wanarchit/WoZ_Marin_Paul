
package marin_paul;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saspaanithy
 */
public class InterfaceGameOver extends JPanel {
        
    Player pl;
    
    
    public void InterfaceGameOver(Graphics g){
    }        
    
    @Override
    
    public void paintComponent(Graphics g){
        try{
            
        if (pl.getHP() == 0){
        try{
                Image img = ImageIO.read(new File("/images/TheGameOver.png"));              
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        
        catch (IOException e) {      
        System.out.println("sa marche pas");
        }
        }
        }
        catch(Exception e){
            System.out.println("Sa marche pas");
        }
        
    }
}
        
        