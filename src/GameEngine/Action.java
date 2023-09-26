package GameEngine;

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
    private final String message;
    public Say(Game game, String message) {
        super(game);
        this.message = message;
    }

    @Override
    public void doAction() {
        // Display the message to the player
        System.out.println(message);
    }
}

/**
 * Fight character
 */
class Fight extends Action {
    private final int damage;
    private NPC npc;
    public Fight(Game game, NPC npc, int damage) {
        super(game);
        this.npc = npc;
        this.damage = damage;
    }

    @Override
    public void doAction() {
        npc.HP -= damage;
        if (npc.HP < 0) {
            npc.HP = 0;
            // Handle player's death, e.g., end the game or show a specific message.
        }
    }
}

/**
 * Add item to player backpack
 */
class AddToBackpack extends Action {
    private Item item;
    public AddToBackpack(Game game, Item item) {
        super(game);
        this.item = item;
    }

    @Override
    public void doAction() {
        Player player = game.getPlayer();
        // Check if the player's backpack already contains the item
        if (player.backpack.containsKey(item)) {
            // If it does, increase the quantity of that item by one
            int currentQuantity = player.backpack.get(item);
            player.backpack.put(item, currentQuantity + 1);
        } else {
            // If not, add the item to the backpack with a quantity of 1
            player.backpack.put(item, 1);
        }
    }
}

/**
 * Remove item from player backpack
 */
class RemoveFromBackpack extends Action {
    private final Item item;
    private final int quantityToRemove;

    public RemoveFromBackpack(Game game, Item item, int quantityToRemove) {
        super(game);
        this.item = item;
        this.quantityToRemove = quantityToRemove;
    }

    @Override
    public void doAction() {
        Player player = game.getPlayer();
        // Check if the player's backpack contains the item
        if (player.backpack.containsKey(item)) {
            int currentQuantity = player.backpack.get(item);
            // If the current quantity is greater than the quantity to remove,
            // just subtract the required quantity
            if (currentQuantity > quantityToRemove) {
                player.backpack.put(item, currentQuantity - quantityToRemove);
            } else {
                // If not, remove the item entirely from the backpack
                player.backpack.remove(item);
            }
        }
    }
}

/**
 * Add item to area
 */
class AddToArea extends Action {
    private Item itemToAdd;
    private NPC npcToAdd;
    private Area targetArea;

    public AddToArea(Game game, Item itemToAdd, Area targetArea) {
        super(game);
        this.itemToAdd = itemToAdd;
        this.targetArea = targetArea;
    }

    public AddToArea(Game game, NPC npcToAdd, Area targetArea) {
        super(game);
        this.npcToAdd = npcToAdd;
        this.targetArea = targetArea;
    }

    @Override
    public void doAction() {
        // add items to area
        if (itemToAdd != null) {
            if (targetArea.Items == null) {
                targetArea.Items = new ArrayList<Item>();
            }
            targetArea.Items.add(itemToAdd);
        }

        // add NPCs to area
        if (npcToAdd != null) {
            if (targetArea.NPCs == null) {
                targetArea.NPCs = new ArrayList<NPC>();
            }
            targetArea.NPCs.add(npcToAdd);
        }
    }
}


/**
 * Remove item from area
 */
class RemoveFromArea extends Action {
    private Area targetArea;
    private Item itemToRemove;
    private NPC npcToRemove;

    // Constructor for removing an item
    public RemoveFromArea(Game game, Area targetArea, Item itemToRemove) {
        super(game);
        this.targetArea = targetArea;
        this.itemToRemove = itemToRemove;
    }

    // Constructor for removing an NPC
    public RemoveFromArea(Game game, Area targetArea, NPC npcToRemove) {
        super(game);
        this.targetArea = targetArea;
        this.npcToRemove = npcToRemove;
    }

    @Override
    public void doAction() {
        if (itemToRemove != null) {
            targetArea.Items.remove(itemToRemove);
        } else if (npcToRemove != null) {
            targetArea.NPCs.remove(npcToRemove);
        }
    }
}

/**
 * Change area player is in
 */
class ChangeArea extends Action {
    private Area currentArea;
    private Area targetArea;

    public ChangeArea(Game game, Area currentArea, Area targetArea) {
        super(game);
        this.currentArea = currentArea;
        this.targetArea = targetArea;
    }

    @Override
    public void doAction() {
        if (currentArea != null && targetArea != null) {
            game.setCurrentArea(targetArea);
        }
    }
}


/**
 * Heal player
 */
class Heal extends Action {
    private final int heal;
    public Heal(Game game, int heal) {
        super(game);
        this.heal = heal;
    }

    @Override
    public void doAction() {
        Player player = game.getPlayer();
        if (player.HP + heal >= player.MaxHP) {
            player.HP = player.MaxHP;
            // Handle player's death, e.g., end the game or show a specific message.
        } else {
            player.HP += heal;
        }
    }
}

/**
 * Deal damage to player
 */
class Hurt extends Action {
    private final int damage;
    public Hurt(Game game, int damage) {
        super(game);
        this.damage = damage;
    }

    @Override
    public void doAction() {
        Player player = game.getPlayer();
        player.HP -= damage;
        if (player.HP < 0) {
            player.HP = 0;
            // Handle player's death, e.g., end the game or show a specific message.
        }
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
