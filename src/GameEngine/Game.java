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

    /**
     * Run the game. Assumes game is loaded
     */
    public void runGame() {
        // do the action of the first area

        while (currentArea != null) {
            currentArea.enterArea();
        }
    }
}
