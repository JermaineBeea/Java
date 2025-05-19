package za.co.wethinkcode;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import static za.co.wethinkcode.ConsolidationOne.*;


public class TestMethods{

    @Test
    static void TestFrequency(){
        assertEquals(5, frequencyOf('a', "aaaaa"));
    }

    @Test
    static void testIsPalindrome(){
        assertTrue(isPalindrome("level"));
    }

    

}