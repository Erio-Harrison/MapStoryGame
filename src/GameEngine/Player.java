package GameEngine;
import java.util.*;  

/**
 * Represents the player of the game
 */
public class Player extends Character {
    public Map<Item, Integer> backpack;


    public Player(int HP, int MaxHP, Map<Item, Integer> backpack) {
        super(HP, MaxHP);
        this.backpack = backpack;
    }

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
            System.out.println("[" + i + "] " + item.ID + " (" + item.description + ") x" + backpack.get(item));
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Select item number to inspect, or type 'exit' to exit: ");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input.trim())) {
                break;
            }
            try {
                int selectedIndex = Integer.parseInt(input);
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
    public void interactWithNPC(NPC npc) {
        npc.interact();
    }
}
