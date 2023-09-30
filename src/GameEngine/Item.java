package GameEngine;
import java.util.*;  

/**
 * Implements items in game
 */
public class Item {
    /**
     * Item ID
     */
    public String ID;

    /**
     * Item description
     */
    public String description;

    /**
     * Item interaction
     */
    public Action interact;

    public Item(String ID, String description, Action interaction) {
        this.ID = ID;
        this.description = description;
        this.interact = interaction;
    }
}
