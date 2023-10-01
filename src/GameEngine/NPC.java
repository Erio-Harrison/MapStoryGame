package GameEngine;
import java.util.*;

/**
 * Represents a non-playable character (NPC) in the game.
 * The NPC class extends the Character class and includes functionality
 * specific to interactions within the game. NPCs have initial interactions,
 * repeat interactions, and a flag indicating whether the player has spoken
 * to them.
 */
public class NPC extends Character {
    /**
     * NPC name
     */
    public String name;

    /**
     * The action to be executed during the initial interaction with the NPC.
     */
    public Action initialInteraction;

    /**
     * The action to be executed during subsequent interactions with the NPC
     * after the initial interaction has occurred.
     */
    public Action repeatInteraction;

    /**
     * A flag indicating whether the player has had the initial interaction
     * with the NPC.
     */
    public Boolean hasSpokenTo;

    /**
     * Allows interaction with the NPC.
     * The method checks whether the player has spoken to the NPC before and
     * executes the appropriate interaction action accordingly.
     */
    public void interact() {
        if (hasSpokenTo) {
            repeatInteraction.doAction();
        } else {
            initialInteraction.doAction();
            hasSpokenTo = true;
        }
    }
    /**
     * Constructs a new NPC instance with the specified HP, MaxHP, name,
     * initial interaction, and repeat interaction.
     *
     * @param HP the current hit points (health) of the NPC
     * @param MaxHP the maximum hit points (health) the NPC can have
     * @param name the name of the NPC
     * @param initialInteraction the action for the initial interaction with the NPC
     * @param repeatInteraction the action for subsequent interactions with the NPC
     */
    public NPC(int HP, int MaxHP, String name, Action initialInteraction, Action repeatInteraction) {
        super(HP, MaxHP);
        this.hasSpokenTo = false;
        this.name = name;
        this.initialInteraction = initialInteraction;
        this.repeatInteraction = repeatInteraction;
    }
}
