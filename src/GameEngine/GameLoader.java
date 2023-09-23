package GameEngine;

/**
 * Loads game from given config file and runs it.
 */
public class GameLoader {
    /**
     * Load game from config file, then run it.
     * @param args There must be one argument, which is the path to config file
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide game config file as argument.");
            System.exit(1);
        }
        System.out.println("game config file:  " + args[0]);
        // load game
    }
}
