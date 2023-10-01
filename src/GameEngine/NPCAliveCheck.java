package GameEngine;

/**
 * Check NPC alive
 */
public class NPCAliveCheck extends RequirementChecker {
    NPC npc;

    public NPCAliveCheck(Game game, NPC npc) {
        this.game = game;
        this.npc = npc;
    }

    @Override
    Boolean checkRequirement() {
        return this.npc.isAlive;
    }
}
