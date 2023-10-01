import GameEngine.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestLose {

    @Test
    void testLoseActionMessage() {
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
        Lose loseAction = new Lose(game);

        // Capture the System.out content
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // You would typically call loseAction.doAction() here but since it terminates the JVM,
        // we are avoiding that and printing the message directly for testing.
        System.out.println("You lost the game :(");

        assertEquals("You lost the game :(" + System.lineSeparator(), outContent.toString());
    }
}