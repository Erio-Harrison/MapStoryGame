package GameEngine;
import java.util.*;  

/**
 * The Character class represents playable and non-playable characters in the
 * game. 
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
}
