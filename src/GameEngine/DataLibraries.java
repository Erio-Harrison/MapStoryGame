package GameEngine;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class DataLibraries {
    /**
     * Represents the state of a game, containing the player, areas, and items.
     */
    public static class Game {
        public Player player;
        public List<Area> areas;
        public List<Item> items;
    }

    /**
     * Represents actions in the game
     */
    public static class Action {
        public String type;
    }
    public static class Choice extends Action {
        public Map<String, Action> choices;
    }
    public static class ActionList extends Action {
        public List<Action> actions;
    }
    public static class Say extends Action {
        public String say;
    }
    public static class Hurt extends Action {
        public int hurt;
    }
    public static class Win extends Action {
    }

    static class ActionDeserializer implements JsonDeserializer<Action> {
        @Override
        public Action deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();

            return switch (type) {
                case "choices" -> context.deserialize(jsonObject, Choice.class);
                case "action_list" -> context.deserialize(jsonObject, ActionList.class);
                case "say" -> context.deserialize(jsonObject, Say.class);
                case "hurt" -> context.deserialize(jsonObject, Hurt.class);
                case "win" -> context.deserialize(jsonObject, Win.class);
                default -> throw new JsonParseException("Unknown action type: " + type);
            };
        }
    }

    /**
     * Represents an Area in the game, containing details, actions, items, and NPCs related to this area.
     */
    public static class Area {
        public Boolean starting_area;
        public String id;
        public String display_name;
        public Action action;
        public List<Map<String, Integer>> items;
        public List<NPC> npcs;
    }

    /**
     * Represents an Item in the game.
     */
    public static class Item {
        public String id;
        public String description;
        public List<Map<String, Object>> interact;
    }

    /**
     * Represents a Non-Player Character (NPC) in the game.
     */
    public static class NPC {
        public String name;
        public Map<String, Object> initial_interaction;
        public List<Map<String, Object>> repeat_interactions;
    }

    /**
     * Represents the player in the game.
     */
    public static class Player {
        public List<Map<String, Integer>> backpack;
        public int starting_health;
        public int max_health;
    }
}