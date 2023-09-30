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
        player = new Player(100, 100, null);
        game = new Game(player, areas, items, testArea1);
    }
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

    @Test
    public void testSetCurrentArea() {
        game.setCurrentArea(testArea2);
        Assertions.assertEquals(testArea2, game.currentArea, "Current area should be set correctly");
    }

    @Test
    public void testPerformActionInspectEmptyBackpack() {
        // Set up the player and game
        Player player = new Player(100, 100, new HashMap<>());
        Game game = new Game(player, new ArrayList<>(), new ArrayList<>(), null);

        // Simulate the input of "inspect" and immediately exit
        String simulatedUserInput = "inspect\nexit\nexit\nexit\nexit\nexit\n";
        InputStream originalSystemIn = System.in;
        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inContent);

        // Capture the contents of System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Call the performAction method
        game.performAction();

        // Check if the output contains "Your backpack is empty." (This is the expected output in the inspectBackpack method)
        assertTrue(outContent.toString().contains("Your backpack is empty."));

        // Restore the original System.in and System.out
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testPerformActionTalkWithNPCs() {
        // Create dummy actions for initial and repeat interactions
        Action initialAction = new Action(null) {
            @Override
            public void doAction() {
                System.out.println("Initial interaction with NPC.");
            }
        };

        Action repeatAction = new Action(null) {
            @Override
            public void doAction() {
                System.out.println("Repeat interaction with NPC.");
            }
        };

        // Add an NPC to the current area with the dummy actions
        NPC testNPC = new NPC(100, 100, "Test NPC", initialAction, repeatAction);
        testArea1.NPCs.add(testNPC);

        // Mock user input for 'talk', choosing the first NPC twice, and then 'exit' to end the loop
        ByteArrayInputStream input = new ByteArrayInputStream("talk\n0\ntalk\n0\nexit\n".getBytes());
        System.setIn(input);

        // Capture the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        game.performAction();

        assertTrue(outContent.toString().contains("Initial interaction with NPC."), "The initial interaction message should be displayed");
        assertTrue(outContent.toString().contains("Repeat interaction with NPC."), "The repeat interaction message should be displayed");
    }

    @Test
    public void testPerformActionTalkNoNPCs() {
        // Mock user input for 'talk' and then 'exit' to end the loop
        ByteArrayInputStream input = new ByteArrayInputStream("talk\\nexit\\n".getBytes());
        System.setIn(input);

        // Capture the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        game.performAction();

        assertTrue(outContent.toString().contains("There are no NPCs to talk to in this area."), "No NPCs message should be displayed");
    }

    // Note: Testing 'exit' would actually exit the JVM, which would terminate the test execution.
    // So, we might want to avoid testing that in this context.

    @Test
    public void testPerformActionMove() {
        // Let's assume that the Game class has a method to get the current area name.
        // If not, you might need to adjust this test a bit.

        // Mock user input for 'move' and then 'exit' to end the loop
        ByteArrayInputStream input = new ByteArrayInputStream("move\nexit\n".getBytes());
        System.setIn(input);

        // Capture the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        game.performAction();

        // You would need some way to check if the current area has actually changed.
        // If there's a method in the Game class to get the current area, you can use it.
        // For now, let's just check if the 'moveArea()' method prints something indicative of a move.
        assertTrue(outContent.toString().contains("You have moved to"), "There should be an indication that the player has moved");
    }

    @Test
    public void testPerformActionInvalidCommand() {
        // Mock user input for an invalid command 'abc' and then 'exit' to end the loop
        ByteArrayInputStream input = new ByteArrayInputStream("abc\nexit\n".getBytes());
        System.setIn(input);

        // Capture the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        game.performAction();

        assertTrue(outContent.toString().contains("Invalid command. Please try again."), "There should be an invalid command message");
    }


}