package GameEngine;
import com.google.gson.Gson;

/**
 * The Game class holds the state of the currently running game.
 */
public abstract class Game {
    /**
     * Area player is currently in
     */
    public Area currentArea;

    /**
     * Player information
     */
    public Player player;

    /**
     * Instantiate game using JSON
     * @param JSON json string to generate game from
     */
    public Game(String JSON) {
    }
}
