package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestKataSolutions {

    @Test
    public void testDescendingOrder() {
        assertEquals(54321, KataSolutions.descendingOrder(12345));
        assertEquals(-54321, KataSolutions.descendingOrder(-12345));
        assertEquals(987654321, KataSolutions.descendingOrder(123456789));
        assertEquals(-987654321, KataSolutions.descendingOrder(-123456789));
    }

    @Test
    public void testVowelCount1() {
        assertEquals(3, KataSolutions.vowelCount1("hello world"));
        assertEquals(3, KataSolutions.vowelCount1("Hello World"));
        assertEquals(5, KataSolutions.vowelCount2("aeiou"));
        assertEquals(0, KataSolutions.vowelCount3("bcdfghjklmnpqrstvwxyz"));
    }

    @Test
    public void testFindNextSquare() {
        assertEquals(121, KataSolutions.findNextSquare(100));
        assertEquals(144, KataSolutions.findNextSquare(121));
        assertEquals(-1, KataSolutions.findNextSquare(114));
        assertEquals(-1, KataSolutions.findNextSquare(-1));
    }

    @Test
    public void testUniqueLetters() {
        assertEquals("abcdefwxy", KataSolutions.uniqueLetters("xyaabbbccccdefww", "xyaabbbccccdefww"));
        assertEquals("abcdefghijklmnopqrstuvwxyz", KataSolutions.uniqueLetters("abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz"));
        assertEquals("abc", KataSolutions.uniqueLetters2("aabbcc", "abc"));
        assertEquals("ab", KataSolutions.uniqueLetters3("a", "b"));
    }

    @Test
    public void testCreatePhoneNumber() {
        assertEquals("(123) 456-7890", KataSolutions.createPhoneNumber(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));
        assertEquals("(987) 654-3210", KataSolutions.createPhoneNumber(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
        assertEquals("(000) 000-0000", KataSolutions.createPhoneNumber(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
        assertEquals("(111) 111-1111", KataSolutions.createPhoneNumber(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testPangram() {
        assertTrue(KataSolutions.pangram("The quick brown fox jumps over the lazy dog."));
        assertFalse(KataSolutions.pangram("Hello World"));
        assertTrue(KataSolutions.pangram2("abcdefghijklmnopqrstuvwxyz"));
        assertFalse(KataSolutions.pangram2("abcde"));
        assertTrue(KataSolutions.pangram3("The quick brown fox jumps over the lazy dog."));
        assertFalse(KataSolutions.pangram3("Hello World"));
    }

}