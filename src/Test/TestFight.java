import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import GameEngine.*;

import java.util.ArrayList;
import java.util.HashMap;

class TestFight{
    private Game game;
    private Area testArea1;
    private Area testArea2;
    private Player player;
    public class TestAction extends Action {
        public boolean isActionDone = false;

        public TestAction() {
            super(null);
        }

        @Override
        public void doAction() {
            isActionDone = true;
        }
    }

    @Test
    void fightDamageTest() {
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

        TestNPC.TestAction initialInteraction = new TestNPC.TestAction();
        TestNPC.TestAction repeatInteraction = new TestNPC.TestAction();
        TestNPC.TestAction deathInteraction = new TestNPC.TestAction();
        NPC npc = new NPC(100, 100, "Test NPC", initialInteraction, repeatInteraction, deathInteraction);


        // Creating Fight action to inflict 50 damage
        Action fightAction = new Fight(game, npc, 50);
        fightAction.doAction();

        assertEquals(50, npc.HP, "NPC health should be reduced to 50");
    }

    @Test
    void fightExcessDamageTest() {
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

        TestNPC.TestAction initialInteraction = new TestNPC.TestAction();
        TestNPC.TestAction repeatInteraction = new TestNPC.TestAction();
        TestNPC.TestAction deathInteraction = new TestNPC.TestAction();
        NPC npc = new NPC(100, 100, "Test NPC", initialInteraction, repeatInteraction, deathInteraction);

        // Creating Fight action to inflict 50 damage
        Action fightAction = new Fight(game, npc, 50);
        fightAction.doAction();

        assertEquals(50, npc.HP, "NPC health should be reduced to 50");
    }

    @Test
    void fightZeroHealthNPC() {
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

        TestNPC.TestAction initialInteraction = new TestNPC.TestAction();
        TestNPC.TestAction repeatInteraction = new TestNPC.TestAction();
        TestNPC.TestAction deathInteraction = new TestNPC.TestAction();
        NPC npc = new NPC(100, 100, "Test NPC", initialInteraction, repeatInteraction, deathInteraction);
        // Creating Fight action to inflict 50 damage
        Action fightAction = new Fight(game, npc, 40);
        fightAction.doAction();

        assertEquals(60, npc.HP, "NPC health should remain 60");
    }
}