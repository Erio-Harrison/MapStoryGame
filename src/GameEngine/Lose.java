package GameEngine;

/**
 * The Lose class extends the Action class and represents an action that signifies
 * the end of the game in a losing condition. This class is designed to convey
 * a losing message to the player and to terminate the game application immediately,
 * making it suitable for scenarios where the player’s defeat is conclusive.
 */
public class Lose extends Action {
    /**
     * Constructs a new Lose action instance associated with the specified game.
     *
     * @param game The Game instance to which this action belongs.
     */
    public Lose(Game game) {
        super(game);
    }
    /**
     * Executes the Lose action. It displays a losing message to the player,
     * indicating the end of the game in a losing condition, and then terminates
     * the game application immediately.
     */
    @Override
    public void doAction() {
        System.out.println("You lost the game :(");
        System.exit(0);
    }
}
