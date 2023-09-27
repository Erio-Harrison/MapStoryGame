package GameEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.crypto.Data;
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
                .registerTypeAdapter(DataLibraries.Action.class, new DataLibraries.ActionDeserializer())
                .create();

        try (FileReader reader = new FileReader("config.json")) {
            // Deserialize JSON to Game object
            DataLibraries.Game game = gson.fromJson(reader, DataLibraries.Game.class);
            System.out.println(game);
            System.out.println(game.player);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);

        // create empty game
        Game G = new Game(null, null, null);

        // create backpack and put one toothpick in it
        Map<Item, Integer> backpack = new HashMap<>();
        Say interactToothpick = new Say(G, "this is a toothpick!");
        Item toothpick = new Item("toothpick", interactToothpick);
        backpack.put(toothpick, 1);

        // create player
        Player p = new Player(backpack);

        // create starting area
        Say sayWelcome = new Say(G, "The fireplace crackles");
        Win wingame = new Win(G);

        Map<String, Action> choices = new HashMap<>();
        Say choice1 = new Say(G, "you did the first choice!");
        Say choice2 = new Say(G, "you did the second choice!");
        choices.put("Choice 1", choice1);
        choices.put("Choice 2", choice2);
        Choice choice = new Choice(G, choices);

        ArrayList<Action> fireplace_actions = new ArrayList<>();
        fireplace_actions.add(sayWelcome);
        fireplace_actions.add(choice);
        fireplace_actions.add(wingame);
        ActionList f_actions = new ActionList(G, fireplace_actions);

        Area fireplace = new Area("Fireplace", new ArrayList<>(), new ArrayList<>(), f_actions);

        // create starting areas (this game has only one area which is fireplace
        ArrayList<Area> areas = new ArrayList<>();
        areas.add(fireplace);

        // set up and run game
        G.player = p;
        G.areas = areas;
        G.currentArea = fireplace;
        G.runGame();

    }

}
