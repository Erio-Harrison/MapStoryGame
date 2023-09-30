import GameEngine.GameJSONData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TestItem {
    @Test
    public void testItemInitialization() {
        GameEngine.Action action = new GameEngine.Action(null) {
            @Override
            public void doAction() {
            }
        };
        GameEngine.Item item = new GameEngine.Item("Test Item", "Description", action);
        Assertions.assertNotNull(item, "Item object should not be null");
    }
    @Test
    public void testRetrieveItem() {
        GameJSONData.Game gameJSON = new GameJSONData.Game();
        gameJSON.itemMap = new HashMap<>();
        gameJSON.items = new ArrayList<>();

        // Create a real GameEngine.Item object with an anonymous Action
        GameEngine.Action action = new GameEngine.Action(null) {
            @Override
            public void doAction() {
            }
        };

        GameEngine.Item item = new GameEngine.Item("Test Item", "Description", action);
        gameJSON.itemMap.put("testItem", item);

        // Execute the method to be tested
        GameEngine.Item retrievedItem = gameJSON.retrieveItem("testItem");

        // Assert the results
        Assertions.assertEquals(item, retrievedItem);
    }

    @Test
    public void testItemInteraction() {
        GameEngine.Action action = new GameEngine.Action(null) {
            @Override
            public void doAction() {
            }
        };
        GameEngine.Item item = new GameEngine.Item("Test Item", "Description", action);
        Assertions.assertEquals(action, item.interact, "Item interaction should be equal to assigned action");
    }

    @Test
    public void testItemDescription() {
        GameEngine.Item item = new GameEngine.Item("TestItemID", "TestItemDescription", null);
        Assertions.assertEquals("TestItemDescription", item.description, "Item description should be initialized correctly");
    }

    @Test
    public void testItemID() {
        GameEngine.Item item = new GameEngine.Item("TestItemID", "TestItemDescription", null);
        Assertions.assertEquals("TestItemID", item.ID, "Item ID should be initialized correctly");
    }
}
