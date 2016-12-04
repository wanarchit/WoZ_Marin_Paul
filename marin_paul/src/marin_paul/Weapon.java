package marin_paul;
import javax.swing.*;

/**
 * The class Weapon is a heiress class of the class Item. It handles 
 * everything about weapon.
 * @author Clément, Marion
 * version 22/11/2016
 */
public class Weapon extends Item {
    private int damage; // damage of the weapon
/**
 * constructor of the Weapon class
 * @param nI Weapon name
 * @param pc the price of the weapon
 * @param dg how much damage the weapon deals
 * @param pict icon of weapon
 */
public Weapon(String nI, int pc, int dg, Icon pict){
    super(nI,pc,pict);
    damage = dg;
}

/**
 * This method allows to use the weapon to deal damage
 * @param cha the character who uses the weapon
 */
public void use(Character cha){
    cha.setWeaponEquipped(this);
}

/**
 * Get the amount the damage the weapon deals
 * @return the damage of the weapon
 */
public int getDamage(){
    return damage;
}

}
