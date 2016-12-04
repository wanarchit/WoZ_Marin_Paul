/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marin_paul;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Paul
 */
public class KarmaBar {
    private JProgressBar bar;
    private int value;
    
    public KarmaBar(){
        bar = new JProgressBar();
        bar.setMaximum(100);
        bar.setMinimum(0);
        bar.setStringPainted(true);
        bar.setValue(50);
        bar.setBorderPainted(false);
        bar.setBackground(Color.black);
        bar.setForeground(Color.blue);
    }
    
    public JProgressBar getKarmaBar(){
        return bar;
    }
    
    public void setValueBar(int val){
        bar.setValue(val);
        colorBar();
    }
    
    public void colorBar(){
        if (bar.getValue() < 50){
            bar.setForeground(Color.red);
        }else{
            bar.setForeground(Color.blue);
        }
    }
}
