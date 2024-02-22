package org.aaron;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsecutiveProcessorContextTest {
    @Test
    public void testRemoveConsecutive() {
        // Create a ConsecutiveProcessorContext with the RemovalProcessor strategy
        ConsecutiveProcessorContext removalContext = new ConsecutiveProcessorContext(new RemovalProcessor());

        // Test cases for removing three or more consecutive identical characters
        assertEquals("", removalContext.processConsecutive(""));
        assertEquals("", removalContext.processConsecutive("aaa"));
        assertEquals("abc", removalContext.processConsecutive("abc"));
        assertEquals("aabbbad", removalContext.processConsecutive("aabcccbbad"));
        assertEquals("aaad", removalContext.processConsecutive("aabbbad"));
        assertEquals("d", removalContext.processConsecutive("aaad"));
    }

    @Test
    public void testReplaceConsecutive() {
        // Create a ConsecutiveProcessorContext with the ReplacementProcessor strategy
        ConsecutiveProcessorContext replacementContext = new ConsecutiveProcessorContext(new ReplacementProcessor());

        // Test cases for replacing three or more consecutive identical characters
        assertEquals("", replacementContext.processConsecutive(""));
        assertEquals("a", replacementContext.processConsecutive("bbb"));
        assertEquals("", replacementContext.processConsecutive("aaa"));
        assertEquals("b", replacementContext.processConsecutive("aaab"));
        assertEquals("aabbbbad", replacementContext.processConsecutive("aabcccbbad"));
        assertEquals("aabbbbad", replacementContext.processConsecutive("aabccccbbad"));
        assertEquals("aaaad", replacementContext.processConsecutive("aabbbad"));
    }
}
