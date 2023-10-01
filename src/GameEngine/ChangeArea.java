package GameEngine;

/**
 * Author: Weiyuan
 * Represents an action that changes the player's current location within the game world.
 * This class manages player movement between different areas or scenes.
 * <p>Usage example:
 * <pre>
 *    Action changeAreaAction = new ChangeArea(gameInstance, forestArea, castleArea);
 *    changeAreaAction.doAction();  // Moves the player from the forest to the castle.
 * </pre>
 * </p>
 */
public class ChangeArea extends Action {
    private Area targetArea;

    /**
     * Constructs a new {@code ChangeArea} action.
     *
     * @param game       the current game instance
     * @param targetArea the area the player will move to
     */
    public ChangeArea(Game game, Area targetArea) {
        super(game);
        this.targetArea = targetArea;
    }

    /**
     * Executes the action, transitioning the player from their current area to the target area.
     */
    @Override
    public void doAction() {
        game.setCurrentArea(targetArea);
    }
}
