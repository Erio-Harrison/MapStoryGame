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

    public void performAction() {
        while (true) {
            System.out.print("Type 'inspect' to inspect backpack, 'talk' to talk to NPC, or 'exit' to exit: ");
            String input = scanner.nextLine();
            switch (input.trim().toLowerCase()) {
                case "inspect":
                    player.inspectBackpack();
                    break;
                case "talk":
                    List<NPC> npcs = currentArea.getNPCs();
                    if (npcs.isEmpty()) {
                        System.out.println("There are no NPCs to talk to in this area.");
                        break;
                    }
                    System.out.println("Select an NPC to talk to:");
                    for (int i = 0; i < npcs.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + npcs.get(i).name);
                    }
                    int selectedIdx = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    if (selectedIdx < 1 || selectedIdx > npcs.size()) {
                        System.out.println("Invalid choice. Please try again.");
                        break;
                    }
                    NPC selectedNPC = npcs.get(selectedIdx - 1);
                    selectedNPC.interact();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    /**
     * Run the game. Assumes game is loaded
     */
    public void runGame() {
        while (currentArea != null) {
            currentArea.enterArea();
            performAction();
        }
    }
}
