package GameEngine;

import java.util.Map;

/**
 * Author: Weiyuan
 * Represents an action that removes an item or NPC from a specific game area.
 * This class provides mechanisms for dynamic events like item pickups or NPC departures.
 * <p>Usage example:
 * <pre>
 *    Action removeFromAreaAction = new RemoveFromArea(gameInstance, castleArea, orcNPC);
 *    removeFromAreaAction.doAction();  // Removes the orc NPC from the castle area.
 * </pre>
 * </p>
 */
public class RemoveFromArea extends Action {
    private Area targetArea;
    private Map<Item, Integer> itemsToRemove;

    /**
     * Constructs a new RemoveFromArea action instance associated with the specified game,
     * target area, and items to remove.
     *
     * @param game          The Game instance to which this action belongs.
     * @param itemsToRemove A map representing the items to be removed and their respective quantities.
     * @param targetArea    The Area instance representing the target area from which items are to be removed.
     */
    public RemoveFromArea(Game game, Map<Item, Integer> itemsToRemove, Area targetArea) {
        super(game);
        this.targetArea = targetArea;
        this.itemsToRemove = itemsToRemove;
    }

    /**
     * Executes the action of removing the specified items from the target area.
     * It iterates over the set of items to remove and adjusts the quantity or
     * removes the item from the area's items as necessary.
     */
    @Override
    public void doAction() {
        for (Item item : this.itemsToRemove.keySet()) {
            int toRemove = this.itemsToRemove.get(item);

            if (targetArea.Items.containsKey(item)) {
                int currentQuantity = targetArea.Items.get(item);

                if (currentQuantity > toRemove) {
                    int newQuantity = currentQuantity - toRemove;
                    targetArea.Items.put(item, newQuantity);
                } else {
                    targetArea.Items.remove(item);
                }
            }
        }
    }
}
