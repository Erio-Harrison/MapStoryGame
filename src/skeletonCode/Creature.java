package skeletonCode;

public class Creature {
    private int HP;

    public Creature(int HP) {
        this.HP = HP;
    }

    public void lookAround() {
        System.out.println("The creature looks around.");
    }

    public void move() {
        System.out.println("The creature moves.");
    }

    public void attack() {
        System.out.println("The creature attacks.");
    }

    public void talk() {
        System.out.println("The creature talks.");
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
    public void setCurrentRoom(Room room) {
    }

    public static void main(String[] args) {
        Creature creature = new Creature(100);

        // Create a Room object and set it as the current room for the creature
        Room room = new Room("Living Room");
        creature.setCurrentRoom(room);

        creature.lookAround();
        creature.move();
        creature.attack();
        creature.talk();
    }
}
