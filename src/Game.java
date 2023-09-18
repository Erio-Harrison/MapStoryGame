import Room;

public class Game {
    private Room startingRoom;

    public Game() {
        // Default constructor
    }

    public Game(Room startingRoom) {
        this.startingRoom = startingRoom;
    }

    public void init(String config) {
        // Initialize the game based on the provided configuration
        System.out.println("Game initialized with config: " + config);
    }

    public static void main(String[] args) {
        Room initialRoom = new Room();
        initialRoom.addNPC(new NPC("Bob"));
        initialRoom.addNPC(new NPC("Alice"));

        Game game = new Game(initialRoom);
        game.init("DefaultConfig");
    }
}
