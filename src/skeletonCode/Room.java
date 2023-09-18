package skeletonCode;

import java.util.ArrayList;
import java.util.List;
public class Room {

    public Room() {
        List<NPC> npcs = new ArrayList<>();
    }
    public void moveToRoomLeft() {
        System.out.println("You move to the room on the left.");
    }

    public void moveToRoomRight() {
        System.out.println("You move to the room on the right.");
    }

    public void moveToRoomUp() {
        System.out.println("You move to the room above.");
    }

    public void moveToRoomDown() {
        System.out.println("You move to the room below.");
    }

    public void inspect() {
        System.out.println("You inspect the room.");
        System.out.println("NPCs in the room:");
        }

    public static void main(String[] args) {
        Room room = new Room();
        room.moveToRoomLeft();
        room.inspect();
    }

    public void addNPC(NPC alice) {
    }
}

