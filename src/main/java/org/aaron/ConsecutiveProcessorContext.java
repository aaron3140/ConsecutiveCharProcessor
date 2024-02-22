package org.aaron;

import java.util.ArrayList;
import java.util.List;
/**
 * Interface for processing consecutive characters.
 */
interface ConsecutiveProcessor {
    /**
     * Processes consecutive characters in the input string.
     *
     * @param input The input string to process.
     * @return The processed string.
     */
    String processConsecutive(String input);
}

/**
 * Implementation for removing three or more consecutive identical characters.
 */
class RemovalProcessor implements ConsecutiveProcessor {
    @Override
    public String processConsecutive(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < input.length()) {
            char currentChar = input.charAt(i);
            int j = 0;

            // Count consecutive occurrences of the current character
            while (i < input.length() && input.charAt(i) == currentChar) {
                result.append(currentChar);
                i++;
                j++;
            }

            // If three or more consecutive characters, remove them
            if (j >= 3) {
                result.delete(i - j, i);
            }
        }

        return result.toString();
    }
}

/**
 * Implementation for replacing three or more consecutive identical characters
 * with the one that comes before alphabetically.
 */
class ReplacementProcessor implements ConsecutiveProcessor {
    @Override
    public String processConsecutive(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        List<Character> result = new ArrayList<>();
        int i = 0;

        while (i < input.length()) {
            char currentChar = input.charAt(i);
            int j = 0;

            // Count consecutive occurrences of the current character
            while (i < input.length() && input.charAt(i) == currentChar) {
                result.add(currentChar);
                i++;
                j++;
            }

            // If three or more consecutive characters, replace them
            if (j >= 3) {
                result.subList(result.size() - j, result.size()).clear();
                if (currentChar != 'a') {
                    char replacementChar = (char) (currentChar - 1);
                    result.add(replacementChar);
                }
            }
        }

        // Convert the list of characters to a string
        return result.stream()
                .map(String::valueOf)
                .reduce("", String::concat);
    }
}

/**
 * Context class that uses the strategy for processing consecutive characters.
 */
public class ConsecutiveProcessorContext {
    private final ConsecutiveProcessor consecutiveProcessor;

    /**
     * Constructs the ConsecutiveProcessorContext with a specific strategy.
     *
     * @param consecutiveProcessor The strategy for processing consecutive characters.
     */
    public ConsecutiveProcessorContext(ConsecutiveProcessor consecutiveProcessor) {
        this.consecutiveProcessor = consecutiveProcessor;
    }

    /**
     * Processes consecutive characters in the input string using the chosen strategy.
     *
     * @param input The input string to process.
     * @return The processed string.
     */
    public String processConsecutive(String input) {
        return consecutiveProcessor.processConsecutive(input);
    }
}