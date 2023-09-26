package GameEngine;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Loads game from given config file and runs it.
 */
public class GameLoader {
    /**
     * Load game from config file, then run it.
     * @param args There must be one argument, which is the path to config file
     */
    public static void main(String[] args) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("config.json")) {
            // Deserialize JSON to Game object
            DataLibraries.Game game = gson.fromJson(reader, DataLibraries.Game.class);
            // Now, the `game` object contains the data from the JSON file.
            Map<String, Object> str =  game.getAreas().get(1).getActions().get(0);
            System.out.println(str.get("choices").getClass().getName());

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
