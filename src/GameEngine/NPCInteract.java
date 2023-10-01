package GameEngine;

/**
 * The NPCInteract class extends the Action class and represents an action to interact with an NPC (Non-Player Character) within a game. This class overrides the doAction method to facilitate interactions with the specified NPC.
 */
public class NPCInteract extends Action {
    NPC to_interact;

    /**
     * Constructs a new NPCInteract instance associated with the specified game and NPC.
     *
     * @param game The Game instance to which this action belongs.
     * @param npc  The NPC instance with which interaction is to be performed.
     */
    public NPCInteract(Game game, NPC npc) {
        super(game);
        to_interact = npc;
    }
    /**
     * Executes the interaction with the specified NPC by calling the interact method on the NPC instance.
     */
    @Override
    public void doAction() {
        this.to_interact.interact();
    }
}
