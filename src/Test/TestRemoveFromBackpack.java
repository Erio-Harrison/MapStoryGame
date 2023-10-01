import GameEngine.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestRemoveFromBackpack {
    private Game game;
    private Area testArea1;
    private Area testArea2;
    private Player player;
    private Item testItem;
    private Item anotherItem;

    @BeforeEach
    public void setup() {
        Action dummyAction = new Action(null) {
            @Override
            public void doAction() {
                // Dummy action
            }
        };

        testItem = new Item("Test Item", "Description", dummyAction);
        anotherItem = new Item("Another Item", "Another Description", dummyAction);

        testArea1 = new Area("Test Area 1", new ArrayList<>(), new HashMap<>(), dummyAction);
        testArea2 = new Area("Test Area 2", new ArrayList<>(), new HashMap<>(), dummyAction);

        Map<Item, Integer> playerBackpack = new HashMap<>();
        playerBackpack.put(testItem, 3); // Setting initial quantity
        playerBackpack.put(anotherItem, 5); // Setting initial quantity

        player = new Player(100, 100, playerBackpack, null);

        ArrayList<Area> areas = new ArrayList<>();
        areas.add(testArea1);
        areas.add(testArea2);

        ArrayList<Item> items = new ArrayList<>();
        items.add(testItem);
        items.add(anotherItem);

        game = new Game(player, areas, items, testArea1);
    }

    @Test
    public void testItemRemoval() {
        Map<Item, Integer> itemsToRemove = new HashMap<>();
        itemsToRemove.put(testItem, 2);
        itemsToRemove.put(anotherItem, 4);

        RemoveFromBackpack removeFromBackpack = new RemoveFromBackpack(game, itemsToRemove);

        // Execute the action
        removeFromBackpack.doAction();

        // Assert the expected outcomes
        Assertions.assertEquals(1, player.backpack.get(testItem), "Test Item quantity should be reduced to 1");
        Assertions.assertEquals(1, player.backpack.get(anotherItem), "Another Item quantity should be reduced to 1");
    }
}