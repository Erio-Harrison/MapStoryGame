package GameEngine;

import java.util.ArrayList;
import java.util.Map;

/**
 * User choice
 */
public class Choice extends Action {
    Map<String, Action> choices;

    public Choice(Game game, Map<String, Action> choices) {
        super(game);
        this.choices = choices;
    }

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
