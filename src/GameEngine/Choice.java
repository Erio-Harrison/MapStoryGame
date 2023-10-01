package GameEngine;

import java.util.ArrayList;
import java.util.Map;

/**
 * The Choice class extends the Action class and represents a mechanism
 * for presenting multiple gameplay choices to the user and executing
 * the corresponding action based on user input. It facilitates user
 * interaction with the game, allowing the player to make decisions
 * that affect the game state and progress.
 */
public class Choice extends Action {
    Map<String, Action> choices;
    /**
     * Constructs a new Choice action instance associated with the specified game
     * and a set of possible actions represented by a map of strings and corresponding actions.
     *
     * @param game    The Game instance to which this action belongs.
     * @param choices A Map containing string representations and corresponding Action instances representing the choices available to the user.
     */
    public Choice(Game game, Map<String, Action> choices) {
        super(game);
        this.choices = choices;
    }
    /**
     * Executes the Choice action. It displays a list of available choices to the user
     * and prompts the user to make a selection. The method then executes the corresponding
     * action based on the user's choice and handles invalid inputs and special commands
     * such as opening the backpack or exiting the game.
     */
    @Override
    public void doAction() {
        int count = 0;

        ArrayList<String> choice_strings = new ArrayList<>();

        for (String s : this.choices.keySet()) {
            Action a = this.choices.get(s);

            if (a instanceof Requirement) {
                if (((Requirement) a).is_visible_if_not_satisfied) {
                    choice_strings.add(s);
                } else {
                    if (((Requirement) a).check.checkRequirement()) {
                        choice_strings.add(s);
                    }
                }
            } else {
                choice_strings.add(s);
            }
        }


        do {
            count = 0;
            for (String s : choice_strings) {
                count++;
                System.out.println("[" + count + "] " + s);
            }
            this.game.player.printPlayerInfo();

            System.out.print("Select choice (enter a number between 1 and " + (count) + ", 'b' to open backpack or 'e' to exit game): ");
            String choice = this.game.scanner.nextLine();

            // handle choosing backpack and exiting
            if (choice.equals("b")) {
                this.game.player.inspectBackpack();
                continue;
            } else if (choice.equals("e")) {
                System.out.println("Exiting the game. Thanks for playing!");
                System.exit(0);
            }

            // input validation
            try {
                int n_choice = Integer.parseInt(choice) - 1;
                if (n_choice >= 0 && n_choice < count) {
                    this.choices.get(choice_strings.get(n_choice)).doAction();
                    return;
                } else {
                    System.out.println("Invalid choice, please enter a number between 1 and " + (count));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a valid number, 'b' to inspect backpack, or 'e' to exit game");
            }
        } while (true);
    }
}
