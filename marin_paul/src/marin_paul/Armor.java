package marin_paul;
import javax.swing.*;
//the class Armor is a heiress class of the class Item.

/**
 * This class allows the creation of the armor which is an object which is used by the character.
 * @Marion & Clément
 * version 22/11/2016
 */

public class Armor extends Item {
    private int defense; // creation of the object's defense
    
/**
 * Constructor with the parameteers of the class Item and the defense pararmeter.
 * @param nI name of the item
 * @param pc price of the object
 * @param def defense 
 * @param pict icon of armor item
 */
public Armor(String nI, int pc, int def,Icon pict){
    super(nI,pc,pict);
    defense = def;   
}

/**
 * the method allows Characters to use an item allowing the defense.
 * @param character 
 */
public void use(Character c){
    c.setArmorEquipped(this);
}

/**
 * The method returns the value of the defense of the Character.
 * @return value of character defense
 */
public int getDefense(){
return defense;
}
}
