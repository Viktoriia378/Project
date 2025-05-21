package guessingGameGUI;

import java.util.List;

public class AnimalGuess implements Guessable {

    private static final List<String> ANIMALS = List.of(
            "dog", "cat", "elephant", "lion", "tiger"
    );

    private final String secret;
    private boolean guessed;

    public AnimalGuess() {
        secret = ANIMALS.get(SharedRandom.RANDOM.nextInt(ANIMALS.size()));
        guessed = false;
    }

    @Override
    public String getPrompt() {
        return "Guess the animal:";
    }

    @Override
    public String guess(String input) {
        input = input.toLowerCase();
        if (guessed) {
            return "Already guessed!";
        }
        if (input.equals(secret)) {
            guessed = true;
            return "Correct! The animal was " + secret + ".";
        }
        return "Incorrect, try again.";
    }

    @Override
    public boolean isGuessed() {
        return guessed;
    }
}