import GameEngine.Item;
import GameEngine.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class TestPlayer {
    private Player player;
    private Item testItem1;
    private Item testItem2;

    @BeforeEach
    public void setup() {
        testItem1 = new Item("Item1ID", "Item1Description", null);
        testItem2 = new Item("Item2ID", "Item2Description", null);
        Map<Item, Integer> backpack = new HashMap<>();
        backpack.put(testItem1, 2);
        backpack.put(testItem2, 3);
        player = new Player(100, 100, backpack);
    }
    @Test
    public void testPlayerInitialization() {
        Map<GameEngine.Item, Integer> backpack = new HashMap<>();
        GameEngine.Player player = new GameEngine.Player(100, 100, backpack);
        Assertions.assertNotNull(player, "Player object should not be null");
    }

    @Test
    public void testPlayerBackpackItems() {
        Map<GameEngine.Item, Integer> backpack = new HashMap<>();
        GameEngine.Item item = new GameEngine.Item("id", "description", null);
        backpack.put(item, 1);
        GameEngine.Player player = new GameEngine.Player(100, 100, backpack);
        Assertions.assertEquals(backpack, player.backpack, "Player backpack items should be equal to assigned items");
    }

    @Test
    public void testPlayerHPAndMaxHP() {
        int HP = 80;
        int MaxHP = 100;
        Map<GameEngine.Item, Integer> backpack = new HashMap<>();
        GameEngine.Player player = new GameEngine.Player(HP, MaxHP, backpack);
        Assertions.assertEquals(HP, player.HP, "Player HP should be equal to assigned HP");
        Assertions.assertEquals(MaxHP, player.MaxHP, "Player MaxHP should be equal to assigned MaxHP");
    }

    /*
    @Test
    public void testInspectBackpack() {
        // Capture the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method
        player.inspectBackpack();

        // Reset the standard output
        System.setOut(System.out);

        String expectedOutput = "Item: " + testItem1.ID + ", Quantity: 2\\n" +
                "Item: " + testItem2.ID + ", Quantity: 3\\n";
        Assertions.assertEquals(expectedOutput, outContent.toString(), "Inspect backpack should list all items correctly");
    }
     */
}