package GameEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.*;  

/**
 * Loads game from given config file and runs it.
 */
public class GameLoader {
    /**
     * Load game from config file, then run it.
     * @param args There must be one argument, which is the path to config file
     */
    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(GameJSONData.Action.class, new GameJSONData.ActionDeserializer())
                .registerTypeAdapter(GameJSONData.RequirementChecker.class, new GameJSONData.RequirementCheckerDeserializer())
                .create();

        try (FileReader reader = new FileReader("config.json")) {
            // Deserialize JSON to Game object
            GameJSONData.Game gameJson = gson.fromJson(reader, GameJSONData.Game.class);

            // Create game from JSON file
            Game G = gameJson.generateGame();

            // run the game
            G.runGame();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }

}
