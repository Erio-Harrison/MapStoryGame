package GameEngine;

/**
 * Author: Weiyuan
 * The {@code Heal} class provides functionality to increase the player's HP.
 * This action, when executed, will heal the player by a specified amount, without exceeding
 * the player's MaxHP.
 *
 * <p>Usage example:
 * <pre>
 *    Action healAction = new Heal(gameInstance, 50);
 *    healAction.doAction();  // Heals the player by 50 HP.
 * </pre>
 * </p>
 */
public class Heal extends Action {
    /**
     * The amount by which the player's HP should be increased.
     */
    private int heal;

    /**
     * Constructs a new {@code Heal} action.
     *
     * @param game the current game instance
     * @param heal the amount to heal the player
     */
    public Heal(Game game, int heal) {
        super(game);
        this.heal = heal;
    }

    /**
     * Executes the healing action.
     * This increases the player's HP by the specified heal amount without exceeding the player's MaxHP.
     */
    @Override
    public void doAction() {
        Player player = game.getPlayer();
        if (player.HP + heal >= player.MaxHP) {
            player.HP = player.MaxHP;
        } else {
            player.HP += heal;
        }
    }
}
