package GameEngine;

/**
 * Represents an Item in the game.
 * The Item class is designed to hold details of individual items present within the game,
 * including an identifier, a description, and the action associated with interacting with the item.
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
    /**
     * Constructs a new Item instance with the specified ID, description, and interaction action.
     *
     * @param ID a unique identifier for the item
     * @param description a descriptive text for the item
     * @param interaction the action to be executed when interacting with the item
     */
    public Item(String ID, String description, Action interaction) {
        this.ID = ID;
        this.description = description;
        this.interact = interaction;
    }
}
