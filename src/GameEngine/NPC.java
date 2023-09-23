package GameEngine;

/**
 * The NPC class represents non-playable characters in the game
 */
public class NPC extends Character {
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
            hasSpokenTo = false;
        }
    }
}
