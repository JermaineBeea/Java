package za.co.wethinkcode;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class TestKatas{

    @Test
    public void testReverseOrder(){
        assertEquals(54321, Katas.reverseOrder(12345));
        assertEquals(-54321, Katas.reverseOrder(-12345));
        assertEquals(987654321, Katas.reverseOrder(123456789));
        assertEquals(-987654321, Katas.reverseOrder(-123456789));
    }

    @Test
    public void testVowelCount1(){
        assertEquals(3, Katas.vowelCount1("hello world"));
        assertEquals(3, Katas.vowelCount1("Hello World"));
        assertEquals(5, Katas.vowelCount1("aeiou"));
        assertEquals(0, Katas.vowelCount1("bcdfghjklmnpqrstvwxyz"));
    }

    @Test
    public void testVowelCount2(){
        assertEquals(3, Katas.vowelCount2("hello world"));
        assertEquals(3, Katas.vowelCount2("Hello World"));
        assertEquals(5, Katas.vowelCount2("aeiou"));
        assertEquals(0, Katas.vowelCount2("bcdfghjklmnpqrstvwxyz"));
    }
}