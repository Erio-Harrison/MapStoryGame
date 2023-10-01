package GameEngine;

/**
 * Interact with an NPC
 */
public class NPCInteract extends Action {
    NPC to_interact;

    public NPCInteract(Game game, NPC npc) {
        super(game);
        to_interact = npc;
    }

    @Override
    public void doAction() {
        this.to_interact.interact();
    }
}
