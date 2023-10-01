package GameEngine;
import java.util.*;

/**
 * Represents a Character within the game.
 * The Character class serves as a base class to represent both playable and non-playable characters in the game.
 * It holds attributes common to all characters, such as Health Points (HP) and Maximum HP (MaxHP).
 */
public abstract class Character {
    /**
     * The current HP of the character. 
     * Zero = death
     */
    public int HP;

    /**
     * The max HP of the character.
     */
    public int MaxHP;

    /**
     * Constructs a new Character with specified HP and MaxHP.
     *
     * @param HP the initial health points of the character
     * @param MaxHP the maximum health points the character can have
     */
    public Character(int HP, int MaxHP) {
        this.HP = HP;
        this.MaxHP = MaxHP;
    }

}
