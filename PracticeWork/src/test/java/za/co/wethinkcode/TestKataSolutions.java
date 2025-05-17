package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestKataSolutions {

    @Test
    public void testdescendingOrder() {
        assertEquals(54321, KataSolutions.descendingOrder(12345));
        assertEquals(-54321, KataSolutions.descendingOrder(-12345));
        assertEquals(987654321, KataSolutions.descendingOrder(123456789));
        assertEquals(-987654321, KataSolutions.descendingOrder(-123456789));
    }
    
    @Test
    public void testdescendingOrder2() {
        assertEquals(54321, KataSolutions.descendingOrder2(12345));
        assertEquals(-54321, KataSolutions.descendingOrder2(-12345));
        assertEquals(987654321, KataSolutions.descendingOrder2(123456789));
        assertEquals(-987654321, KataSolutions.descendingOrder2(-123456789));
    }

    @Test
    public void testVowelCount1() {
        assertEquals(3, KataSolutions.vowelCount1("hello world"));
        assertEquals(3, KataSolutions.vowelCount1("Hello World"));
        assertEquals(5, KataSolutions.vowelCount1("aeiou"));
        assertEquals(0, KataSolutions.vowelCount1("bcdfghjklmnpqrstvwxyz"));
    }
    
    @Test
    public void testVowelCount2() {
        assertEquals(3, KataSolutions.vowelCount2("hello world"));
        assertEquals(3, KataSolutions.vowelCount2("Hello World"));
        assertEquals(5, KataSolutions.vowelCount2("aeiou"));
        assertEquals(0, KataSolutions.vowelCount2("bcdfghjklmnpqrstvwxyz"));
    }
    
    @Test
    public void testVowelCount3() {
        assertEquals(3, KataSolutions.vowelCount3("hello world"));
        assertEquals(3, KataSolutions.vowelCount3("Hello World"));
        assertEquals(5, KataSolutions.vowelCount3("aeiou"));
        assertEquals(0, KataSolutions.vowelCount3("bcdfghjklmnpqrstvwxyz"));
    }

    @Test
    public void testFindNextSquare() {
        assertEquals(144, KataSolutions.findNextSquare(121));
        assertEquals(121, KataSolutions.findNextSquare(100));
        assertEquals(-1, KataSolutions.findNextSquare(114));
        assertEquals(-1, KataSolutions.findNextSquare(-1));
    }

    @Test
    public void testUniqueLetters() {
        assertEquals("abcdefwxy", KataSolutions.uniqueLetters("xyaabbbccccdefww", "xyaabbbccccdefww"));
        assertEquals("abcdefghijklmnopqrstuvwxyz", KataSolutions.uniqueLetters("abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz"));
        assertEquals("abc", KataSolutions.uniqueLetters("aabbcc", "abc"));
        assertEquals("ab", KataSolutions.uniqueLetters("a", "b"));
    }
    
    @Test
    public void testUniqueLetters2() {
        assertEquals("abc", KataSolutions.uniqueLetters2("aabbcc", "abc"));
        assertEquals("ab", KataSolutions.uniqueLetters2("a", "b"));
    }
    
    @Test
    public void testUniqueLetters3() {
        assertEquals("abc", KataSolutions.uniqueLetters3("aabbcc", "abc"));
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
    public void testCreatePhoneNumber2() {
        assertEquals("(123) 456-7890", KataSolutions.createPhoneNumber2(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));
        assertEquals("(987) 654-3210", KataSolutions.createPhoneNumber2(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
        assertEquals("(000) 000-0000", KataSolutions.createPhoneNumber2(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
        assertEquals("(111) 111-1111", KataSolutions.createPhoneNumber2(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }
    
    @Test
    public void testCreatePhoneNumber3() {
        assertEquals("(123) 456-7890", KataSolutions.createPhoneNumber3(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));
        assertEquals("(987) 654-3210", KataSolutions.createPhoneNumber3(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
        assertEquals("(000) 000-0000", KataSolutions.createPhoneNumber3(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
        assertEquals("(111) 111-1111", KataSolutions.createPhoneNumber3(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testPangram() {
        assertTrue(KataSolutions.pangram("The quick brown fox jumps over the lazy dog."));
        assertFalse(KataSolutions.pangram("Hello World"));
        assertTrue(KataSolutions.pangram("Pack my box with five dozen liquor jugs"));
        assertFalse(KataSolutions.pangram("abcde"));
    }
    
    @Test
    public void testPangram2() {
        assertTrue(KataSolutions.pangram2("The quick brown fox jumps over the lazy dog."));
        assertFalse(KataSolutions.pangram2("Hello World"));
        assertTrue(KataSolutions.pangram2("Pack my box with five dozen liquor jugs"));
        assertFalse(KataSolutions.pangram2("abcde"));
    }
    
    @Test
    public void testPangram3() {
        assertTrue(KataSolutions.pangram3("The quick brown fox jumps over the lazy dog."));
        assertFalse(KataSolutions.pangram3("Hello World"));
        assertTrue(KataSolutions.pangram3("Pack my box with five dozen liquor jugs"));
        assertFalse(KataSolutions.pangram3("abcde"));
    }
    
    @Test
    public void testFindOutlier() {
        assertEquals(11, KataSolutions.findOutlier(new int[]{2, 4, 0, 100, 4, 11, 2602, 36}));
        assertEquals(160, KataSolutions.findOutlier(new int[]{160, 3, 1719, 19, 11, 13, -21}));
        assertEquals(1, KataSolutions.findOutlier(new int[]{2, 6, 8, 10, 1}));
        assertEquals(0, KataSolutions.findOutlier(new int[]{1, 3, 5, 7, 0}));
    }
    
    @Test
    public void testFindOutlier2() {
        assertEquals(11, KataSolutions.findOutlier2(new int[]{2, 4, 0, 100, 4, 11, 2602, 36}));
        assertEquals(160, KataSolutions.findOutlier2(new int[]{160, 3, 1719, 19, 11, 13, -21}));
        assertEquals(1, KataSolutions.findOutlier2(new int[]{2, 6, 8, 10, 1}));
        assertEquals(0, KataSolutions.findOutlier2(new int[]{1, 3, 5, 7, 0}));
    }
    
    @Test
    public void testHighAndLow() {
        assertEquals("5 1", KataSolutions.highAndLow("1 2 3 4 5"));
        assertEquals("5 -3", KataSolutions.highAndLow("1 2 -3 4 5"));
        assertEquals("9 -5", KataSolutions.highAndLow("1 9 3 4 -5"));
        assertEquals("542 -214", KataSolutions.highAndLow("4 5 29 54 4 0 -214 542 -64 1 -3 6 -6"));
    }
    
    @Test
    public void testHighAndLow2() {
        assertEquals("5 1", KataSolutions.highAndLow2("1 2 3 4 5"));
        assertEquals("5 -3", KataSolutions.highAndLow2("1 2 -3 4 5"));
        assertEquals("9 -5", KataSolutions.highAndLow2("1 9 3 4 -5"));
        assertEquals("542 -214", KataSolutions.highAndLow2("4 5 29 54 4 0 -214 542 -64 1 -3 6 -6"));
    }
}