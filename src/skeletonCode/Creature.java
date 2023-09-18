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

    public static void main(String[] args) {
        Creature creature = new Creature(100);
        creature.lookAround();
        creature.move();
        creature.attack();
        creature.talk();
    }
}
