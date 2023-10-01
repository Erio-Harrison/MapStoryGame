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
    /**
     * Constructs a new AddToArea instance associated with the specified game and
     * initializes it with items to add and the target area.
     *
     * @param game        The Game instance to which this action belongs.
     * @param itemToAdd  A Map representing the items to be added to the target area,
     *                    with each item associated with a quantity.
     * @param targetArea  The Area instance representing the area where the items will be added.
     */
    public AddToArea(Game game, Map<Item, Integer> itemToAdd, Area targetArea) {
        super(game);
        this.itemsToAdd = itemToAdd;
        this.targetArea = targetArea;
    }

    /**
     * Executes the AddToArea action, adding the specified items to the target game area.
     * It iterates through the set of items and either increases the quantity of existing items
     * in the area or introduces new items to it.
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
