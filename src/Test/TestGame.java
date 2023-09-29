import GameEngine.Game;
import GameEngine.Area;
import GameEngine.Player;
import GameEngine.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

public class TestGame {

    @Test
    public void testGameInitialization() {
        // Arrange
        Player player = new Player(100, 200, new HashMap<>()); // Initialize as necessary
        ArrayList<Area> areas = new ArrayList<>();
        Area startingArea = new Area("Starting Area", new ArrayList<>(), new HashMap<>(), null); // Initialize as necessary
        areas.add(startingArea);
        ArrayList<Item> items = new ArrayList<>();

        // Act
        Game game = new Game(player, areas, items, startingArea);

        // Assert
        Assertions.assertNotNull(game, "Game should be initialized");
        Assertions.assertEquals(player, game.getPlayer(), "Player should be initialized correctly");
        Assertions.assertEquals(startingArea, game.currentArea, "CurrentArea should be initialized correctly");
        Assertions.assertEquals(areas, game.areas, "Areas should be initialized correctly");
        Assertions.assertEquals(items, game.items, "Items should be initialized correctly");
    }
}