package GameEngine;
import java.util.*;  

/**
 * Represents the player of the game
 */
public class Player extends Character {
    public Map<Item, Integer> backpack;

    public Player(int HP, int MaxHP, Map<Item, Integer> backpack) {
        super(HP, MaxHP);
        this.backpack = backpack;
    }

    public void inspectBackpack() {
        // [0] bandaid
        // [1] toothpick
        // Select item to inspect, or nothing to exit: 0
    }
}
