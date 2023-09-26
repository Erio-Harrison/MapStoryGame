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
     * Item interaction
     */
    public Action interact;

    public Item(String ID, Action interaction) {
        this.ID = ID;
        this.interact = interaction;
    }
}
