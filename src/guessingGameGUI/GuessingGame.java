package guessingGameGUI;

import java.util.List;

/**
 * Game engine: keeps track of challenges and progress.
 */
public class GuessingGame {

    private final List<Guessable> challenges = List.of(
            new ColorGuess(),
            new NumberGuess(),
            new AnimalGuess()
    );
    private int currentIndex = 0;

    /**
     * @return prompt for current challenge
     */
    public String getCurrentPrompt() {
        return challenges.get(currentIndex).getPrompt();
    }

    /**
     * Processes player input and returns feedback.
     * Automatically progresses to next challenge when current is solved.
     */
    public String processInput(String input) {
        input = input.trim().toLowerCase();
        Guessable current = challenges.get(currentIndex);
        String feedback = current.guess(input);
        if (current.isGuessed()) {
            currentIndex++;
            if (isFinished()) {
                feedback += "\nCongratulations! You solved all challenges!";
            }
        }
        return feedback;
    }

    /**
     * @return true when the whole game is done
     */
    public boolean isFinished() {
        return currentIndex >= challenges.size();
    }
}