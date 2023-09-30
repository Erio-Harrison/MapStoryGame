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
     * Represents the state of a game, containing the player, areas, and items.
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
     * Represents actions in the game
     */
    public static abstract class Action {
        @Expose
        public String type;

        public abstract GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame);
    }
    public static class Choice extends Action {
        @Expose
        public Map<String, Action> choices;

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
    public static class ActionList extends Action {
        @Expose
        public List<Action> actions;

        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            ArrayList<GameEngine.Action> actions = new ArrayList<>();

            for (Action a : this.actions) {
                actions.add(a.generateAction(G, jsonGame));
            }

            return new GameEngine.ActionList(G, actions);
        }
    }
    public static class Say extends Action {
        @Expose
        public String say;

        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            return new GameEngine.Say(G, this.say);
        }
    }
    public static class Hurt extends Action {
        @Expose
        public int hurt;

        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            return new GameEngine.Hurt(G, this.hurt);
        }
    }
    public static class Heal extends Action {
        @Expose
        public int heal;

        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            return new GameEngine.Heal(G, this.heal);
        }
    }
    public static class ChangeArea extends Action {
        @Expose
        public String area;

        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            return new GameEngine.ChangeArea(G, jsonGame.retrieveArea(this.area));
        }
    }
    public static class RemoveFromArea extends Action {
        @Expose
        public Map<String, Integer> to_remove;
        @Expose
        public String area;

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
    public static class AddToArea extends Action {
        @Expose
        public Map<String, Integer> to_add;
        public String area;

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
    public static class RemoveFromBackpack extends Action {
        @Expose
        public Map<String, Integer> to_remove;

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
    public static class AddToBackpack extends Action {
        @Expose
        public Map<String, Integer> to_add;

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
    public static class Win extends Action {

        @Override
        public GameEngine.Action generateAction(GameEngine.Game G, Game jsonGame) {
            return new GameEngine.Win(G);
        }

    }

    public static class Requirement extends Action {
        @Expose
        public RequirementChecker requirement;
        @Expose
        public boolean choice_visible_if_requirement_not_met;
        @Expose
        public Action req_satisfied;
        @Expose
        public Action req_not_satisfied;

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
    public static abstract class RequirementChecker {
        @Expose
        public String type;

        public abstract GameEngine.RequirementChecker generateRequirement(GameEngine.Game G, Game jsonGame);
    }
    public static class ItemInBackpackCheck extends  RequirementChecker {
        @Expose
        public Map<String, Integer> items;

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
     * Represents an Item in the game.
     */
    public static class Item {
        @Expose
        public String id;
        @Expose
        public String description;
        @Expose
        public Action interact;

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
     * Represents a Non-Player Character (NPC) in the game.
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

        public GameEngine.NPC generateNPC(GameEngine.Game G, Game jsonGame) {
            GameEngine.Action initial = this.initial_interaction.generateAction(G, jsonGame);
            GameEngine.Action repeat = this.repeat_interaction.generateAction(G, jsonGame);
            return new GameEngine.NPC(starting_health, max_health, this.name, initial, repeat);
        }
    }

    /**
     * Represents the player in the game.
     */
    public static class Player {
        @Expose
        public Map<String, Integer> backpack;
        @Expose
        public int starting_health;
        @Expose
        public int max_health;

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