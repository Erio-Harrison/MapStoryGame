package GameEngine;

/**
 * Attack NPC
 */
public class AttackNPC extends Action {
    NPC npc;
    int damage;

    public AttackNPC(Game game, NPC npc, int damage) {
        super(game);
        this.npc = npc;
        this.damage = damage;
    }

    @Override
    public void doAction() {
        npc.attack_npc(this.damage);
    }
}
