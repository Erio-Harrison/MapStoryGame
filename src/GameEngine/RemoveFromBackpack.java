package GameEngine;

import java.util.Map;

/**
 * Author: Weiyuan
 * Represents an action that removes an item from a player's inventory or "backpack".
 * This class facilitates the removal or usage of items from the player's inventory.
 * <p>Usage example:
 * <pre>
 *    Action removeFromBackpackAction = new RemoveFromBackpack(gameInstance, potionItem, 1);
 *    removeFromBackpackAction.doAction();  // Removes one potion from the player's inventory.
 * </pre>
 * </p>
 */
public class RemoveFromBackpack extends Action {
    private Map<Item, Integer> itemsToRemove;
    private int quantityToRemove;

    /**
     * Constructs a new {@code RemoveFromBackpack} action.
     *
     * @param game          the current game instance
     * @param itemsToRemove the map containing items and their corresponding quantities to be removed from the player's inventory
     */
    public RemoveFromBackpack(Game game, Map<Item, Integer> itemsToRemove) {
        super(game);
        this.itemsToRemove = itemsToRemove;
    }

    /**
     * Executes the action, removing the specified quantity of the item from the player's inventory.
     */
    @Override
    public void doAction() {
        for (Item item : this.itemsToRemove.keySet()) {
            int toRemove = this.itemsToRemove.get(item);

            if (game.player.backpack.containsKey(item)) {
                int currentQuantity = game.player.backpack.get(item);

                if (currentQuantity > toRemove) {
                    int newQuantity = currentQuantity - toRemove;
                    game.player.backpack.put(item, newQuantity);
                } else {
                    game.player.backpack.remove(item);
                }
            }
        }
    }
}
