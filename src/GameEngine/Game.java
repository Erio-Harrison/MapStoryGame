package GameEngine;
import java.util.*;

/**
 * The Game class represents the main control flow of the game. It maintains the
 * state of the game, holds references to the different areas in the game, the
 * items, and the player. The class provides methods to instantiate a new game,
 * run the game, set the current area, and get the player.
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

    /**
     * Sets the current area of the player in the game.
     *
     * @param currentArea the area to be set as current area.
     */
    public void setCurrentArea(Area currentArea) {
        this.currentArea = currentArea;
    }

    /**
     * Retrieves the player of the game.
     *
     * @return the player of the game.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Scanner to take input from standard in
     */
    public Scanner scanner;

    /**
     * Instantiate a new game with specified player, areas, items, and starting area.
     *
     * @param player        the player of the game.
     * @param areas         the list of all areas in the game.
     * @param items         the list of all items in the game.
     * @param startingArea  the starting area of the game.
     */
    public Game(Player player, ArrayList<Area> areas, ArrayList<Item> items, Area startingArea) {
        // setup scanner
        this.scanner = new Scanner(System.in);

        this.player = player;
        this.areas = areas;
        this.items = items;
        this.currentArea = startingArea;
    }

    /**
     * Reads input from the player with a default value to avoid NoSuchElementException.
     *
     * @return the trimmed and lowercased input from the player, "exit" if an exception occurs.
     */
    private String readInputWithDefault() {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            return "exit";
        }
    }

    /**
     * Run the game. Assumes game is loaded
     */
    public void runGame() {
        Area prevArea = null;
        while (currentArea != null) {
            if (prevArea != currentArea) {
                System.out.println("You enter the " + currentArea.name);
            }
            prevArea = currentArea;
            currentArea.enterArea();
        }
    }

}
