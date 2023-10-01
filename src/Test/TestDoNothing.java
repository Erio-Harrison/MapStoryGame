import GameEngine.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestDoNothing {

    Game testGame;
    Area forestArea;
    Player dummyPlayer;

    @BeforeEach
    public void setUp() {
        Map<Item, Integer> initialItems = new HashMap<>();
        forestArea = new Area("Forest", new ArrayList<>(), initialItems, null);

        ArrayList<Area> gameAreas = new ArrayList<>();
        gameAreas.add(forestArea);

        dummyPlayer = new Player(100, 100, new HashMap<>(), null);

        testGame = new Game(dummyPlayer, gameAreas, new ArrayList<>(), forestArea);
    }

    @Test
    public void testDoAction_NoEffectOnGame() {
        Area initialArea = testGame.currentArea;
        int initialHealth = dummyPlayer.HP; // Assuming a getHealth() method exists in Player class

        DoNothing action = new DoNothing(testGame);
        action.doAction();

        assertEquals(initialArea, testGame.currentArea);
        assertEquals(initialHealth, dummyPlayer.HP);
    }
}
