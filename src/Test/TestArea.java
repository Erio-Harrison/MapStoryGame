
import GameEngine.GameJSONData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

    @Test
    public void testAreaName() {
        GameEngine.Action dummyAction = new GameEngine.Action(null) {
            @Override
            public void doAction() {
            }
        };
        GameEngine.Area area = new GameEngine.Area("Test Area", new ArrayList<>(), new HashMap<>(), dummyAction);
        Assertions.assertEquals("Test Area", area.name, "Area name should be initialized correctly");
    }

    @Test
    public void testEnterAreaMessage() {
        GameEngine.Action dummyAction = new GameEngine.Action(null) {
            @Override
            public void doAction() {
                // Dummy action
            }
        };
        GameEngine.Area area = new GameEngine.Area("Test Area", new ArrayList<>(), new HashMap<>(), dummyAction);

        // Capture the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method
        area.enterArea();

        // Reset the standard output
        System.setOut(System.out);

        String expectedMessage = "You enter the " + area.name;
        Assertions.assertEquals(expectedMessage.trim(), outContent.toString().trim(), "Enter area message should be correct");
    }
}
