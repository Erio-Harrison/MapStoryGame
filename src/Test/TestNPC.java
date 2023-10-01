import GameEngine.Action;
import GameEngine.NPC;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestNPC {

    public static class TestAction extends Action {
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
    public void testNPCInitialization() {
        TestAction initialInteraction = new TestAction();
        TestAction repeatInteraction = new TestAction();
        TestAction deathInteraction = new TestAction();
        NPC npc = new NPC(100, 100, "Test NPC", initialInteraction, repeatInteraction, deathInteraction);

        Assertions.assertNotNull(npc, "NPC object should not be null");
        Assertions.assertEquals("Test NPC", npc.name, "NPC name should be initialized correctly");
        Assertions.assertFalse(npc.hasSpokenTo, "NPC should not have spoken to anyone initially");
    }

    @Test
    public void testNPCInteractions() {
        TestAction initialInteraction = new TestAction();
        TestAction repeatInteraction = new TestAction();
        TestAction deathInteraction = new TestAction();
        NPC npc = new NPC(100, 100, "Test NPC", initialInteraction, repeatInteraction, deathInteraction);

        // Interact for the first time
        npc.interact();
        Assertions.assertTrue(initialInteraction.isActionDone, "initialInteraction should be invoked");
        Assertions.assertFalse(repeatInteraction.isActionDone, "repeatInteraction should not be invoked");
        Assertions.assertTrue(npc.hasSpokenTo, "NPC should have spoken after interaction");

        // Reset action states
        initialInteraction.isActionDone = false;
        repeatInteraction.isActionDone = false;

        // Interact again
        npc.interact();
        Assertions.assertFalse(initialInteraction.isActionDone, "initialInteraction should not be invoked");
        Assertions.assertTrue(repeatInteraction.isActionDone, "repeatInteraction should be invoked this time");
    }

    @Test
    public void testNPCHealth() {
        NPC npc = new NPC( 50, 100, "TestNPC", null, null, null);
        Assertions.assertEquals(50, npc.HP, "NPC health should be initialized correctly");
    }

    @Test
    public void testNPCMaxHealth() {
        NPC npc = new NPC( 50, 100, "TestNPC", null, null, null);
        Assertions.assertEquals(100, npc.MaxHP, "NPC max health should be initialized correctly");
    }
}