import GameEngine.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestAttackNPC {

    @Test
    public void testAttackNPCCreation() {
        // Arrange
        Map<Item, Integer> backpack = new HashMap<>();
        Action onDeath = new Action(new Game(null, null, null, null)) {
            @Override
            public void doAction() {
            }
        };
        Player player = new Player(100, 100, backpack, onDeath);

        ArrayList<Area> areas = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        Area startingArea = new Area("Starting Area", new ArrayList<>(), new HashMap<>(), new Action(new Game(player, areas, items, null)) {
            @Override
            public void doAction() {
            }
        });

        Game game = new Game(player, areas, items, startingArea);
        NPC npc = new NPC(100, 100, "TestNPC", new Action(game) {
            @Override
            public void doAction() {
            }
        }, new Action(game) {
            @Override
            public void doAction() {
            }
        }, new Action(game) {
            @Override
            public void doAction() {
            }
        });

        int damage = 20;

        // Act
        Action attackNpcAction = new AttackNPC(game, npc, damage);

        // Assert
        Assertions.assertNotNull(attackNpcAction, "AttackNPC action should be created successfully");
    }

    @Test
    public void testDoActionReducesNpcHp() {
        // Arrange
        Map<Item, Integer> backpack = new HashMap<>();
        Action onDeath = new Action(new Game(null, null, null, null)) {
            @Override
            public void doAction() {
            }
        };
        Player player = new Player(100, 100, backpack, onDeath);

        ArrayList<Area> areas = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        Area startingArea = new Area("Starting Area", new ArrayList<>(), new HashMap<>(), new Action(new Game(player, areas, items, null)) {
            @Override
            public void doAction() {
            }
        });

        Game game = new Game(player, areas, items, startingArea);
        int initialHp = 100;
        int damage = 20;

        NPC npc = new NPC(initialHp, 100, "TestNPC", new Action(game) {
            @Override
            public void doAction() {
            }
        }, new Action(game) {
            @Override
            public void doAction() {
            }
        }, new Action(game) {
            @Override
            public void doAction() {
            }
        });

        Action attackNpcAction = new AttackNPC(game, npc, damage);

        // Act
        attackNpcAction.doAction();

        // Assert
        Assertions.assertEquals(initialHp - damage, npc.HP, "NPC HP should be reduced by the damage amount");
        Assertions.assertTrue(npc.isAlive, "NPC should be alive after the attack");
    }

    @Test
    public void testDoActionKillsNpcWhenHpIsZero() {
        // Arrange
        Map<Item, Integer> backpack = new HashMap<>();
        Action onDeath = new Action(new Game(null, null, null, null)) {
            @Override
            public void doAction() {
            }
        };
        Player player = new Player(100, 100, backpack, onDeath);

        ArrayList<Area> areas = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        Area startingArea = new Area("Starting Area", new ArrayList<>(), new HashMap<>(), new Action(new Game(player, areas, items, null)) {
            @Override
            public void doAction() {
            }
        });

        Game game = new Game(player, areas, items, startingArea);
        int initialHp = 20;
        int damage = 20;

        NPC npc = new NPC(initialHp, 100, "TestNPC", new Action(game) {
            @Override
            public void doAction() {
            }
        }, new Action(game) {
            @Override
            public void doAction() {
            }
        }, new Action(game) {
            @Override
            public void doAction() {
            }
        });

        Action attackNpcAction = new AttackNPC(game, npc, damage);

        // Act
        attackNpcAction.doAction();

        // Assert
        Assertions.assertEquals(0, npc.HP, "NPC HP should be 0 after the attack");
        Assertions.assertFalse(npc.isAlive, "NPC should be dead after the attack");
    }


}