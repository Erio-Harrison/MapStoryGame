package GameEngine;
import java.util.*;

/**
 * Represents a player in the game.
 * The Player class extends the Character class and includes functionality
 * specific to player interaction within the game, such as inspecting the backpack
 * and interacting with Non-Player Characters (NPCs).
 */
public class Player extends Character {
    /**
     * A map representing the player's backpack, where the keys are items
     * and the values are the quantity of each item.
     */
    public Map<Item, Integer> backpack;

    /**
     * Constructs a new Player instance with the specified HP, MaxHP, and backpack.
     *
     * @param HP the current hit points (health) of the player
     * @param MaxHP the maximum hit points (health) the player can have
     * @param backpack a map representing the initial items in the player's backpack
     */
    public Player(int HP, int MaxHP, Map<Item, Integer> backpack) {
        super(HP, MaxHP);
        this.backpack = backpack;
    }

    /**
     * Allows the player to inspect the contents of their backpack.
     * The player can interact with the items in the backpack or exit the inspection.
     * The item numbers are displayed starting from 1 for user-friendly interaction.
     */
    public void inspectBackpack() {
        if (backpack.isEmpty()) {
            System.out.println("Your backpack is empty.");
            return;
        }

        System.out.println("Contents of your backpack:");
        Set<Item> items = backpack.keySet();
        Item[] itemList = items.toArray(new Item[0]);
        for (int i = 0; i < itemList.length; i++) {
            Item item = itemList[i];
            System.out.println("[" + (i + 1) + "] " + item.ID + " (" + item.description + ") x" + backpack.get(item)); // Changed i to i + 1
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Select item number to inspect, or type 'exit' to exit: ");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input.trim())) {
                break;
            }
            try {
                int selectedIndex = Integer.parseInt(input) - 1; // Adjust selectedIndex to match the array index
                if (selectedIndex < 0 || selectedIndex >= itemList.length) {
                    System.out.println("Invalid choice. Please select a valid item number or type 'exit' to exit.");
                } else {
                    Item selectedItem = itemList[selectedIndex];
                    System.out.println("You have selected: " + selectedItem.ID);
                    System.out.println("Description: " + selectedItem.description);
                    System.out.println("Do you want to interact with this item? (yes/no)");
                    String interactResponse = scanner.nextLine();
                    if ("yes".equalsIgnoreCase(interactResponse.trim())) {
                        selectedItem.interact.doAction(); // Interacting with the selected item
                    }
                    break; // Breaking out of the loop after interaction
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the item or type 'exit' to exit.");
            }
        }
    }

    /**
     * Allows the player to interact with a given NPC.
     * The interaction is defined by the NPC's interact method.
     *
     * @param npc the NPC with whom the player is to interact
     */
    public void interactWithNPC(NPC npc) {
        npc.interact();
    }
}
