package GameEngine;
import java.util.*;

import java.util.ArrayList;

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
 * nop
 */
class DoNothing extends Action {
    ArrayList<Action> action_list;

    public DoNothing(Game game) {
        super(game);
    }

    @Override
    public void doAction() {
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
class Say extends Action {
    private String message;

    /**
     * Constructs a new {@code Say} action.
     *
     * @param game the current game instance
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
        System.out.println(message);
    }
}

/**
 * Author: Weiyuan
 * Represents a combat action that damages a non-player character (NPC) in the game.
 * This class allows players to engage in combat by inflicting damage to an NPC.
 * <p>Usage example:
 * <pre>
 *    Action fightAction = new Fight(gameInstance, orcNPC, 50);
 *    fightAction.doAction();  // Deals 50 damage to the orc NPC.
 * </pre>
 * </p>
 */
class Fight extends Action {
    private int damage;
    private NPC npc;

    /**
     * Constructs a new {@code Fight} action.
     *
     * @param game the current game instance
     * @param npc the target NPC that will receive damage
     * @param damage the amount of damage to inflict on the NPC
     */
    public Fight(Game game, NPC npc, int damage) {
        super(game);
        this.npc = npc;
        this.damage = damage;
    }

    /**
     * Executes the combat action, reducing the NPC's HP by the specified damage amount.
     * If the NPC's HP drops to zero or below, the NPC is considered defeated.
     */
    @Override
    public void doAction() {
        npc.HP -= damage;
        if (npc.HP < 0) {
            npc.HP = 0;
            // Handle NPC's defeat, e.g., drop loot, trigger event, etc.
        }
    }
}

/**
 * Author: Weiyuan
 * Represents an action that adds an item to a player's inventory or "backpack".
 * This class offers a mechanism to increase the player's inventory.
 * <p>Usage example:
 * <pre>
 *    Action addToBackpackAction = new AddToBackpack(gameInstance, potionItem);
 *    addToBackpackAction.doAction();  // Adds a potion to the player's inventory.
 * </pre>
 * </p>
 */
class AddToBackpack extends Action {
    private Map<Item, Integer> itemsToAdd;

    /**
     * Constructs a new {@code AddToBackpack} action.
     *
     * @param game the current game instance
     * @param item the item to be added to the player's inventory
     */
    public AddToBackpack(Game game, Map<Item, Integer> itemsToAdd) {
        super(game);
        this.itemsToAdd = itemsToAdd;
    }

    /**
     * Executes the action, adding the specified item to the player's inventory.
     * If the player already has the item, its quantity is increased.
     */
    @Override
    public void doAction() {
        // add items to area
        for (Item item : this.itemsToAdd.keySet()) {
            int toAdd = this.itemsToAdd.get(item);

            if (game.player.backpack.containsKey(item)) {
                int currentQuantity = game.player.backpack.get(item);
                game.player.backpack.put(item, currentQuantity + toAdd);
            } else {
                game.player.backpack.put(item, toAdd);
            }
        }
    }
}

/**
 * Author: Weiyuan
 * Represents an action that removes an item from a player's inventory or "backpack".
 * This class facilitates the removal or usage of items from the player's inventory.
 * <p>Usage example:
 * <pre>
 *    Action removeFromBackpackAction = new RemoveFromBackpack(gameInstance, potionItem, 1);
 *    removeFromBackpackAction.doAction();  // Removes one potion from the player's inventory.
 * </pre>
 * </p>
 */
class RemoveFromBackpack extends Action {
    private Map<Item, Integer> itemsToRemove;
    private int quantityToRemove;

    /**
     * Constructs a new {@code RemoveFromBackpack} action.
     *
     * @param game the current game instance
     * @param item the item to be removed from the player's inventory
     * @param quantityToRemove the quantity of the item to remove
     */
    public RemoveFromBackpack(Game game, Map<Item, Integer> itemsToRemove) {
        super(game);
        this.itemsToRemove = itemsToRemove;
    }

    /**
     * Executes the action, removing the specified quantity of the item from the player's inventory.
     */
    @Override
    public void doAction() {
        for (Item item : this.itemsToRemove.keySet()) {
            int toRemove = this.itemsToRemove.get(item);

            if (game.player.backpack.containsKey(item)) {
                int currentQuantity = game.player.backpack.get(item);

                if (currentQuantity > toRemove) {
                    int newQuantity = currentQuantity - toRemove;
                    game.player.backpack.put(item, newQuantity);
                } else {
                    game.player.backpack.remove(item);
                }
            }
        }
    }
}

/**
 * Author: Weiyuan
 * Represents an action that adds an item or NPC to a specific game area.
 * This class allows dynamic changes to the game world, such as placing items or introducing characters.
 * <p>Usage example:
 * <pre>
 *    Action addToAreaAction = new AddToArea(gameInstance, swordItem, castleArea);
 *    addToAreaAction.doAction();  // Places a sword in the castle area.
 * </pre>
 * </p>
 */
class AddToArea extends Action {
    private Map<Item, Integer> itemsToAdd;
    private Area targetArea;

    public AddToArea(Game game, Map<Item, Integer> itemToAdd, Area targetArea) {
        super(game);
        this.itemsToAdd = itemToAdd;
        this.targetArea = targetArea;
    }

    /**
     * Executes the action, adding the specified item or NPC to the target game area.
     */
    @Override
    public void doAction() {
        // add items to area
        for (Item item : this.itemsToAdd.keySet()) {
            int toAdd = this.itemsToAdd.get(item);

            if (targetArea.Items.containsKey(item)) {
                int currentQuantity = targetArea.Items.get(item);
                targetArea.Items.put(item, currentQuantity + toAdd);
            } else {
                targetArea.Items.put(item, toAdd);
            }
        }

    }
}

/**
 * Author: Weiyuan
 * Represents an action that removes an item or NPC from a specific game area.
 * This class provides mechanisms for dynamic events like item pickups or NPC departures.
 * <p>Usage example:
 * <pre>
 *    Action removeFromAreaAction = new RemoveFromArea(gameInstance, castleArea, orcNPC);
 *    removeFromAreaAction.doAction();  // Removes the orc NPC from the castle area.
 * </pre>
 * </p>
 */
class RemoveFromArea extends Action {
    private Area targetArea;
    private Map<Item, Integer> itemsToRemove;

    // Constructor for removing an item
    public RemoveFromArea(Game game, Map<Item, Integer> itemsToRemove, Area targetArea) {
        super(game);
        this.targetArea = targetArea;
        this.itemsToRemove = itemsToRemove;
    }

    /**
     * Executes the action, removing the specified item or NPC from the target game area.
     */
    @Override
    public void doAction() {
        for (Item item : this.itemsToRemove.keySet()) {
            int toRemove = this.itemsToRemove.get(item);

            if (targetArea.Items.containsKey(item)) {
                int currentQuantity = targetArea.Items.get(item);

                if (currentQuantity > toRemove) {
                    int newQuantity = currentQuantity - toRemove;
                    targetArea.Items.put(item, newQuantity);
                } else {
                    targetArea.Items.remove(item);
                }
            }
        }
    }
}

/**
 * Author: Weiyuan
 * Represents an action that changes the player's current location within the game world.
 * This class manages player movement between different areas or scenes.
 * <p>Usage example:
 * <pre>
 *    Action changeAreaAction = new ChangeArea(gameInstance, forestArea, castleArea);
 *    changeAreaAction.doAction();  // Moves the player from the forest to the castle.
 * </pre>
 * </p>
 */
class ChangeArea extends Action {
    private Area targetArea;

    /**
     * Constructs a new {@code ChangeArea} action.
     *
     * @param game the current game instance
     * @param targetArea the area the player will move to
     */
    public ChangeArea(Game game, Area targetArea) {
        super(game);
        this.targetArea = targetArea;
    }

    /**
     * Executes the action, transitioning the player from their current area to the target area.
     */
    @Override
    public void doAction() {
        game.setCurrentArea(targetArea);
    }
}

/**
 * Author: Weiyuan
 * The {@code Heal} class provides functionality to increase the player's HP.
 * This action, when executed, will heal the player by a specified amount, without exceeding
 * the player's MaxHP.
 *
 * <p>Usage example:
 * <pre>
 *    Action healAction = new Heal(gameInstance, 50);
 *    healAction.doAction();  // Heals the player by 50 HP.
 * </pre>
 * </p>
 */
class Heal extends Action {
    /**
     * The amount by which the player's HP should be increased.
     */
    private int heal;

    /**
     * Constructs a new {@code Heal} action.
     *
     * @param game the current game instance
     * @param heal the amount to heal the player
     */
    public Heal(Game game, int heal) {
        super(game);
        this.heal = heal;
    }

    /**
     * Executes the healing action.
     * This increases the player's HP by the specified heal amount without exceeding the player's MaxHP.
     */
    @Override
    public void doAction() {
        Player player = game.getPlayer();
        if (player.HP + heal >= player.MaxHP) {
            player.HP = player.MaxHP;
        } else {
            player.HP += heal;
        }
    }
}

/**
 * Author: Weiyuan
 * The {@code Hurt} class provides functionality to decrease the player's HP.
 * This action, when executed, will damage the player by a specified amount.
 * If the damage exceeds the player's current HP, the HP is set to zero.
 *
 * <p>Usage example:
 * <pre>
 *    Action hurtAction = new Hurt(gameInstance, 50);
 *    hurtAction.doAction();  // Reduces the player's HP by 50.
 * </pre>
 * </p>
 */
class Hurt extends Action {
    /** The amount by which the player's HP should be decreased. */
    private int damage;

    /**
     * Constructs a new {@code Hurt} action.
     *
     * @param game the current game instance
     * @param damage the amount to reduce the player's HP
     */
    public Hurt(Game game, int damage) {
        super(game);
        this.damage = damage;
    }

    /**
     * Executes the damage action.
     * This reduces the player's HP by the specified damage amount, setting it to zero if the damage is more than the current HP.
     */
    @Override
    public void doAction() {
        Player player = game.getPlayer();
        player.HP -= damage;
        if (player.HP < 0) {
            player.HP = 0;
            System.out.println("You died :(");
            System.exit(0);
        }
    }
}


/**
 * Do action based on requirement
 */
class Requirement extends Action {
    RequirementChecker check;
    Action satisfied;
    Action not_satisfied;

    public Requirement(Game game, RequirementChecker check, Action satisfied, Action notSatisfied) {
        super(game);
        this.check = check;
        this.satisfied = satisfied;
        this.not_satisfied = notSatisfied;
    }

    @Override
    public void doAction() {
        if (this.check.checkRequirement()) {
            this.satisfied.doAction();
        } else {
            this.not_satisfied.doAction();
        }
    }
}

/**
 * Implement type of requirements
 */ 
abstract class RequirementChecker {
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
class ItemInBackpackCheck extends RequirementChecker {
    Map<Item, Integer> items_to_check;

    public ItemInBackpackCheck(Game game, Map<Item, Integer> items_to_check) {
        this.items_to_check= items_to_check;
        this.game = game;
    }

    @Override
    Boolean checkRequirement() {
        for (Item item : this.items_to_check.keySet()) {
            if (!game.player.backpack.containsKey(item)) {
                return false;
            } if (game.player.backpack.get(item) <= this.items_to_check.get(item)) {
                return false;
            }
        }
        return true;
    }
}
