package Part3;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WordPatternTest {

    @Test
    public void testWordPatternMatch1() {
        String pattern = "abba";
        String s = "dog cat cat dog";
        assertTrue(WordPattern.wordPatternMatch(pattern, s));
    }

    @Test
    public void testWordPatternMatch2() {
        String pattern = "abba";
        String s = "dog cat cat fish";
        assertFalse(WordPattern.wordPatternMatch(pattern, s));
    }

    @Test
    public void testWordPatternMatch3() {
        String pattern = "aaaa";
        String s = "dog cat cat dog";
        assertFalse(WordPattern.wordPatternMatch(pattern, s));
    }

    @Test
    public void testWordPatternMatch4() {
        String pattern = "abba";
        String s = "dog dog dog dog";
        assertFalse(WordPattern.wordPatternMatch(pattern, s));
    }

    @Test
    public void testWordPatternMatch5() {
        String pattern = "abc";
        String s = "dog cat fish";
        assertTrue(WordPattern.wordPatternMatch(pattern, s));
    }
}
