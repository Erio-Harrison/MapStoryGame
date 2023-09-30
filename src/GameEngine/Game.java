package GameEngine;
import java.util.*;  

/**
 * The Game class holds the state of the currently running game.
 */
public class Game {
    /**
     * Area player is currently in
     */
    public Area currentArea;

    /**
     * All areas in game
     */
    public ArrayList<Area> areas;

    /**
     * All items in game
     */
    public ArrayList<Item> items;

    /**
     * Player information
     */
    public Player player;

    public void setCurrentArea(Area currentArea) {
        this.currentArea = currentArea;
    }
    public Player getPlayer() {
        return player;
    }

    /**
     * Scanner to take input from standard in
     */
    public Scanner scanner;

    /**
     * Instantiate a new game
     */
    public Game(Player player, ArrayList<Area> areas, ArrayList<Item> items, Area startingArea) {
        // setup scanner
        this.scanner = new Scanner(System.in);

        this.player = player;
        this.areas = areas;
        this.items = items;
        this.currentArea = startingArea;
    }

    private String readInputWithDefault() {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            return "exit";
        }
    }

    public void performAction() {
        while (true) {
            System.out.print("Type 'inspect' to inspect backpack, 'talk' to talk to NPC,\n 'move' to move to another area, or 'exit' to exit: ");
            String input = readInputWithDefault(); // Use the new method here
            switch (input.trim().toLowerCase()) {
                case "inspect":
                    player.inspectBackpack();
                    break;
                case "talk":
                    if (currentArea.NPCs.isEmpty()) {
                        System.out.println("There are no NPCs to talk to in this area.");
                        break;
                    }
                    // Letting the player choose which NPC to talk to.
                    for (int i = 0; i < currentArea.NPCs.size(); i++) {
                        System.out.println("[" + i + "] " + currentArea.NPCs.get(i).name);
                    }
                    System.out.print("Select NPC number to talk to, or type 'back' to go back: ");
                    String npcInput = scanner.nextLine();
                    if ("back".equalsIgnoreCase(npcInput.trim())) {
                        break;
                    }
                    try {
                        int selectedIndex = Integer.parseInt(npcInput);
                        if (selectedIndex < 0 || selectedIndex >= currentArea.NPCs.size()) {
                            System.out.println("Invalid choice. Please select a valid NPC number or type 'back' to go back.");
                        } else {
                            NPC selectedNPC = currentArea.NPCs.get(selectedIndex);
                            selectedNPC.interact();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number corresponding to the NPC or type 'back' to go back.");
                    }
                    break;
                case "move":
                    moveArea();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void moveArea() {
        if (areas.isEmpty()) {
            System.out.println("There are no other areas to move to.");
            return;
        }

        System.out.println("Available areas to move to:");
        for (int i = 0; i < areas.size(); i++) {
            if (!areas.get(i).equals(currentArea)) // Don’t list the current area as an option to move to.
                System.out.println("[" + i + "] " + areas.get(i).name);
        }

        System.out.print("Select area number to move to, or type 'back' to go back: ");
        while (true) {
            String areaInput = scanner.nextLine();
            if ("back".equalsIgnoreCase(areaInput.trim())) {
                break;
            }
            try {
                int selectedIndex = Integer.parseInt(areaInput);
                if (selectedIndex < 0 || selectedIndex >= areas.size() || areas.get(selectedIndex).equals(currentArea)) {
                    System.out.println("Invalid choice. Please select a valid area number or type 'back' to go back.");
                } else {
                    currentArea = areas.get(selectedIndex);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the area or type 'back' to go back.");
            }
            System.out.print("Select area number to move to, or type 'back' to go back: ");
        }
    }

    /**
     * Run the game. Assumes game is loaded
     */
    public void runGame() {
        while (currentArea != null) {
            currentArea.enterArea();
            //performAction();
        }
    }
}
