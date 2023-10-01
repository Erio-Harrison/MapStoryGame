package GameEngine;

import java.util.Map;

/**
 * Author: Weiyuan
 * Represents an action that adds an item or NPC to a specific game area.
 * This class allows dynamic changes to the game world, such as placing items or introducing characters.
 * <p>Usage example:
 * <pre>
 *    Action addToAreaAction = new AddToArea(gameInstance, swordItem, castleArea);
 *    addToAreaAction.doAction();  // Places a sword in the castle area.
 * </pre>
 * </p>
 */
public class AddToArea extends Action {
    private Map<Item, Integer> itemsToAdd;
    private Area targetArea;

    public AddToArea(Game game, Map<Item, Integer> itemToAdd, Area targetArea) {
        super(game);
        this.itemsToAdd = itemToAdd;
        this.targetArea = targetArea;
    }

    /**
     * Executes the action, adding the specified item or NPC to the target game area.
     */
    @Override
    public void doAction() {
        // add items to area
        for (Item item : this.itemsToAdd.keySet()) {
            int toAdd = this.itemsToAdd.get(item);

            if (targetArea.Items.containsKey(item)) {
                int currentQuantity = targetArea.Items.get(item);
                targetArea.Items.put(item, currentQuantity + toAdd);
            } else {
                targetArea.Items.put(item, toAdd);
            }
        }

    }
}
