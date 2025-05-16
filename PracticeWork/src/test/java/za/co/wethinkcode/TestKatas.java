package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestKatas {

    @Test
    public void testReverseOrder() {
        assertEquals(54321, Katas.reverseOrder(12345));
        assertEquals(-54321, Katas.reverseOrder(-12345));
        assertEquals(987654321, Katas.reverseOrder(123456789));
        assertEquals(-987654321, Katas.reverseOrder(-123456789));
    }

    @Test
    public void testVowelCount1() {
        assertEquals(3, Katas.vowelCount1("hello world"));
        assertEquals(3, Katas.vowelCount1("Hello World"));
        assertEquals(5, Katas.vowelCount1("aeiou"));
        assertEquals(0, Katas.vowelCount1("bcdfghjklmnpqrstvwxyz"));
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
        assertTrue(Katas.pangram2("abcdefghijklmnopqrstuvwxyz"));
        assertFalse(Katas.pangram2("abcde"));
    }

}