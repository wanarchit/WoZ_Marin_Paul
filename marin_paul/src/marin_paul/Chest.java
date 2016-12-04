package marin_paul;

import javax.swing.Icon;

/**
 *This class allows the creation of a chest which is an object which is used by the character
 * @Marion & Clément
 * version 22/11/2016
 */
public class Chest {
    
    private int money;//the money contained in the chest.
    private Inventory inv; // inventary presents in the chest created
    private String nameChest; // name of the chest created
    private Icon pic;
    
    /**
     * Constructor which allows the creation of the chest
     * @param name name of the chest to do a difference between the different chest
     * @param m money presents in the chest
     * @param sizeInv size of the inventory in the chest
     * @param pict of the chest
     */
    public Chest(String name, int m, int sizeInv, Icon pict){
        money = m;
        nameChest = name;
        inv = new Inventory(sizeInv);
        pic = pict;
    }
    
    
    /**
     * method which allows to change the money in the chest
     * @param mt of money in the chest
     */
    public void setMoney(int mt){  
        money = mt;
    }
    
    /**
     *method which returns the money in the chest 
     * @return the value of money in the chest
     */
    public int getMoney() {
        return money;
    }
    
    /**
     * method which returns the name of the chest 
     * @return the name of the chest
     */
    public String getNameChest(){
        return nameChest;
    }
    

    /**
     * Method which returns the inventory presents in the chest 
     * @return the inventory present in the chest
     */
    public Inventory getInventory(){
        return inv;
    }
    
    public Icon getIconChest(){
        return pic;
    }
}

