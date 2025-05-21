package guessingGameGUI;

import java.util.List;

public class ColorGuess implements Guessable {

    private static final List<String> COLORS = List.of(
            "red", "blue", "green", "yellow", "orange", "purple"
    );

    private final String secret;
    private boolean guessed;

    public ColorGuess() {
        secret = COLORS.get(SharedRandom.RANDOM.nextInt(COLORS.size()));
        guessed = false;
    }

    @Override
    public String getPrompt() {
        return "Guess the color:";
    }

    @Override
    public String guess(String input) {
        input = input.toLowerCase();
        if (guessed) {
            return "Already guessed!";
        }
        int cmp = input.compareTo(secret);
        if (cmp == 0) {
            guessed = true;
            return "Correct! The color was " + secret + ".";
        }
        return (cmp < 0)
//                ? "The color comes alphabetically after "
                ? "The color comes alphabetically after " + input + "."
                : "The color comes alphabetically before " + input + ".";
    }

    @Override
    public boolean isGuessed() {
        return guessed;
    }
}
