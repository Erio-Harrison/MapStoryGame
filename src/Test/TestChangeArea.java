import GameEngine.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestChangeArea {

    Game testGame;
    Area forestArea;
    Area castleArea;
    Player dummyPlayer;

    @BeforeEach
    public void setUp() {
        Map<Item, Integer> initialItems = new HashMap<>();

        forestArea = new Area("Forest", new ArrayList<>(), initialItems, null);
        castleArea = new Area("Castle", new ArrayList<>(), initialItems, null);

        ArrayList<Area> gameAreas = new ArrayList<>();
        gameAreas.add(forestArea);
        gameAreas.add(castleArea);

        dummyPlayer = new Player(100, 100, new HashMap<>(), null);

        testGame = new Game(dummyPlayer, gameAreas, new ArrayList<>(), forestArea);
    }

    @Test
    public void testDoAction_ChangeArea() {
        assertEquals(forestArea, testGame.currentArea);

        ChangeArea action = new ChangeArea(testGame, castleArea);
        action.doAction();

        assertEquals(castleArea, testGame.currentArea);
    }

    @Test
    public void testDoAction_SameArea() {
        assertEquals(forestArea, testGame.currentArea);

        ChangeArea action = new ChangeArea(testGame, forestArea);
        action.doAction();

        assertEquals(forestArea, testGame.currentArea);
    }
}
