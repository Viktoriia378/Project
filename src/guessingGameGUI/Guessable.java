package guessingGameGUI;

/**
 * Contract for a single guessing challenge.
 */
public interface Guessable {
    /**
     * Prompt shown to the player before each attempt.
     */
    String getPrompt();

    /**
     * Processes user's guess and returns feedback message.
     *
     * @param input Raw user input (trimmed, lowered)
     * @return feedback for the player
     */
    String guess(String input);

    /**
     * @return true when the challenge is solved
     */
    boolean isGuessed();
}