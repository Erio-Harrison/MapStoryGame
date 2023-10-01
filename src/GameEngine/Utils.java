package GameEngine;

public class Utils {
    public static String surroundWithLines(String input) {
        int inputLength = input.length();
        StringBuilder result = new StringBuilder();

        // Create the top border of the box
        result.append("+" + "-".repeat(inputLength + 2) + "+\n");

        // Add the string inside the box with vertical bars
        result.append("| " + input + " |\n");

        // Create the bottom border of the box
        result.append("+" + "-".repeat(inputLength + 2) + "+");

        return result.toString();
    }
}
