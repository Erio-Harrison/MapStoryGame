import GameEngine.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;

class TestHurt {


    @Test
    void hurtAboveZeroHPTest1() {
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

        // Create Hurt Action to reduce 30 HP
        Action hurtAction = new Hurt(game, 30);
        hurtAction.doAction();

        assertEquals(50, player.HP, "Player health should be reduced to 50");
    }

    @Test
    void hurtAboveZeroHPTest2() {
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

        // Create Hurt Action to reduce 30 HP
        Action hurtAction = new Hurt(game, 30);
        hurtAction.doAction();

        assertEquals(70, player.HP, "Player health should be reduced to 70");
    }

}