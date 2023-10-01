package GameEngine;

import java.util.ArrayList;

/**
 * The DoNothing class is a specialization of the Action class within the game engine.
 * It represents an action that, when executed, performs no operation (no-op),
 * meaning it doesn't affect the game state or invoke any behavior.
 * This class can be used for scenarios where an action is required
 * by the system or interface, but no behavior is desired.
 */
public class DoNothing extends Action {
    // It seems this ArrayList is not used within the class;
    // consider whether it's needed or if it should be removed.
    ArrayList<Action> action_list;
    /**
     * Constructs a new DoNothing action instance associated with the specified game.
     *
     * @param game The Game instance to which this action belongs.
     */
    public DoNothing(Game game) {
        super(game);
    }
    /**
     * Executes the DoNothing action. As the name implies, this method performs no operation
     * and does not affect the game state or invoke any behavior when executed.
     */
    @Override
    public void doAction() {
    }
}
