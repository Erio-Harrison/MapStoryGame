package GameEngine;
import com.google.gson.Gson;

/**
 * The Game class holds the state of the currently running game.
 */
public class Game {
    /**
     * Area player is currently in
     */
    public Area currentArea;

    /**
     * Player information
     */
    public Player player;

    public Area getCurrentArea() {
        return currentArea;
    }

    public void setCurrentArea(Area currentArea) {
        this.currentArea = currentArea;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Instantiate game using JSON
     * @param JSON json string to generate game from
     */
    public Game(String JSON) {
    }
}
