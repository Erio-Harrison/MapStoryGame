package GameEngine;

/**
 * Author: Weiyuan
 * Represents a combat action that damages a non-player character (NPC) in the game.
 * This class allows players to engage in combat by inflicting damage to an NPC.
 * <p>Usage example:
 * <pre>
 *    Action fightAction = new Fight(gameInstance, orcNPC, 50);
 *    fightAction.doAction();  // Deals 50 damage to the orc NPC.
 * </pre>
 * </p>
 */
public class Fight extends Action {
    private int damage;
    private NPC npc;

    /**
     * Constructs a new {@code Fight} action.
     *
     * @param game   the current game instance
     * @param npc    the target NPC that will receive damage
     * @param damage the amount of damage to inflict on the NPC
     */
    public Fight(Game game, NPC npc, int damage) {
        super(game);
        this.npc = npc;
        this.damage = damage;
    }

    /**
     * Executes the combat action, reducing the NPC's HP by the specified damage amount.
     * If the NPC's HP drops to zero or below, the NPC is considered defeated.
     */
    @Override
    public void doAction() {
        npc.HP -= damage;
        if (npc.HP < 0) {
            npc.HP = 0;
            // Handle NPC's defeat, e.g., drop loot, trigger event, etc.
        }
    }
}
