package marin_paul;
import javax.swing.*;

/**
 * This class allows to handle doors. Doors are the object between rooms so doors
 * knows the actual room and the room behind them. A door can be locked (physically
 * or magically). Actual room can be the same as next Room. Each door has a picture.
 * 
 * @author Jonathan, Babujhi
 */
public class Door {
    private boolean locked; //If the door is locked (true : locked)
    private Room actualRoom, nextRoom; //the room in which th edoor is and the room 
    //behind the door
    private Icon pic; // the picture of the Door.
    
    /**
     * Constructor of a Door
     * @param l a boolean to set the door to locked or not
     * @param actual the room in which the door is
     * @param next the room behind the door
     * @param picture of the door
     */
    public Door(boolean l, Room actual, Room next, Icon picture){     
        locked = l;
        actualRoom = actual;
        nextRoom = next;
        pic = picture;
    }
    
    /**
     * get the boolean locked (true : the door is locked, false : it is not)
     * @return the boolean locked
     */
    public boolean isLocked()
    {  
        return locked;
    }

    /**
     * Set the boolean locked to true or false, if it true it becomes false
     * and vice versa
     */
    public void setLocked(){    
        locked = !locked;
    }

    /**
     * Get which Room is behind the door
     * @return the next Room
     */
    public Room getNextRoom(){
        return nextRoom; 
    }

    /**
     * Get in which room the door is.
     * @return the actual room
     */
    public Room getActualRoom(){  
        return actualRoom;
    }
    
    /**
     * Get the picture of the door
     * @return the door as a picture
     */
    public Icon getPicture() {
        return pic;
    }
}
