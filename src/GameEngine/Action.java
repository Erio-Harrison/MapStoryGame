package GameEngine;
import java.lang.reflect.Array;
import java.util.*;

/**
 * The Action class provides the basic building blocks of game functionality.
 * Actions can be chained together in various ways to implement rich interactions.
 * Actions are specified in a JSON format.
 */
public abstract class Action {
    /**
     * All actions need to have access to the currently running game.
     * This is many actions modify game state (e.g adding health to player)
     */
    public Game game;

    /**
     * Create a new Action for a game.
     * @param game currently running game
     */
    public Action(Game game) {
        this.game = game;
    }

    public abstract void doAction();
}

/**
 * User choice
 */
class Choice extends Action {
    Map<String, Action> choices;

    public Choice(Game game, Map<String, Action> choices) {
        super(game);
        this.choices = choices;
    }

    @Override
    public void doAction() {
        // [1] tell him to get lost
        // Enter choice, or b for backpack: b
        int count = 0;

        ArrayList<String> choice_strings = new ArrayList<>(this.choices.keySet());
        for (String s : choice_strings) {
            count++;
            System.out.println("[" + count + "] " + s);
        }
        System.out.print("Select choice (enter a number between 1 and " + count + "): ");
        String choice = this.game.scanner.nextLine();
        int n_choice = Integer.parseInt(choice) - 1;

        // do the corresponding choice
        this.choices.get(choice_strings.get(n_choice)).doAction();
    }
}

/**
 * List of actions
 */
class ActionList extends Action {
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

/**
 * List of actions
 */
class Win extends Action {
    public Win(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
        System.out.println("you win!");
        System.exit(0);
    }
}

/**
 * Print to screen
 */
class Say extends Action {
    String to_print;

    public Say(Game game, String to_print) {
        super(game);
        this.to_print = to_print;
    }

    @Override
    public void doAction() {
        System.out.println(to_print);
    }
}

/**
 * Fight character
 */
class Fight extends Action {
    public Fight(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
    }
}

/**
 * Add item to player backpack
 */
class AddToBackpack extends Action {
    public AddToBackpack(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
    }
}

/**
 * Remove item from player backpack
 */
class RemoveFromBackpack extends Action {
    public RemoveFromBackpack(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
    }
}

/**
 * Add item to area
 */
class AddToArea extends Action {
    public AddToArea(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
    }
}

/**
 * Remove item from area
 */
class RemoveFromArea extends Action {
    public RemoveFromArea(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
    }
}

/**
 * Change area player is in
 */
class ChangeArea extends Action {
    Area next_area;

    public ChangeArea(Game game, Area next_area) {
        super(game);
        this.next_area = next_area;
    }

    @Override
    public void doAction() {
        this.game.currentArea = next_area;
    }
}

/**
 * Heal player
 */
class Heal extends Action {
    public Heal(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
    }
}

/**
 * Deal damage to player
 */
class Hurt extends Action {
    public Hurt(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
    }
}

/**
 * Do action based on requirement
 */
class Requirement extends Action {
    public Requirement(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
    }
}

/**
 * Implement type of requirements
 */ 
abstract class RequirementType {
    /**
     * Requirements need to check game state to see if requirement satisfied
     */
    Game game;

    /**
     * Check if requirement satisfied
     */
    abstract Boolean checkRequirement();

}

/**
 * Check if item in backpack
 */ 
class ItemInBackpackCheck extends RequirementType {
    @Override
    Boolean checkRequirement() {
        return null;
    }
}
