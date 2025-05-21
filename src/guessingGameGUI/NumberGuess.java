package guessingGameGUI;

public class NumberGuess implements Guessable {

    private final int secret;
    private boolean guessed;

    public NumberGuess() {
        secret = SharedRandom.RANDOM.nextInt(10) + 1; // 1–10
        guessed = false;
    }

    @Override
    public String getPrompt() {
        return "Guess the number (1‑10):";
    }

    @Override
    public String guess(String input) {
        if (guessed) {
            return "Already guessed!";
        }
        int value;
        try {
            value = Integer.parseInt(input.trim());
        } catch (NumberFormatException ex) {
            return "Please enter a valid integer.";
        }
        if (value == secret) {
            guessed = true;
            return "Correct! The number was " + secret + ".";
        }
        return (value < secret) ? "Too low." : "Too high.";
    }

    @Override
    public boolean isGuessed() {
        return guessed;
    }
}
