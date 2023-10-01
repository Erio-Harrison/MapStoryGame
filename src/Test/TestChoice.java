import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import GameEngine.*;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

class TestChoice {
    private Game game;
    private Area testArea1;
    private Area testArea2;
    private Player player;


    @Test
    void choiceInitializationTest() {
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

        // Setup choices
        Map<String, Action> choices = new HashMap<>();
        choices.put("Do something", new Action(game) {
            @Override
            public void doAction() {
                // Do something
            }
        });

        // Execute
        Choice choice = new Choice(game, choices);

        // Verify
        assertNotNull(choice, "Choice object should not be null");
        assertNotNull(choice.choices, "Choices map should not be null");
        assertEquals(choices, choice.choices, "Choices map should be correctly set");
        assertEquals(game, choice.game, "Game should be correctly set");
    }
}