package GameEngine;

/**
 * The NPCAliveCheck class extends the RequirementChecker class, representing a condition checker
 * that verifies whether a specific Non-Player Character (NPC) in the game is alive or not. This class
 * can be utilized wherever a condition based on an NPC's alive status needs to be evaluated within the game.
 */
public class NPCAliveCheck extends RequirementChecker {
    NPC npc;
    /**
     * Constructs a new NPCAliveCheck instance associated with the specified game and NPC.
     *
     * @param game The Game instance to which this checker belongs.
     * @param npc  The NPC instance whose alive status is to be checked.
     */
    public NPCAliveCheck(Game game, NPC npc) {
        this.game = game;
        this.npc = npc;
    }
    /**
     * Checks the requirement by evaluating the alive status of the associated NPC.
     *
     * @return Returns true if the NPC is alive; otherwise, returns false.
     */
    @Override
    Boolean checkRequirement() {
        return this.npc.isAlive;
    }
}
