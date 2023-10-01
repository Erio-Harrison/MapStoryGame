package GameEngine;

import java.util.Map;

/**
 * ItemInAreaCheck is a class that extends RequirementChecker
 * and is used to verify if specified items are present in a given area in the required quantities.
 * It is utilized in the context of a game to check whether the required conditions,
 * based on the items in an area, are met.
 */
public class ItemInAreaCheck extends RequirementChecker {

    // The map representing the items to check along with their required quantities.
    Map<Item, Integer> items_to_check;
    Area area;

    /**
     * Initializes a new instance of `ItemInAreaCheck`.
     *
     * @param game             The game instance associated with this checker.
     * @param items_to_check   The map of items along with their quantities to be checked in the area.
     * @param area_to_check_in The area in which the items should be checked.
     */
    public ItemInAreaCheck(Game game, Map<Item, Integer> items_to_check, Area area_to_check_in) {
        this.items_to_check = items_to_check;
        this.area = area_to_check_in;
        this.game = game;
    }

    /**
     * Verifies if all specified items are present in the given area in the required quantities.
     *
     * @return true if all items in `items_to_check` are present in `area` in the required quantities, otherwise false.
     */
    @Override
    Boolean checkRequirement() {
        for (Item item : this.items_to_check.keySet()) {
            if (!this.area.Items.containsKey(item)) {
                return false;
            }
            if (this.area.Items.get(item) < this.items_to_check.get(item)) {
                return false;
            }
        }
        return true;
    }
}
