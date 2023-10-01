import GameEngine.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestRemoveFromArea {
    private Game game;
    private Area testArea1;
    private Area testArea2;
    private Player player;
    @BeforeEach
    public void setup() {
        Action dummyAction = new Action(null) {
            @Override
            public void doAction() {
                // Dummy action
            }
        };
        testArea1 = new Area("Test Area 1", new ArrayList<>(), new HashMap<>(), dummyAction);
        testArea2 = new Area("Test Area 2", new ArrayList<>(), new HashMap<>(), dummyAction);
        ArrayList<Area> areas = new ArrayList<>();
        areas.add(testArea1);
        areas.add(testArea2);
        ArrayList<Item> items = new ArrayList<>();
        player = new Player(100, 100, null, null);
        game = new Game(player, areas, items, testArea1);
    }
    GameEngine.Action action = new GameEngine.Action(null) {
        @Override
        public void doAction() {
        }
    };
    GameEngine.Area targetArea = new GameEngine.Area("Test Area", new ArrayList<>(), new HashMap<>(), action);

    @Test
    public void testItemRemoval() {

        // Initialize the initialItems Map
        Map<Item, Integer> initialItems = new HashMap<>();
        Item item1 = new Item("Test Item", "Description", action);
        Item item2 = new Item("Test Item", "Description", action);
        initialItems.put(item1, 3);
        initialItems.put(item2, 5);

        // Update the targetArea.Items with initialItems
        targetArea.Items = initialItems;

        // Define the items to remove and create a RemoveFromArea instance
        Map<Item, Integer> itemsToRemove = new HashMap<>();
        itemsToRemove.put(item1, 2);
        itemsToRemove.put(item2, 4);
        RemoveFromArea removeFromArea = new RemoveFromArea(game, itemsToRemove, targetArea);

        // Execute the action
        removeFromArea.doAction();

        // Assert the expected outcomes
        Assertions.assertEquals(1, targetArea.Items.get(item1), "Item1 quantity should be reduced to 1");
        Assertions.assertEquals(1, targetArea.Items.get(item2), "Item2 quantity should be reduced to 1");
    }
}


