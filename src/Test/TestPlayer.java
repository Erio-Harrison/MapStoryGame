import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestPlayer {

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
}