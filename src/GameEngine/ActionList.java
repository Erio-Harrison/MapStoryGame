package GameEngine;

import java.util.ArrayList;

/**
 * The ActionList class extends the Action class, serving as a container for a list
 * of actions. It is designed to facilitate the execution of multiple actions in sequence.
 * This class is ideal for scenarios where multiple actions should be triggered
 * by a single game event or user interaction.
 */
public class ActionList extends Action {
    ArrayList<Action> action_list;
    /**
     * Constructs a new ActionList instance associated with the specified game
     * and initializes it with a list of actions.
     *
     * @param game         The Game instance to which this action belongs.
     * @param action_list  An ArrayList containing Action instances that are to be executed in sequence when this ActionList is triggered.
     */
    public ActionList(Game game, ArrayList<Action> action_list) {
        super(game);
        this.action_list = action_list;
    }
    /**
     * Executes the ActionList action. It traverses through each Action instance
     * stored in the action list and sequentially executes them.
     */
    @Override
    public void doAction() {
        for (Action a : this.action_list) {
            a.doAction();
        }
    }
}
