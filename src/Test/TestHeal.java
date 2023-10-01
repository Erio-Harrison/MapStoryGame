import GameEngine.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;

class TestHeal {

    @Test
    void healUnderMaxHPTest() {
        // Initialize the required objects and actions
        Action dummyAction = new Action(null) {
            @Override
            public void doAction() {
                // Dummy action
            }
        };

        Area testArea1 = new Area("Test Area 1", new ArrayList<>(), new HashMap<>(), dummyAction);
        ArrayList<Area> areas = new ArrayList<>();
        areas.add(testArea1);
        ArrayList<Item> items = new ArrayList<>();
        Player player = new Player(80, 100, null, null);
        Game game = new Game(player, areas, items, testArea1);

        // Create Heal Action to heal 10 HP
        Action healAction = new Heal(game, 10);
        healAction.doAction();

        assertEquals(90, player.HP, "Player health should be increased to 90");
    }

    @Test
    void healOverMaxHPTest() {
        // Initialize the required objects and actions
        Action dummyAction = new Action(null) {
            @Override
            public void doAction() {
                // Dummy action
            }
        };

        Area testArea1 = new Area("Test Area 1", new ArrayList<>(), new HashMap<>(), dummyAction);
        ArrayList<Area> areas = new ArrayList<>();
        areas.add(testArea1);
        ArrayList<Item> items = new ArrayList<>();
        Player player = new Player(95, 100, null, null);
        Game game = new Game(player, areas, items, testArea1);

        // Create Heal Action to heal 10 HP
        Action healAction = new Heal(game, 10);
        healAction.doAction();

        assertEquals(100, player.HP, "Player health should be increased to MaxHP (100)");
    }

    @Test
    void healAtMaxHPTest() {
        // Initialize the required objects and actions
        Action dummyAction = new Action(null) {
            @Override
            public void doAction() {
                // Dummy action
            }
        };

        Area testArea1 = new Area("Test Area 1", new ArrayList<>(), new HashMap<>(), dummyAction);
        ArrayList<Area> areas = new ArrayList<>();
        areas.add(testArea1);
        ArrayList<Item> items = new ArrayList<>();
        Player player = new Player(100, 100, null, null);
        Game game = new Game(player, areas, items, testArea1);

        // Create Heal Action to heal 10 HP
        Action healAction = new Heal(game, 10);
        healAction.doAction();

        assertEquals(100, player.HP, "Player health should remain at MaxHP (100)");
    }
}