package GameEngine;

import java.util.Map;

/**
 * Author: Weiyuan
 * Represents an action that adds an item to a player's inventory or "backpack".
 * This class offers a mechanism to increase the player's inventory.
 * <p>Usage example:
 * <pre>
 *    Action addToBackpackAction = new AddToBackpack(gameInstance, potionItem);
 *    addToBackpackAction.doAction();  // Adds a potion to the player's inventory.
 * </pre>
 * </p>
 */
public class AddToBackpack extends Action {
    private Map<Item, Integer> itemsToAdd;

    /**
     * Constructs a new {@code AddToBackpack} action.
     *
     * @param game       the current game instance
     * @param itemsToAdd the item to be added to the player's inventory
     */
    public AddToBackpack(Game game, Map<Item, Integer> itemsToAdd) {
        super(game);
        this.itemsToAdd = itemsToAdd;
    }

    /**
     * Executes the action, adding the specified item to the player's inventory.
     * If the player already has the item, its quantity is increased.
     */
    @Override
    public void doAction() {
        // add items to area
        for (Item item : this.itemsToAdd.keySet()) {
            int toAdd = this.itemsToAdd.get(item);

            if (game.player.backpack.containsKey(item)) {
                int currentQuantity = game.player.backpack.get(item);
                game.player.backpack.put(item, currentQuantity + toAdd);
            } else {
                game.player.backpack.put(item, toAdd);
            }
        }
    }
}
