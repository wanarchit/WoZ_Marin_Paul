package marin_paul;

import java.util.*;
import javax.swing.*;

/**
 * This class allows to handle Room. Each Room has a name,a set of character, 
 * there is possibility that the room has chests in it. Each room has one or 
 * several exits in it. Finally it is possible to set a room as clear or not.
 * @author Jonathan, Babujhi
 */
public class Room {
    private boolean clear,visited;
    private ArrayList<NPC> characterRoom; //pas deux fois le meme
    private ArrayList<Chest> chestRoom; // pas deux fois le meme
    private String nameRoom;
    private HashMap <String, Door> exit; //pas deux fois la meme porte
    private Icon pictRoom;

    /**
     * Constructor of a Room, the HashMap and the ArrayList are empty when the 
     * class is created.
     * @param name if there is no name, it automatically set to "Room"
     * @param empty set the boolean clear in the constructor
     */
    public Room(String name, boolean empty, boolean visit, Icon pic){
        clear = empty;
        visited = visit;
        nameRoom = name;
        exit = new HashMap <>();
        characterRoom = new ArrayList<NPC>();
        chestRoom = new ArrayList<Chest>();
        pictRoom = pic;
    }

    /**
     * Get if the room is clear (true : clear, false : not clear)
     * @return a boolean depending if the room is clear or not
     */
    public boolean isClear(){
        return clear;
    }
    
    /**
     * Get if the room is visited (true : visited, false : not visited)
     * @return a boolean depending if the room is visited or not
     */
    public boolean isVisited(){
        return visited;
    }
    
    /**
     * Allows to modify the parameter visited, which is a boolean. If the boolean is 
     * true it becomes false and vice versa.
     */
    public void setVisited(){
        visited = !visited;
    }

    /**
     * Get the name of the Room
     * @return a String, the name of the Room
     */
    public String getNameRoom(){
        return nameRoom;
    }

    /**
     * Allows to modify the parameter clear, which is a boolean. If the boolean is 
     * true it becomes false and vice versa.
     */
    public void setClear(){
        clear = !clear;
    }

    /**
     * Create a new exit and put it in the exit Hashmap. If the name is empty, it is 
     * set to "North".
     * @param s the direction of the exit
     * @param d a Door between two Rooms (knows actual and next room)
     */
    public void setExit(String s, Door d){
        boolean bool = true;
        for (HashMap.Entry<String, Door> entry : exit.entrySet()) {
            if (entry.getValue().getNextRoom().equals(d.getNextRoom())) {
                bool = false;
                break;
            }
        }
        if (bool && exit.containsKey(s)==false) {
            exit.put(s,d);
        }
    }
    
    public Door getValue(String s){
        for (HashMap.Entry<String, Door> entry : exit.entrySet()) {
            if (entry.getKey().equals(s)) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    /**
     * Add a NPC in the ArrayList characterRoom
     * @param n a NPC who is in the room
     */
    public void addNPC (NPC n) {
        characterRoom.add(n);
    } 
    
    
    /**
     * Add a Chest in the ArrayList chestRoom
     * @param c a Chest which is in the Room
     */
    public void addChest (Chest c) {
        chestRoom.add(c);
    }
    
    /**
     * Get the whole HashMap
     * @return the HashMap containing the exits
     */
    public HashMap getHashMap() {
        return exit;
    }
    
    /**
     * Give the size (the number of door) of the HashMap
     * @return an int, the size of the hashmap
     */
    public int getSizeHashMap() {
        return exit.size();
    }
    
    /**
     * Give the list of NPC present in the room
     * @return an Arraylist, the entire List of character in the room
     */
    public ArrayList<NPC> getNPCRoom(){
        return characterRoom;
    }
    
    /**
     * Give the lisr of Chest of the Room
     * @return an ArrayList, the entire List of chest in the room
     */
    public ArrayList<Chest> getChestRoom(){
        return chestRoom;
    }
    
    /**
     * Give just one NPC of the list
     * @param posListNPC, the number of the line
     * @return the NPC at the position of the int given
     */
    public NPC getNPC(int posListNPC){
        return characterRoom.get(posListNPC);
    }
    
    /**
     * Give just one chest of the list
     * @param posListChest, the number of the line
     * @return the chest at the position of the int given
     */
    public Chest getChest(int posListChest) {
        return chestRoom.get(posListChest);
    }
    
    public Icon getPicture(){
        return pictRoom;
    }
}