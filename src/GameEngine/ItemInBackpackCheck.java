package GameEngine;

import java.util.Map;

/**
 * `ItemInBackpackCheck` is a class extending `RequirementChecker`. It is employed to determine whether
 * specified items are present in a player's backpack in the requisite quantities. This class finds its application
 * primarily in gaming scenarios where there is a need to validate certain conditions based on the items
 * in a player’s backpack.
 */
public class ItemInBackpackCheck extends RequirementChecker {
    // The map representing the items along with their required quantities to check in the player's backpack.
    Map<Item, Integer> items_to_check;

    /**
     * Constructs a new instance of `ItemInBackpackCheck`.
     *
     * @param game           The game instance associated with this checker.
     * @param items_to_check The map of items along with their quantities to be checked in the player's backpack.
     */
    public ItemInBackpackCheck(Game game, Map<Item, Integer> items_to_check) {
        this.items_to_check = items_to_check;
        this.game = game;
    }

    /**
     * Validates whether all specified items are present in the player's backpack in the required quantities.
     *
     * @return true if all items in `items_to_check` are present in the player’s backpack in the required quantities, otherwise false.
     */
    @Override
    Boolean checkRequirement() {
        for (Item item : this.items_to_check.keySet()) {
            if (!game.player.backpack.containsKey(item)) {
                return false;
            }
            if (game.player.backpack.get(item) < this.items_to_check.get(item)) {
                return false;
            }
        }
        return true;
    }
}
