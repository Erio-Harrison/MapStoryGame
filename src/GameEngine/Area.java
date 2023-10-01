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
    public Map<Item, Integer> Items;

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
    public Area(String name, ArrayList<NPC> NPCs, Map<Item, Integer> Items, Action action) {
        this.name = name;
        this.NPCs = NPCs;
        this.Items = Items;
        this.action = action;
    }

    public void enterArea() {
        System.out.println("You enter the " + this.name);
        this.action.doAction();
    }
    /**
     * Gets all NPCs in the area
     *
     * @return A list of NPCs in the area
     */
    public List<NPC> getNPCs() {
        return NPCs;
    }

}
