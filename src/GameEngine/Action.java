package GameEngine;
import java.util.*;

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


