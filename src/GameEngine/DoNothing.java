package GameEngine;

import java.util.ArrayList;

/**
 * nop
 */
public class DoNothing extends Action {
    ArrayList<Action> action_list;

    public DoNothing(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
    }
}
