import GameEngine.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGame {

    private Game game;
    private Area testArea1;
    private Area testArea2;
    private Player player;

    @BeforeEach
    public void setup() {
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
    }
    @Test
    public void testGameInitialization() {
        // Arrange
        Player player = new Player(100, 200, new HashMap<>(), null); // Initialize as necessary
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

    @Test
    public void testSetCurrentArea() {
        game.setCurrentArea(testArea2);
        Assertions.assertEquals(testArea2, game.currentArea, "Current area should be set correctly");
    }

}