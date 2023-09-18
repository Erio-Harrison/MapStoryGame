package skeletonCode;

public class Game {

    public Game() {
        // Default constructor
    }

    public Game(Room startingRoom) {
    }

    public void init(String config) {
        // Initialize the game based on the provided configuration
        System.out.println("assignment3.Game initialized with config: " + config);
    }

    public static void main(String[] args) {
        Room initialRoom = new Room();
        initialRoom.addNPC(new NPC("Bob"));
        initialRoom.addNPC(new NPC("Alice"));

        Game game = new Game(initialRoom);
        game.init("DefaultConfig");
    }
}
