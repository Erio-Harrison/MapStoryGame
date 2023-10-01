package GameEngine;

import java.util.ArrayList;

/**
 * List of actions
 */
public class ActionList extends Action {
    ArrayList<Action> action_list;

    public ActionList(Game game, ArrayList<Action> action_list) {
        super(game);
        this.action_list = action_list;
    }

    @Override
    public void doAction() {
        for (Action a : this.action_list) {
            a.doAction();
        }
    }
}
