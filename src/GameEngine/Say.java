package GameEngine;

/**
 * Author: Weiyuan
 * Represents an action where a message is displayed to the player.
 * This class provides a means to communicate specific information or narrative content to the player.
 * <p>Usage example:
 * <pre>
 *    Action sayAction = new Say(gameInstance, "Hello, Player!");
 *    sayAction.doAction();  // Displays "Hello, Player!" to the player.
 * </pre>
 * </p>
 */
public class Say extends Action {
    private String message;

    /**
     * Constructs a new {@code Say} action.
     *
     * @param game    the current game instance
     * @param message the message to be displayed to the player
     */
    public Say(Game game, String message) {
        super(game);
        this.message = message;
    }


    /**
     * Executes the action, displaying the specified message to the player.
     */
    @Override
    public void doAction() {
        System.out.println(Utils.surroundWithLines(this.message));
    }
}
