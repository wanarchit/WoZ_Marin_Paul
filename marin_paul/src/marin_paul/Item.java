/*
*
*/
package marin_paul;
import javax.swing.*;
/**
 * The class Item
 * 
 * @author Marion
 * @version 22/11/2016 
 */
abstract class Item {
    // Name of item, it cannot be changed
    private String nameItem;
    //Item must belong to an inventory (of character or chest)
    // So we add in inventory the item
    //protected Inventory inv;
    // Price of item, which will using for the sale of object.
    private int price;
    private Icon pic;
    
    public Item (String n, int p, Icon pict){
        nameItem =n;
        price = p;
        pic = pict;
    }

    /**
     *
     */
public void use(Character c){
    
}

/**
 * Utility function which allow getting the price of object
 * @return price of object
 */
public int getPrice(){
    return price;
}


public String getName(){
return nameItem;
}

/**
 * 
 * @return picture of 
 */
public Icon getPicture(){
    return pic;
}
}