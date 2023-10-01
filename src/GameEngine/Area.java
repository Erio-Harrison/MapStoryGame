package GameEngine;
import java.util.*;

/**
 * Represents an Area in the game.
 * The Area class is intended to encapsulate information about different areas within the game.
 * Each area can contain a set of NPCs, items, an associated action, and has a name.
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
     * Constructs a new Area instance with specified name, NPCs, Items, and action.
     *
     * @param name the name of the area
     * @param NPCs the list of NPCs present in the area
     * @param Items the map of Items available in the area with their quantities
     * @param action the action associated with this area
     */
    public Area(String name, ArrayList<NPC> NPCs, Map<Item, Integer> Items, Action action) {
        this.name = name;
        this.NPCs = NPCs;
        this.Items = Items;
        this.action = action;
    }
    /**
     * Invoked when the player enters this area.
     * Prints the message that the player has entered this area and then performs the associated action.
     */
    public void enterArea() {
        System.out.println("You enter the " + this.name);
        this.action.doAction();
    }
    /**
     * Retrieves a list of all NPCs present in this area.
     *
     * @return a list of NPCs in the area
     */
    public List<NPC> getNPCs() {
        return NPCs;
    }

}
