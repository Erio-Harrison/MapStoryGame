package GameEngine;
import java.util.*;  

/**
 * The Area class implements different areas
 */
public class Area {
    /**
     * NPCs in area
     */
    public ArrayList<NPC> NPCs;

    /**
     * Items in area
     */
    public ArrayList<Item> Items;

    /**
     * Area interaction
     */
    public Action action;

    /**
     * Area name
     */
    public String name;

    /**
     * constructor
     */
    public Area(String name, ArrayList<NPC> NPCs, ArrayList<Item> Items, Action action) {
        this.name = name;
        this.NPCs = NPCs;
        this.Items = Items;
        this.action = action;
    }

    public void enterArea() {
        System.out.println("You enter the " + this.name);
        this.action.doAction();
    }

}
