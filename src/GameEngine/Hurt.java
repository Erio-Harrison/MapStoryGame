package GameEngine;

/**
 * Author: Weiyuan
 * The {@code Hurt} class provides functionality to decrease the player's HP.
 * This action, when executed, will damage the player by a specified amount.
 * If the damage exceeds the player's current HP, the HP is set to zero.
 *
 * <p>Usage example:
 * <pre>
 *    Action hurtAction = new Hurt(gameInstance, 50);
 *    hurtAction.doAction();  // Reduces the player's HP by 50.
 * </pre>
 * </p>
 */
public class Hurt extends Action {
    /**
     * The amount by which the player's HP should be decreased.
     */
    private int damage;

    /**
     * Constructs a new {@code Hurt} action.
     *
     * @param game   the current game instance
     * @param damage the amount to reduce the player's HP
     */
    public Hurt(Game game, int damage) {
        super(game);
        this.damage = damage;
    }

    /**
     * Executes the damage action.
     * This reduces the player's HP by the specified damage amount, setting it to zero if the damage is more than the current HP.
     */
    @Override
    public void doAction() {
        Player player = game.getPlayer();
        player.HP -= damage;
        if (player.HP <= 0) {
            player.HP = 0;
            System.out.println(Utils.surroundWithLines("You died!"));
            this.game.player.printPlayerInfo();
            this.game.player.on_death.doAction();
        }
    }
}
