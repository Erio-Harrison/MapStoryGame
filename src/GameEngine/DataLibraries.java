package GameEngine;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DataLibraries {
    /**
     * Represents the state of a game, containing the player, areas, and items.
     */
    public static class Game {
        private final Player player;
        private final List<Area> areas;
        private final List<Item> items;

        /**
         * Constructs a Game with the given player, areas, and items.
         *
         * @param player the player of the game. Must not be null.
         * @param areas  the list of areas in the game. Must not be null.
         * @param items  the list of items in the game. Must not be null.
         */
        public Game(Player player, List<Area> areas, List<Item> items) {
            this.player = player;
            this.areas = areas;
            this.items = items;
        }

        /**
         * @return the player of the game.
         */
        public Player getPlayer() {
            return player;
        }

        /**
         * @return an unmodifiable view of the list of areas in the game.
         */
        public List<Area> getAreas() {
            return Collections.unmodifiableList(areas);
        }

        /**
         * @return an unmodifiable view of the list of items in the game.
         */
        public List<Item> getItems() {
            return Collections.unmodifiableList(items);
        }

        @Override
        public String toString() {
            return "Game{" +
                    "player=" + player +
                    ", areas=" + areas +
                    ", items=" + items +
                    '}';
        }
    }
    /**
     * Represents an Area in the game, containing details, actions, items, and NPCs related to this area.
     */
    public static class Area {
        private final String id;
        private final String displayName;
        private final String description;
        private final List<Map<String, Object>> actions;
        private final List<Map<String, Integer>> items;
        private final List<NPC> npcs;

        /**
         * Constructs an Area with the given details, actions, items, and NPCs.
         *
         * @param id           the id of the area. Must not be null.
         * @param displayName  the display name of the area. Must not be null.
         * @param description  the description of the area. Must not be null.
         * @param actions      the actions related to this area. Must not be null.
         * @param items        the items found in this area. Must not be null.
         * @param npcs         the NPCs found in this area. Must not be null.
         */
        public Area(String id, String displayName, String description, List<Map<String, Object>> actions,
                    List<Map<String, Integer>> items, List<NPC> npcs) {

            this.id = id;
            this.displayName = displayName;
            this.description = description;
            this.actions = actions; // Creating a defensive copy
            this.items = items; // Creating a defensive copy
            this.npcs = npcs; // Creating a defensive copy
        }

        /**
         * @return the id of the area.
         */
        public String getId() {
            return id;
        }

        /**
         * @return the display name of the area.
         */
        public String getDisplayName() {
            return displayName;
        }

        /**
         * @return the description of the area.
         */
        public String getDescription() {
            return description;
        }

        /**
         * @return an unmodifiable view of the actions related to this area.
         */
        public List<Map<String, Object>> getActions() {
            return Collections.unmodifiableList(actions);
        }

        /**
         * @return an unmodifiable view of the items found in this area.
         */
        public List<Map<String, Integer>> getItems() {
            return Collections.unmodifiableList(items);
        }

        /**
         * @return an unmodifiable view of the NPCs found in this area.
         */
        public List<NPC> getNpcs() {
            return Collections.unmodifiableList(npcs);
        }

        @Override
        public String toString() {
            return "Area{" +
                    "id='" + id + '\'' +
                    ", displayName='" + displayName + '\'' +
                    ", description='" + description + '\'' +
                    ", actions=" + actions +
                    ", items=" + items +
                    ", npcs=" + npcs +
                    '}';
        }
    }
    /**
     * Represents an Item in the game.
     */
    public static class Item {
        private final String id;
        private final String description;
        private final List<Map<String, Object>> interact;

        /**
         * Constructs an Item with the given id, description, and interactions.
         *
         * @param id          the id of the item. Must not be null.
         * @param description the description of the item. Must not be null.
         * @param interact    the interactions of the item. Must not be null.
         */
        public Item(String id, String description, List<Map<String, Object>> interact) {
            this.id = id;
            this.description = description;
            this.interact = interact; // Creating a defensive copy
        }

        /**
         * @return the id of the item.
         */
        public String getId() {
            return id;
        }

        /**
         * @return the description of the item.
         */
        public String getDescription() {
            return description;
        }

        /**
         * @return an unmodifiable view of the interactions of the item.
         */
        public List<Map<String, Object>> getInteract() {
            return Collections.unmodifiableList(interact);
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id='" + id + '\'' +
                    ", description='" + description + '\'' +
                    ", interact=" + interact +
                    '}';
        }
    }
    /**
     * Represents a Non-Player Character (NPC) in the game.
     */
    public static class NPC {

        private final String name;
        private final Map<String, Object> initialInteraction;
        private final List<Map<String, Object>> repeatInteractions;

        /**
         * Constructs an NPC with the given name, initial interaction, and repeat interactions.
         *
         * @param name               the name of the NPC.
         * @param initialInteraction the initial interaction with the NPC.
         * @param repeatInteractions a list of interactions that can be repeated.
         */
        public NPC(String name, Map<String, Object> initialInteraction, List<Map<String, Object>> repeatInteractions) {
            this.name = name;
            this.initialInteraction = initialInteraction;
            this.repeatInteractions = repeatInteractions;
        }

        /**
         * @return the name of the NPC.
         */
        public String getName() {
            return name;
        }

        /**
         * @return an unmodifiable view of the initial interaction with the NPC.
         */
        public Map<String, Object> getInitialInteraction() {
            return Collections.unmodifiableMap(initialInteraction);
        }

        /**
         * @return an unmodifiable view of the list of repeat interactions with the NPC.
         */
        public List<Map<String, Object>> getRepeatInteractions() {
            return Collections.unmodifiableList(repeatInteractions);
        }

        @Override
        public String toString() {
            return "NPC{" +
                    "name='" + name + '\'' +
                    ", initialInteraction=" + initialInteraction +
                    ", repeatInteractions=" + repeatInteractions +
                    '}';
        }
    }
    /**
     * Represents the player in the game.
     */
    public static class Player {
        private final List<Map<String, Integer>> backpack;
        private int hp;
        private final int startingHealth;

        /**
         * Constructs a player with the given starting health and backpack items.
         *
         * @param startingHealth the starting health of the player. Must be a positive value.
         * @param backpack       the backpack of the player. Must not be null.
         */
        public Player(int startingHealth, List<Map<String, Integer>> backpack) {
            this.hp = startingHealth;
            this.startingHealth = startingHealth;
            this.backpack = backpack;
        }

        /**
         * @return an unmodifiable view of the backpack.
         */
        public List<Map<String, Integer>> getBackpack() {
            return Collections.unmodifiableList(backpack);
        }

        /**
         * @return the current health of the player.
         */
        public int getHp() {
            return hp;
        }

        /**
         * @return the starting health of the player.
         */
        public int getStartingHealth() {
            return startingHealth;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "backpack=" + backpack +
                    ", hp=" + hp +
                    ", startingHealth=" + startingHealth +
                    '}';
        }
    }
}

