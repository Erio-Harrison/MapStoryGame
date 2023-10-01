import GameEngine.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TestNPCAliveCheck {
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
    public void testNPCAliveCheckWhenAlive() {
        // Setup TestAction for NPC initialization.
        TestNPC.TestAction initialInteraction = new TestNPC.TestAction();
        TestNPC.TestAction repeatInteraction = new TestNPC.TestAction();
        TestNPC.TestAction deathInteraction = new TestNPC.TestAction();


        // Setup NPC with 100 HP.
        NPC npc = new NPC(100, 100, "Test NPC", initialInteraction, repeatInteraction, deathInteraction);
        NPCAliveCheck npcAliveCheck = new NPCAliveCheck(game, npc);

        // Check if NPC is alive.
        Assertions.assertTrue(npcAliveCheck.checkRequirement(), "NPC should be alive as it has positive HP");
    }

    @Test
    public void testNPCAliveCheckWhenDead() {
        // Setup TestAction for NPC initialization.
        TestNPC.TestAction initialInteraction = new TestNPC.TestAction();
        TestNPC.TestAction repeatInteraction = new TestNPC.TestAction();
        TestNPC.TestAction deathInteraction = new TestNPC.TestAction();

        // Setup NPC with 0 HP, representing a dead NPC.
        NPC npc = new NPC(100, 100, "Test NPC", initialInteraction, repeatInteraction, deathInteraction);
        NPCAliveCheck npcAliveCheck = new NPCAliveCheck(game, npc);
        npc.attack_npc(100);
        // Check if NPC is dead.
        Assertions.assertFalse(npcAliveCheck.checkRequirement(), "NPC should be dead as it has 0 HP");
    }

    @Test
    public void testNPCAliveCheckNotNull() {
        // Setup TestAction for NPC initialization.
        TestNPC.TestAction initialInteraction = new TestNPC.TestAction();
        TestNPC.TestAction repeatInteraction = new TestNPC.TestAction();
        TestNPC.TestAction deathInteraction = new TestNPC.TestAction();

        // Setup NPC and NPCAliveCheck instance.
        NPC npc = new NPC(100, 100, "Test NPC", initialInteraction, repeatInteraction, deathInteraction);
        NPCAliveCheck npcAliveCheck = new NPCAliveCheck(game, npc);

        // Validate NPCAliveCheck instance is not null.
        Assertions.assertNotNull(npcAliveCheck, "NPCAliveCheck instance should not be null");
    }
}