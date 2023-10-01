package GameEngine;

/**
 * The Requirement class extends Action to provide a way to execute conditional
 * actions based on a specific requirement within a Game instance. It uses an
 * instance of RequirementChecker to evaluate the requirement and decides which
 * Action to execute.
 */
public class Requirement extends Action {
    RequirementChecker check;
    Action satisfied;
    Action not_satisfied;
    boolean is_visible_if_not_satisfied;

    /**
     * Constructs a new Requirement instance with the specified game, check, satisfied action,
     * not satisfied action, and visibility flag.
     *
     * @param game     The Game instance to which this requirement belongs.
     * @param check    The RequirementChecker instance used to evaluate if the requirement is met.
     * @param satisfied The Action instance to be executed when the requirement is satisfied.
     * @param notSatisfied The Action instance to be executed when the requirement is not satisfied.
     * @param is_visible  A boolean indicating whether the requirement is visible if not satisfied.
     */
    public Requirement(Game game, RequirementChecker check, Action satisfied, Action notSatisfied, boolean is_visible) {
        super(game);
        this.check = check;
        this.satisfied = satisfied;
        this.not_satisfied = notSatisfied;
        this.is_visible_if_not_satisfied = is_visible;
    }
    /**
     * Executes the appropriate action depending on whether the requirement,
     * as evaluated by the RequirementChecker instance, is satisfied or not.
     */
    @Override
    public void doAction() {
        if (this.check.checkRequirement()) {
            this.satisfied.doAction();
        } else {
            this.not_satisfied.doAction();
        }
    }
}
