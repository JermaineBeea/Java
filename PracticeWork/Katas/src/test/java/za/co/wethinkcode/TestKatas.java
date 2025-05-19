package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestKatas {

    @Test
    public void testdescendingOrder() {
        assertEquals(54321, Katas.descendingOrder(12345));
        assertEquals(-54321, Katas.descendingOrder(-12345));
        assertEquals(987654321, Katas.descendingOrder(123456789));
        assertEquals(-987654321, Katas.descendingOrder(-123456789));
    }

    @Test
    public void testVowelCount() {
        assertEquals(3, Katas.vowelCount("hello world"));
        assertEquals(3, Katas.vowelCount("Hello World"));
        assertEquals(5, Katas.vowelCount("aeiou"));
        assertEquals(0, Katas.vowelCount("bcdfghjklmnpqrstvwxyz"));
    }

    @Test
    public void testFindNextSquare() {
        assertEquals(121, Katas.findNextSquare(100));
        assertEquals(144, Katas.findNextSquare(121));
        assertEquals(-1, Katas.findNextSquare(114));
        assertEquals(-1, Katas.findNextSquare(-1));
    }

    @Test
    public void testUniqueLetters() {
        assertEquals("abcdefwxy", Katas.uniqueLetters("xyaabbbccccdefww", "xyaabbbccccdefww"));
        assertEquals("abcdefghijklmnopqrstuvwxyz", Katas.uniqueLetters("abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz"));
        assertEquals("abc", Katas.uniqueLetters("aabbcc", "abc"));
        assertEquals("ab", Katas.uniqueLetters("a", "b"));
    }

    @Test
    public void testCreatePhoneNumber() {
        assertEquals("(123) 456-7890", Katas.createPhoneNumber(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));
        assertEquals("(987) 654-3210", Katas.createPhoneNumber(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
        assertEquals("(000) 000-0000", Katas.createPhoneNumber(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
        assertEquals("(111) 111-1111", Katas.createPhoneNumber(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testPangram() {
        assertTrue(Katas.pangram("The quick brown fox jumps over the lazy dog."));
        assertFalse(Katas.pangram("Hello World"));
        assertTrue(Katas.pangram("abcdefghijklmnopqrstuvwxyz"));
        assertFalse(Katas.pangram("abcde"));
    }

    @Test
    public void testFindOutlier() {
        assertEquals(11, Katas.findOutlier(new int[]{2, 4, 0, 100, 4, 11, 2602, 36}));
        assertEquals(160, Katas.findOutlier(new int[]{160, 3, 1719, 19, 11, 13, -21}));
        assertEquals(1, Katas.findOutlier(new int[]{2, 6, 8, 10, 1}));
        assertEquals(2, Katas.findOutlier(new int[]{1, 3, 5, 7, 2, 9, 11}));
    }

    @Test 
    public void testHighAndLow() {
        assertEquals("5 1", Katas.highAndLow("1 2 3 4 5"));
        assertEquals("5 -3", Katas.highAndLow("1 2 -3 4 5"));
        assertEquals("9 -5", Katas.highAndLow("1 9 3 4 -5"));
        assertEquals("42 -42", Katas.highAndLow("42 -42"));
    }
}