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
}
