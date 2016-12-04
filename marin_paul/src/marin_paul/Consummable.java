/*
*  
* 
*/
package marin_paul;
import javax.swing.*;
//the class Consummable is a heiress class of the class Item.

/**
 * This class allows the creation of an consummable object like food or drink or poison
 *
 * @Marion & Clément
 * version 22/11/2016
 */
public class Consummable extends Item{
    private int bonus; // bonus is a specific attribut of consummable
    
    /**
     * Constructor of the consummable object, allows its creation
     * @param nI name of the item
     * @param pc price 
     * @param bns bonus obtained thanks to the consummable
     * @param pict it is icon of object consummable
     */
public Consummable(String nI, int pc, int bns, Icon pict){
    super(nI,pc,pict);
    bonus = bns; 
}

    /**
     * the method allows Characters to drink or eat a consummable
     * If it is a positive bonus : it is apply to player in gaining of HP
     * If it is a negative bonus : it is apply to NPC in losing of HP
     * @param character : could be player (bonus) or enemy (malus)
     */
public void use(Character character){
    if (bonus == 10){
        character.getInventory().setSize(character.getInventory().getSize()+10);
    }else if(bonus>0){
        character.gainHP(bonus);
    }else{
        character.loseHP(bonus);
    }
}

    /**
     *this method allows to returns the bonus obtained thanks to the consummable
     * @return the number of bonus/malus (neg)
     */
public int getBonus(){
    return bonus;
}
}

    
