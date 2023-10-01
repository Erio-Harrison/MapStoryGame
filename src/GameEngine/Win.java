package GameEngine;

/**
 * Win
 */
public class Win extends Action {
    public Win(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
        System.out.println("You win! Congratulations!");
        System.exit(0);
    }
}
