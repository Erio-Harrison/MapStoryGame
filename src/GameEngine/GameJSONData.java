package GameEngine;

import com.google.gson.*;
import com.google.gson.annotations.Expose;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameJSONData {
    /**
     * Represents a game with players, areas, items and the current state.
     * This class is responsible for managing the game and its elements,
     * including creating, retrieving, and setting up the game, areas, items, and players.
     */
    public static class Game {
        @Expose
        public Player player;
        @Expose
        public List<Area> areas;
        @Expose
        public List<Item> items;

        public GameEngine.Game genGame;

        public Map<String, GameEngine.Area> areaMap;
        public Map<String, GameEngine.Item> itemMap;

        /**
         * Retrieves the area object associated with the given id string.
         *
         * @param id_str The string id of the area to be retrieved.
         * @return The GameEngine.Area object associated with the given id string.
         * @throws Error if the area associated with the given id string cannot be found.
         */
        public GameEngine.Area retrieveArea(String id_str) {
            if (this.areaMap.containsKey(id_str)) {
                // if in area map, use area
                return this.areaMap.get(id_str);
            } else {
                // otherwise generate area and use
                for (Area area : this.areas) {
                    if (area.id.equals(id_str)) {
                        // generate area
                        GameEngine.Area genArea = area.generateArea(this.genGame, this);
                        return genArea;
                    }
                }
            }
            throw new Error("Cannot find area: " + id_str);
        }

        /**
         * Retrieves the item object associated with the given id string.
         *
         * @param id_str The string id of the item to be retrieved.
         * @return The GameEngine.Item object associated with the given id string.
         * @throws Error if the item associated with the given id string cannot be found.
         */
        public GameEngine.Item retrieveItem(String id_str) {
            if (this.itemMap.containsKey(id_str)) {
                return this.itemMap.get(id_str);
            } else {
                for (Item item : this.items) {
                    if (item.id.equals(id_str)) {
                        GameEngine.Item genItem = item.generateItem(this.genGame, this);
                        this.itemMap.put(item.id, genItem);
                        return genItem;
                    }
                }
            }
            throw new Error("Cannot find item: " + id_str);
        }
        /**
         * Generates and sets up a new game with players, areas, and items.
         *
         * @return The newly generated GameEngine.Game instance.
         * @throws Error if no starting area is specified in the areas list.
         */
        public GameEngine.Game generateGame() {
            this.genGame = new GameEngine.Game(null, null, null, null);
            this.areaMap = new HashMap<>();
            this.itemMap = new HashMap<>();

            // Generate all items
            ArrayList<GameEngine.Item> items = new ArrayList<>();
            for (Item item : this.items) {
                items.add(this.retrieveItem(item.id));
            }

            // Generate player
            GameEngine.Player player = this.player.generatePlayer(this.genGame, this);

            // Generate areas
            ArrayList<GameEngine.Area> areas = new ArrayList<>();
            GameEngine.Area startingArea = null;
            for (Area area : this.areas) {
                GameEngine.Area genArea = this.retrieveArea(area.id);
                areas.add(genArea);

                if (area.starting_area != null && area.starting_area) {
                    startingArea = genArea;
                }
            }

            if (startingArea == null) {
                throw new Error("No starting area specified!");
            }

            // Setup game
            genGame.items = items;
            genGame.player = player;
            genGame.currentArea = startingArea;
            genGame.areas = areas;

            return this.genGame;
        }
    }

    /**
     * Represents an abstract action within the game.
     * This class is a parent class for all specific action classes.
     */
    public static abstract class Action {
        @Expose
        public String type;

        /**
         * Generates a GameEngine.Action instance related to the action.
         * This method is abstract and needs to be implemented by all subclasses.
         *
         * @param G The GameEngine.Game instance associated with the action.
         * @param jsonGame The Game instance associated with the action.
         * @return The generated GameEngine.Action instance.
         */
        public abstract GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame);
    }
    /**
     * Represents actions in the game. This is an abstract class that is
     * extended by various action types to represent different game actions.
     */
    public static class Choice extends Action {
        @Expose
        public Map<String, Action> choices;
        /**
         * Generates a GameEngine.Action instance based on the action type.
         *
         * @param G The Game instance.
         * @param jsonGame The GameJSONData.Game instance.
         * @return The generated GameEngine.Action instance.
         */
        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            Map<String, GameEngine.Action> choices = new HashMap<>();

            for (String s : this.choices.keySet()) {
                Action jsonAction = this.choices.get(s);
                choices.put(s, jsonAction.generateAction(G, jsonGame));
            }

            return new GameEngine.Choice(G, choices);
        }
    }
    /**
     * Represents a list of actions in the game.
     * This class is used when a sequence of actions needs to be executed.
     */
    public static class ActionList extends Action {
        @Expose
        public List<Action> actions;
        /**
         * Generates a corresponding GameEngine.ActionList from this ActionList.
         *
         * @param G The game instance associated with this ActionList.
         * @param jsonGame The JSON game data associated with this ActionList.
         * @return A new GameEngine.ActionList instance.
         */
        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            ArrayList<GameEngine.Action> actions = new ArrayList<>();

            for (Action a : this.actions) {
                actions.add(a.generateAction(G, jsonGame));
            }

            return new GameEngine.ActionList(G, actions);
        }
    }
    /**
     * Represents a say action in the game.
     * This action is used when the game needs to communicate something to the player.
     */
    public static class Say extends Action {
        @Expose
        public String say;
        /**
         * Generates a corresponding GameEngine.Say from this Say action.
         *
         * @param G The game instance associated with this Say action.
         * @param jsonGame The JSON game data associated with this Say action.
         * @return A new GameEngine.Say instance.
         */
        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            return new GameEngine.Say(G, this.say);
        }
    }
    /**
     * Represents a hurt action in the game, which can be used to inflict damage to the player.
     */
    public static class Hurt extends Action {
        @Expose
        public int hurt;
        /**
         * Generates a corresponding GameEngine.Hurt from this Hurt action.
         *
         * @param G The game instance associated with this Hurt action.
         * @param jsonGame The JSON game data associated with this Hurt action.
         * @return A new GameEngine.Hurt instance.
         */
        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            return new GameEngine.Hurt(G, this.hurt);
        }
    }
    /**
     * Represents a heal action in the game, which can be used to restore health to the player.
     */
    public static class Heal extends Action {
        @Expose
        public int heal;
        /**
         * Generates a corresponding GameEngine.Heal from this Heal action.
         *
         * @param G The game instance associated with this Heal action.
         * @param jsonGame The JSON game data associated with this Heal action.
         * @return A new GameEngine.Heal instance.
         */
        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            return new GameEngine.Heal(G, this.heal);
        }
    }

    /**
     * Represents a change area action in the game.
     * This action is used when the player needs to move from one area to another within the game.
     */
    public static class ChangeArea extends Action {
        @Expose
        public String area;
        /**
         * Generates a corresponding GameEngine.ChangeArea from this ChangeArea action.
         *
         * @param G The game instance associated with this ChangeArea action.
         * @param jsonGame The JSON game data associated with this ChangeArea action.
         * @return A new GameEngine.ChangeArea instance.
         */
        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            return new GameEngine.ChangeArea(G, jsonGame.retrieveArea(this.area));
        }
    }

    /**
     * Represents a remove from area action in the game.
     * This action is used when items need to be removed from a specific area within the game.
     */
    public static class RemoveFromArea extends Action {
        @Expose
        public Map<String, Integer> to_remove;
        @Expose
        public String area;
        /**
         * Generates a corresponding GameEngine.RemoveFromArea from this RemoveFromArea action.
         *
         * @param G The game instance associated with this RemoveFromArea action.
         * @param jsonGame The JSON game data associated with this RemoveFromArea action.
         * @return A new GameEngine.RemoveFromArea instance.
         */

        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            Map <GameEngine.Item, Integer> itemsToRemove = new HashMap<>();

            for (String id: this.to_remove.keySet()) {
                Integer amount_to_remove = this.to_remove.get(id);
                itemsToRemove.put(jsonGame.retrieveItem(id), amount_to_remove);
            }
            return new GameEngine.RemoveFromArea(G, itemsToRemove, jsonGame.retrieveArea(this.area));
        }
    }
    /**
     * Represents an add to area action in the game.
     * This action is used when items need to be added to a specific area within the game.
     */
    public static class AddToArea extends Action {
        @Expose
        public Map<String, Integer> to_add;
        public String area;

        /**
         * Generates a corresponding GameEngine.AddToArea from this AddToArea action.
         *
         * @param G The game instance associated with this AddToArea action.
         * @param jsonGame The JSON game data associated with this AddToArea action.
         * @return A new GameEngine.AddToArea instance.
         */
        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            Map <GameEngine.Item, Integer> itemsToAdd = new HashMap<>();

            for (String id: this.to_add.keySet()) {
                Integer amount_to_remove = this.to_add.get(id);
                itemsToAdd.put(jsonGame.retrieveItem(id), amount_to_remove);
            }
            return new GameEngine.AddToArea(G, itemsToAdd, jsonGame.retrieveArea(this.area));
        }
    }
    /**
     * Represents a remove from backpack action in the game.
     * This action is used when items need to be removed from the player's backpack.
     */
    public static class RemoveFromBackpack extends Action {
        @Expose
        public Map<String, Integer> to_remove;

        /**
         * Generates a corresponding GameEngine.RemoveFromBackpack from this RemoveFromBackpack action.
         *
         * @param G The game instance associated with this RemoveFromBackpack action.
         * @param jsonGame The JSON game data associated with this RemoveFromBackpack action.
         * @return A new GameEngine.RemoveFromBackpack instance.
         */
        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            Map <GameEngine.Item, Integer> itemsToRemove = new HashMap<>();

            for (String id: this.to_remove.keySet()) {
                Integer amount_to_remove = this.to_remove.get(id);
                itemsToRemove.put(jsonGame.retrieveItem(id), amount_to_remove);
            }
            return new GameEngine.RemoveFromBackpack(G, itemsToRemove);
        }
    }

    /**
     * Represents an add to backpack action in the game.
     * This action is used when items need to be added to the player's backpack.
     */
    public static class AddToBackpack extends Action {
        @Expose
        public Map<String, Integer> to_add;

        /**
         * Generates a corresponding GameEngine.AddToBackpack from this AddToBackpack action.
         *
         * @param G The game instance associated with this AddToBackpack action.
         * @param jsonGame The JSON game data associated with this AddToBackpack action.
         * @return A new GameEngine.AddToBackpack instance.
         */
        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            Map <GameEngine.Item, Integer> itemsToAdd = new HashMap<>();

            for (String id: this.to_add.keySet()) {
                Integer amount_to_add = this.to_add.get(id);
                itemsToAdd.put(jsonGame.retrieveItem(id), amount_to_add);
            }
            return new GameEngine.AddToBackpack(G, itemsToAdd);
        }
    }
    /**
     * Represents a win action in the game.
     * This action is used when the player achieves a win condition within the game.
     */
    public static class Win extends Action {
        /**
         * Generates a corresponding GameEngine.Win from this Win action.
         *
         * @param G The game instance associated with this Win action.
         * @param jsonGame The JSON game data associated with this Win action.
         * @return A new GameEngine.Win instance.
         */
        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            return new GameEngine.Win(G);
        }

    }
    /**
     * Represents a requirement action in the game.
     * This action is used to check a requirement and determine the next action based on whether the requirement is met or not.
     */
    public static class Requirement extends Action {
        @Expose
        public RequirementChecker requirement;
        @Expose
        public boolean choice_visible_if_requirement_not_met;
        @Expose
        public Action req_satisfied;
        @Expose
        public Action req_not_satisfied;
        /**
         * Generates a corresponding GameEngine.Requirement from this Requirement action.
         *
         * @param G The game instance associated with this Requirement action.
         * @param jsonGame The JSON game data associated with this Requirement action.
         * @return A new GameEngine.Requirement instance.
         */
        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            GameEngine.Action satisfied = this.req_satisfied.generateAction(G, jsonGame);

            GameEngine.Action not_satisfied;
            if (this.req_not_satisfied == null) {
                not_satisfied = new GameEngine.DoNothing(G);
            } else {
                not_satisfied = this.req_not_satisfied.generateAction(G, jsonGame);
            }

            GameEngine.RequirementChecker req_check = this.requirement.generateRequirement(G, jsonGame);
            return new GameEngine.Requirement(G, req_check, satisfied, not_satisfied);
        }
    }
    /**
     * A custom deserializer for Action objects.
     * This class helps in deserializing JSON objects into Java objects of the corresponding action class.
     */
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
     * Represents an abstract requirement checker within the game.
     * This class is a parent class for all specific requirement checker classes.
     */
    public static abstract class RequirementChecker {
        @Expose
        public String type;

        /**
         * Generates a GameEngine.RequirementChecker instance related to the requirement checker.
         * This method is abstract and needs to be implemented by all subclasses.
         *
         * @param G The GameEngine.Game instance associated with the requirement checker.
         * @param jsonGame The Game instance associated with the requirement checker.
         * @return The generated GameEngine.RequirementChecker instance.
         */
        public abstract GameEngine.RequirementChecker generateRequirement(GameEngine.Game G, Game jsonGame);
    }
    /**
     * Represents a requirement checker that checks if certain items are present in the backpack.
     * This class is used to validate if certain conditions are met in the game, based on the items present in the backpack.
     */
    public static class ItemInBackpackCheck extends  RequirementChecker {
        @Expose
        public Map<String, Integer> items;

        /**
         * Generates a corresponding GameEngine.ItemInBackpackCheck from this ItemInBackpackCheck.
         *
         * @param G The game instance associated with this ItemInBackpackCheck.
         * @param jsonGame The JSON game data associated with this ItemInBackpackCheck.
         * @return A new GameEngine.ItemInBackpackCheck instance that can be used to check game requirements.
         */
        @Override
        public GameEngine.RequirementChecker generateRequirement(GameEngine.Game G, Game jsonGame) {
            Map <GameEngine.Item, Integer> itemsToCheck = new HashMap<>();

            for (String id: this.items.keySet()) {
                Integer amount_to_check = this.items.get(id);
                itemsToCheck.put(jsonGame.retrieveItem(id), amount_to_check);
            }

            return new GameEngine.ItemInBackpackCheck(G, itemsToCheck);
        }
    }

    /**
     * Deserializer for RequirementChecker, used to deserialize JSON representations of RequirementChecker objects.
     * The deserializer reads the 'type' attribute from the JSON object to determine the specific type of RequirementChecker to instantiate.
     */

    static class RequirementCheckerDeserializer implements JsonDeserializer<RequirementChecker> {
        /**
         * Deserializes the given JsonElement into a corresponding RequirementChecker object.
         *
         * @param json The JsonElement being deserialized.
         * @param typeOfT The type of the Object to deserialize to.
         * @param context Context for deserialization where the current state of deserialization is stored.
         * @return A new RequirementChecker object deserialized from the provided JsonElement.
         * @throws JsonParseException if json is not in the expected format of RequirementChecker
         */
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
     * Represents an area within the game, containing details, actions, items, and NPCs related to this area.
     * This class is responsible for managing and generating the area and its elements.
     */
    public static class Area {
        @Expose
        public Boolean starting_area;
        @Expose
        public String id;
        @Expose
        public String display_name;
        @Expose
        public Action action;
        @Expose
        public Map<String, Integer> items;
        @Expose
        public List<NPC> npcs;

        /**
         * Generates and sets up a new area with items and NPCs.
         *
         * @param G The GameEngine.Game instance associated with the area.
         * @param jsonGame The Game instance associated with the area.
         * @return The newly generated GameEngine.Area instance.
         */
        public GameEngine.Area generateArea(GameEngine.Game G, Game jsonGame) {
            // store area to be created in area map to avoid recursively generating areas when areas refer to themselves
            GameEngine.Area genArea = new GameEngine.Area(null, null, null, null);
            jsonGame.areaMap.put(this.id, genArea);

            // generate items in area
            Map<GameEngine.Item, Integer> items = new HashMap<>();
            for (String id : this.items.keySet()) {
                Integer amt = this.items.get(id);
                items.put(jsonGame.retrieveItem(id), amt);
            }

            // generate area NPCs
            ArrayList<GameEngine.NPC> NPCs = new ArrayList<>();
            if (this.npcs != null) {
                for (NPC npc : this.npcs) {
                    NPCs.add(npc.generateNPC(G, jsonGame));
                }
            }

            // generate area action
            GameEngine.Action action = this.action.generateAction(G, jsonGame);

            // setup area
            genArea.name = this.display_name;
            genArea.NPCs = NPCs;
            genArea.Items = items;
            genArea.action = action;

            return genArea;
        }
    }

    /**
     * Represents an item within the game.
     * This class is responsible for managing and generating the item and its interactions.
     */
    public static class Item {
        @Expose
        public String id;
        @Expose
        public String description;
        @Expose
        public Action interact;

        /**
         * Generates and sets up a new item with interactions.
         *
         * @param game The GameEngine.Game instance associated with the item.
         * @param jsonGame The Game instance associated with the item.
         * @return The newly generated GameEngine.Item instance.
         */
        public GameEngine.Item generateItem(GameEngine.Game game, Game jsonGame) {
            GameEngine.Action interact;
            if (this.interact == null) {
                interact = new GameEngine.DoNothing(game);
            } else {
                interact = this.interact.generateAction(game, jsonGame);
            }
            return new GameEngine.Item(this.id, this.description, interact);
        }
    }

    /**
     * Represents a Non-Player Character (NPC) within the game.
     * This class is responsible for managing and generating the NPC and its interactions.
     */
    public static class NPC {
        @Expose
        public String name;
        @Expose
        public int starting_health;
        @Expose
        public int max_health;
        @Expose
        public Action initial_interaction;
        @Expose
        public Action repeat_interaction;

        /**
         * Generates and sets up a new NPC with interactions.
         *
         * @param G The GameEngine.Game instance associated with the NPC.
         * @param jsonGame The Game instance associated with the NPC.
         * @return The newly generated GameEngine.NPC instance.
         */
        public GameEngine.NPC generateNPC(GameEngine.Game G, Game jsonGame) {
            GameEngine.Action initial = this.initial_interaction.generateAction(G, jsonGame);
            GameEngine.Action repeat = this.repeat_interaction.generateAction(G, jsonGame);
            return new GameEngine.NPC(starting_health, max_health, this.name, initial, repeat);
        }
    }

    /**
     * Represents the player within the game.
     * This class is responsible for managing and generating the player and its backpack.
     */
    public static class Player {
        @Expose
        public Map<String, Integer> backpack;
        @Expose
        public int starting_health;
        @Expose
        public int max_health;
        /**
         * Generates and sets up a new player with backpack items.
         *
         * @param game The GameEngine.Game instance associated with the player.
         * @param jsonGame The Game instance associated with the player.
         * @return The newly generated GameEngine.Player instance.
         */
        public GameEngine.Player generatePlayer(GameEngine.Game game, Game jsonGame) {
            Map<GameEngine.Item, Integer> backpack = new HashMap<>();

            for (String id : this.backpack.keySet()) {
                Integer amt = this.backpack.get(id);
                backpack.put(jsonGame.retrieveItem(id), amt);
            }

            return new GameEngine.Player(this.starting_health, this.max_health, backpack);
        }
    }
}