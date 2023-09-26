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
     * Player information
     */
    public Player player;

    /**
     * Scanner to take input from standard in
     */
    public Scanner scanner;

    /**
     * Instantiate a new game
     */
    public Game(Player player, ArrayList<Area> areas, Area startingArea) {
        // setup scanner
        this.scanner = new Scanner(System.in);

        this.player = player;
        this.areas = areas;
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
