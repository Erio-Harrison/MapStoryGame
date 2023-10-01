package GameEngine;

/**
 * Do action based on requirement
 */
public class Requirement extends Action {
    RequirementChecker check;
    Action satisfied;
    Action not_satisfied;
    boolean is_visible_if_not_satisfied;

    public Requirement(Game game, RequirementChecker check, Action satisfied, Action notSatisfied, boolean is_visible) {
        super(game);
        this.check = check;
        this.satisfied = satisfied;
        this.not_satisfied = notSatisfied;
        this.is_visible_if_not_satisfied = is_visible;
    }

    @Override
    public void doAction() {
        if (this.check.checkRequirement()) {
            this.satisfied.doAction();
        } else {
            this.not_satisfied.doAction();
        }
    }
}
