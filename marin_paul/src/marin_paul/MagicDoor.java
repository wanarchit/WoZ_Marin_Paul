package marin_paul;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathan, Babujhi
 */


public class MagicDoor extends Door { 
    
    private String pwd; 
    private String pwd_user;
    private String name;
    
    



/* The constructor allowed the creation of a MagicDoor
 * The magic door is closed and can only be open thanks to a code.
*/

    /**
     *
     * @param n
     * @param p
     * @param lock
     * @param Actual
     * @param Next
     * @param pictDoor of door
     */

    public MagicDoor(String n, String p, Boolean lock, Room Actual, Room Next, Icon pictDoor)
{
            
        super(lock, Actual, Next, pictDoor);
        pwd = p;
        name = n; 
}

    /*
    The method resolve allowed the user to enter a string and compare the pwd
    with the code of the Magic door.
    */
    
    /**
     * Check if the password given by the player is the same as the password which open the door
     * @param pwd_user
     * @return boolean
     */
    
    public boolean resolve (String pwd_user) {
        return pwd_user.equals(pwd);
    }
    
    /**
     * 
     * @return The password of the door
     */
    public String getPwd() {
        return pwd;
    }
    
    /**
     * 
     * @return the name of the door
     */
    public String getName() {
        return name;
    }
    
}