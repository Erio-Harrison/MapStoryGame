package GameEngine;

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

// NOTE
// These subclasses are just placeholders to flesh out
// They all need more parameters in their constructor and other stuff

/**
 * User choice
 */
class Choice extends Action {
    public Choice(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
    }
}

/**
 * List of actions
 */
class ActionList extends Action {
    public ActionList(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
    }
}

/**
 * Print to screen
 */
class Say extends Action {
    public Say(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
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
    public ChangeArea(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
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
