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
    public static class Heal extends Action {
        public int hurt;
    }
    public static class ChangeArea extends Action {
        public String area;
    }
    public static class RemoveFromArea extends Action {
        public Map<String, Integer> to_remove;
    }
    public static class AddToArea extends Action {
        public Map<String, Integer> to_add;
    }
    public static class RemoveFromBackpack extends Action {
        public Map<String, Integer> to_remove;
    }
    public static class AddToBackpack extends Action {
        public Map<String, Integer> to_add;
    }
    public static class Win extends Action {
    }
    public static class Requirement extends Action {
        public RequirementChecker requirement;
        public boolean choice_visible_if_requirement_not_met;
        public Action req_satisfied;
        public Action req_not_satisfied;
    }

    static class ActionDeserializer implements JsonDeserializer<Action> {
        @Override
        public Action deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            if (!jsonObject.has("type")) {
                throw new JsonParseException("Actions need to have a 'type' attribute specifying what type they are! " + jsonObject);
            }
            String type = jsonObject.get("type").getAsString();

            switch (type) {
                case "choices":
                    return context.deserialize(jsonObject, Choice.class);
                case "action_list":
                    return context.deserialize(jsonObject, ActionList.class);
                case "say":
                    return context.deserialize(jsonObject, Say.class);
                case "hurt":
                    return context.deserialize(jsonObject, Hurt.class);
                case "heal":
                    return context.deserialize(jsonObject, Heal.class);
                case "win":
                    return context.deserialize(jsonObject, Win.class);
                case "change_area":
                    return context.deserialize(jsonObject, ChangeArea.class);
                case "remove_item_from_area":
                    return context.deserialize(jsonObject, RemoveFromArea.class);
                case "add_item_to_area":
                    return context.deserialize(jsonObject, AddToArea.class);
                case "remove_item_from_backpack":
                    return context.deserialize(jsonObject, RemoveFromBackpack.class);
                case "add_item_to_backpack":
                    return context.deserialize(jsonObject, AddToBackpack.class);
                case "requirement":
                    return context.deserialize(jsonObject, Requirement.class);
                default:
                    throw new JsonParseException("Unknown action type: " + type);
            }
        }
    }

    /**
     * Represents checks needed by 'Requirement' action
     */
    public static class RequirementChecker {
        public String type;
    }
    public static class ItemInBackpackCheck extends  RequirementChecker {
        public Map<String, Integer> items;
    }

    static class RequirementCheckerDeserializer implements JsonDeserializer<RequirementChecker> {
        @Override
        public RequirementChecker deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();

            switch (type) {
                case "backpack_contains_at_least":
                    return context.deserialize(jsonObject, ItemInBackpackCheck.class);
                default:
                    throw new JsonParseException("Unknown requirement type: " + type);
            }
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
        public Map<String, Integer> items;
        public List<NPC> npcs;
    }

    /**
     * Represents an Item in the game.
     */
    public static class Item {
        public String id;
        public String description;
        public Action interact;
    }

    /**
     * Represents a Non-Player Character (NPC) in the game.
     */
    public static class NPC {
        public String name;
        public Action initial_interaction;
        public Action repeat_interactions;
    }

    /**
     * Represents the player in the game.
     */
    public static class Player {
        public Map<String, Integer> backpack;
        public int starting_health;
        public int max_health;
    }
}