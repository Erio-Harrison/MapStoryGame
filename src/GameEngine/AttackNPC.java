package GameEngine;

/**
 * The AttackNPC class extends the Action class and represents an action that
 * models an attack on an NPC within the game. This class is designed to facilitate
 * the inflicting of damage to NPCs as a result of player actions or game events.
 */
public class AttackNPC extends Action {
    NPC npc;
    int damage;
    /**
     * Constructs a new AttackNPC action instance associated with the specified game,
     * targeting a specific NPC and inflicting a specified amount of damage.
     *
     * @param game   The Game instance to which this action belongs.
     * @param npc    The NPC instance representing the target of the attack.
     * @param damage The amount of damage to be inflicted upon the targeted NPC.
     */
    public AttackNPC(Game game, NPC npc, int damage) {
        super(game);
        this.npc = npc;
        this.damage = damage;
    }
    /**
     * Executes the AttackNPC action. It calls the attack_npc method on the targeted
     * NPC instance, passing in the damage value, to model the infliction of damage.
     */
    @Override
    public void doAction() {
        npc.attack_npc(this.damage);
    }
}
