import GameEngine.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAddToArea {
    Game testGame;
    Area testArea;
    Item sword;
    Item shield;
    Player dummyPlayer;

    @BeforeEach
    public void setUp() {
        sword = new Item("001", "A sharp sword", null); // assuming no action for these items in this context
        shield = new Item("002", "A sturdy shield", null);

        ArrayList<NPC> dummyNPCs = new ArrayList<>(); // no NPCs for this test
        Map<Item, Integer> initialItems = new HashMap<>(); // initially empty items map
        testArea = new Area("Test Area", dummyNPCs, initialItems, null); // assuming no action in this context

        ArrayList<Area> gameAreas = new ArrayList<>();
        gameAreas.add(testArea);

        ArrayList<Item> gameItems = new ArrayList<>();
        gameItems.add(sword);
        gameItems.add(shield);

        Map<Item, Integer> playerBackpack = new HashMap<>();
        Action dummyAction = null; // no specific action in this context
        dummyPlayer = new Player(100, 100, playerBackpack, dummyAction);

        testGame = new Game(dummyPlayer, gameAreas, gameItems, testArea);
    }

    @Test
    public void testDoAction_ItemNotInArea() {
        Map<Item, Integer> itemsToAdd = new HashMap<>();
        itemsToAdd.put(sword, 2);
        AddToArea action = new AddToArea(testGame, itemsToAdd, testArea);

        action.doAction();

        assertEquals(Integer.valueOf(2), testArea.Items.get(sword));
    }

    @Test
    public void testDoAction_ItemAlreadyInArea() {
        Map<Item, Integer> itemsToAdd = new HashMap<>();
        itemsToAdd.put(sword, 2);
        AddToArea action = new AddToArea(testGame, itemsToAdd, testArea);
        testArea.Items.put(sword, 3);  // Assuming sword is already in the area

        action.doAction();

        assertEquals(Integer.valueOf(5), testArea.Items.get(sword));  // 3 + 2 = 5
    }

    @Test
    public void testDoAction_MultipleItems() {
        Map<Item, Integer> itemsToAdd = new HashMap<>();
        itemsToAdd.put(sword, 2);
        itemsToAdd.put(shield, 3);
        AddToArea action = new AddToArea(testGame, itemsToAdd, testArea);
        testArea.Items.put(sword, 3);  // Assuming sword is already in the area

        action.doAction();

        assertEquals(Integer.valueOf(5), testArea.Items.get(sword));  // 3 + 2 = 5
        assertEquals(Integer.valueOf(3), testArea.Items.get(shield));
    }
}
