/*
 * The inventory is an object composed by a list of items.
 * It will be used in multiples context such as the player inventory of a chest inventory
 * It can be empty
 * @version 22/11/16
 * @author Marion
 */
package marin_paul;
import java.util.ArrayList;

/**
 *
 * @author Paul
 */
public class Inventory {
    private ArrayList<Item> inv; //Array list of item composing the inventory
    private int size; // size of the inventory (of the array list)

/**
 * Constructor for the inventory
 * @param s size of the inventory
 */
public Inventory(int s)
{
    inv = new ArrayList<Item>();
    size=s;
}
/**
 * Setter for the inventory size
 * @param s Inventory size to be set
 */
public void setSize(int s)
{
    size=s;
}
/**
 * getter for the inventory size
 * @return int value of the inventory size
 */
public int getSize()
{
    return size;
}

public int getLength(){
    return inv.size();
}
/**
 * Method that allows to add an item to the inventory
 * @param i object added to the inventory
 */
public void addObject(Item i)
{
    if(inv.size()!= size){
        inv.add(i);
    }
}
/**
 * Method that allows to delete an item from the inventory
 * @param i object deleted from the inventory
 */
public void deleteObject(Item i)
{
    for(int j = 0; j < inv.size(); j++){
        if(inv.get(j).equals(i)){
            inv.remove(j);
            break;
        }
    }
}

/**
 * 
 * @param itemResearch object which is checked
 * @return boolean true or false
 */
public boolean checkInv(Item itemResearch){
    for(int j = 0; j < inv.size(); j++){
        if(inv.get(j).equals(itemResearch)){
            return true;}
    }
    return false;
}


public Item getItem(int numObject){
    return inv.get(numObject);
}

}

