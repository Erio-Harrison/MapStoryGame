import GameEngine.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAddToBackpack {

    Game testGame;
    Area testArea;
    Item sword;
    Item shield;
    Player dummyPlayer;

    @BeforeEach
    public void setUp() {
        sword = new Item("001", "A sharp sword", null);
        shield = new Item("002", "A sturdy shield", null);

        Map<Item, Integer> initialItems = new HashMap<>();
        testArea = new Area("Test Area", new ArrayList<>(), initialItems, null);

        ArrayList<Area> gameAreas = new ArrayList<>();
        gameAreas.add(testArea);

        ArrayList<Item> gameItems = new ArrayList<>();
        gameItems.add(sword);
        gameItems.add(shield);

        Map<Item, Integer> playerBackpack = new HashMap<>();
        dummyPlayer = new Player(100, 100, playerBackpack, null);

        testGame = new Game(dummyPlayer, gameAreas, gameItems, testArea);
    }

    @Test
    public void testDoAction_ItemNotInBackpack() {
        Map<Item, Integer> itemsToAdd = new HashMap<>();
        itemsToAdd.put(sword, 2);
        AddToBackpack action = new AddToBackpack(testGame, itemsToAdd);

        action.doAction();

        assertEquals(Integer.valueOf(2), testGame.player.backpack.get(sword));
    }

    @Test
    public void testDoAction_ItemAlreadyInBackpack() {
        Map<Item, Integer> itemsToAdd = new HashMap<>();
        itemsToAdd.put(sword, 2);
        AddToBackpack action = new AddToBackpack(testGame, itemsToAdd);
        testGame.player.backpack.put(sword, 3);  // Assuming sword is already in the backpack

        action.doAction();

        assertEquals(Integer.valueOf(5), testGame.player.backpack.get(sword));  // 3 + 2 = 5
    }

    @Test
    public void testDoAction_MultipleItems() {
        Map<Item, Integer> itemsToAdd = new HashMap<>();
        itemsToAdd.put(sword, 2);
        itemsToAdd.put(shield, 3);
        AddToBackpack action = new AddToBackpack(testGame, itemsToAdd);
        testGame.player.backpack.put(sword, 3);  // Assuming sword is already in the backpack

        action.doAction();

        assertEquals(Integer.valueOf(5), testGame.player.backpack.get(sword));  // 3 + 2 = 5
        assertEquals(Integer.valueOf(3), testGame.player.backpack.get(shield));
    }
}
