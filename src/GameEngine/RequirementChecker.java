package GameEngine;

/**
 * Implement type of requirements
 */
public abstract class RequirementChecker {
    /**
     * Requirements need to check game state to see if requirement satisfied
     */
    Game game;

    /**
     * Check if requirement satisfied
     */
    abstract Boolean checkRequirement();
}
