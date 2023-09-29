
import GameEngine.GameJSONData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TestArea {
    @Test
    public void testAreaInitialization() {
        GameEngine.Action action = new GameEngine.Action(null) {
            @Override
            public void doAction() {
            }
        };
        GameEngine.Area area = new GameEngine.Area("Test Area", new ArrayList<>(), new HashMap<>(), action);
        Assertions.assertNotNull(area, "Area object should not be null");
    }
    @Test
    public void testRetrieveArea() {
        GameJSONData.Game gameJSON = new GameJSONData.Game();
        gameJSON.areaMap = new HashMap<>();
        gameJSON.areas = new ArrayList<>();

        // Create a real GameEngine.Area object with an anonymous Action
        GameEngine.Action action = new GameEngine.Action(null) {
            @Override
            public void doAction() {
            }
        };

        GameEngine.Area area = new GameEngine.Area("Test Area", new ArrayList<>(), new HashMap<>(), action);
        gameJSON.areaMap.put("testArea", area);

        // Execute the method to be tested
        GameEngine.Area retrievedArea = gameJSON.retrieveArea("testArea");

        // Assert the results
        Assertions.assertEquals(area, retrievedArea);
    }
    @Test
    public void testAreaNPCs() {
        ArrayList<GameEngine.NPC> npcs = new ArrayList<>();
        GameEngine.Action action = new GameEngine.Action(null) {
            @Override
            public void doAction() {
            }
        };
        GameEngine.Area area = new GameEngine.Area("Test Area", npcs, new HashMap<>(), action);
        Assertions.assertEquals(npcs, area.NPCs, "Area NPCs should be equal to assigned NPCs");
    }
    @Test
    public void testAreaItems() {
        HashMap<GameEngine.Item, Integer> items = new HashMap<>();
        GameEngine.Action action = new GameEngine.Action(null) {
            @Override
            public void doAction() {
            }
        };
        GameEngine.Area area = new GameEngine.Area("Test Area", new ArrayList<>(), items, action);
        Assertions.assertEquals(items, area.Items, "Area items should be equal to assigned items");
    }
    @Test
    public void testAreaActionExecution() {
        boolean[] actionExecuted = {false};
        GameEngine.Action action = new GameEngine.Action(null) {
            @Override
            public void doAction() {
                actionExecuted[0] = true;
            }
        };
        GameEngine.Area area = new GameEngine.Area("Test Area", new ArrayList<>(), new HashMap<>(), action);
        area.enterArea();
        Assertions.assertTrue(actionExecuted[0], "Area action should be executed when entering the area");
    }
}
