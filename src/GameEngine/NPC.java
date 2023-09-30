package GameEngine;
import java.util.*;  

/**
 * The NPC class represents non-playable characters in the game
 */
public class NPC extends Character {
    /**
     * NPC name
     */
    public String name;

    /**
     * NPC initial interaction
     */
    public Action initialInteraction;

    /**
     * NPC repeat interaction
     */
    public Action repeatInteraction;

    /**
     * If initial interaction has been completed
     */
    public Boolean hasSpokenTo;

    /**
     * Interact with NPC
     */
    public void interact() {
        if (hasSpokenTo) {
            repeatInteraction.doAction();
        } else {
            initialInteraction.doAction();
            hasSpokenTo = true;
        }
    }

    public NPC(int HP, int MaxHP, String name, Action initialInteraction, Action repeatInteraction) {
        super(HP, MaxHP);
        this.hasSpokenTo = false;
        this.name = name;
        this.initialInteraction = initialInteraction;
        this.repeatInteraction = repeatInteraction;
    }
}
