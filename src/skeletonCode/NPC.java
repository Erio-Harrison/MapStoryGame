package skeletonCode;

public class NPC {
    private static String name;

    public NPC(String name) {
        this.name = name;
    }

    public void talk(String message) {
        System.out.println(name + " says: " + message);
    }

    public static void main(String[] args) {
        NPC npc1 = new NPC("Bob");
        NPC npc2 = new NPC("Alice");

        npc1.talk("Hello there!");
        npc2.talk("Hi, how can I help you?");
    }
        public static String getName() {
            return name;
        }
    }

