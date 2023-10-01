package GameEngine;

/**
 * Lose
 */
public class Lose extends Action {
    public Lose(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
        System.out.println("You lost the game :(");
        System.exit(0);
    }
}
