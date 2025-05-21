package guessingGameGUI;

import java.util.Random;

/**
 * Provides a single shared Random instance for the whole project.
 */
public final class SharedRandom {
    public static final Random RANDOM = new Random();
    private SharedRandom() {}
}
