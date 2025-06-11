package za.co.wethinkcode;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static za.co.wethinkcode.ConsolidationOne.*;

public class ConsolidationOneTests {
    @Test
    void testFizzBuzzOnce() {
        assertEquals("1", fizzBuzzOnce(1));
        assertEquals("Fizz", fizzBuzzOnce(3));
        assertEquals("Buzz", fizzBuzzOnce(5));
        assertEquals("FizzBuzz", fizzBuzzOnce(15));
        assertEquals("101", fizzBuzzOnce(101));
        assertEquals("Fizz", fizzBuzzOnce(333));
        assertEquals("Buzz", fizzBuzzOnce(505));
        assertEquals("FizzBuzz", fizzBuzzOnce(19995));
    }

    @Test
    void testFizzBuzz() {
        int n = 20;
        List<String> expectedOutput = Arrays.asList(
                "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz",
                "11", "Fizz", "13", "14", "FizzBuzz", "16", "17", "Fizz", "19", "Buzz"
        );
        List<String> actualOutput = ConsolidationOne.fizzBuzz(n);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testGenerateFibonacciSequence() {
        int n = 10;
        String expectedSequence = "Fibonacci Sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34,";
        String actualSequence = getFibonacciSequence(n);
        assertEquals(expectedSequence, actualSequence);
    }

    @Test
    void testGenerateFibonacciSequenceLarger() {
        int n = 20;
        String expectedSequence = "Fibonacci Sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89," +
                " 144, 233, 377, 610, 987, 1597, 2584, 4181,";
        String actualSequence = getFibonacciSequence(n);
        assertEquals(expectedSequence, actualSequence);
    }

    @Test
    void testRecursiveFibonacci() {
        assertEquals(5, recursiveFibonacciN(6));
        assertEquals(34, recursiveFibonacciN(10));
        assertEquals(377, recursiveFibonacciN(15));
    }

    @Test
    void testDrawTriangle() {
        int n = 5;
        String expectedOutput = "*\n**\n***\n****\n*****\n";
        String actualOutput = drawTriangle(n);
        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    void testDrawTriangle10() {
        int n = 10;
        String expectedOutput = "*\n**\n***\n****\n*****\n******\n*******\n********\n*********\n**********\n";
        String actualOutput = drawTriangle(n);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testAddition() {
        String result = calculate(5, 3, "+");
        assertEquals("Okay: 8", result);
    }

    @Test
    void testSubtraction() {
        String result = calculate(10, 4, "-");
        assertEquals("Okay: 6", result);
    }

    @Test
    void testMultiplication() {
        String result = calculate(6, 2, "*");
        assertEquals("Okay: 12", result);
    }

    @Test
    void testDivision() {
        String result = calculate(15, 5, "/");
        assertEquals("Okay: 3", result);
    }

    @Test
    void testDivisionByZero() {
        String result = calculate(10, 0, "/");
        assertEquals("Error: Division by zero is not allowed.", result);
    }

    @Test
    void testModuloCorrect() {
        assertEquals("Okay: 1", calculateModCorrectly(7, 3, "%"));
        assertEquals("Okay: 2", calculateModCorrectly(5, 3, "%"));
    }

    @Test
    void testInvalidOperator() {
        String result = calculate(2, 3, "^");
        assertEquals("Error: Invalid operator: ^", result);
    }

    @Test
    void testCorrectOrderWithEmptyList() {
        List<String> names = List.of();
        List<String> expectedOrder = List.of();
        List<String> actualOrder = correctOrder(names);
        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void testCorrectOrderWithSingleName() {
        List<String> names = List.of("Sphamandla");
        List<String> expectedOrder = List.of("Sphamandla");
        List<String> actualOrder = correctOrder(names);
        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void testCorrectOrder() {
        List<String> names = List.of("Khethukuthula", "Jayson", "Chattergoon", "Tshilidzi", "Neo");
        List<String> expectedOrder = List.of("Neo", "Jayson", "Tshilidzi", "Chattergoon", "Khethukuthula");
        List<String> actualOrder = correctOrder(names);
        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void testDnaProteinWithValidProtein() {
        String dna = "ATGCGATACTGA";
        assertTrue(dnaProtein(dna));
    }

    @Test
    void testDnaProteinWithInvalidProtein_LengthNotDivisibleByThree() {
        String dna = "ATGCGATAGA";
        assertFalse(dnaProtein(dna));
    }

    @Test
    void testDnaProteinWithInvalidProtein_NoStartCodon() {
        String dna = "CGATACTGA";
        assertFalse(dnaProtein(dna));
    }

    @Test
    void testDnaProteinWithInvalidProtein_NoStopCodon() {
        String dna = "ATGCGATACT";
        assertFalse(dnaProtein(dna));
    }

    @Test
    void testDnaProteinWithInvalidProtein_ShorterThanMinimumLength() {
        String dna = "ATG";
        assertFalse(dnaProtein(dna));
    }

    @Test
    void testPunnetSquare() {
        String genotype1 = "Aa";
        String genotype2 = "Bb";

        String[][] result = punnettSquare(genotype1, genotype2);

        String[][] expected = {
                {"AB", "Ab"},
                {"aB", "ab"}
        };

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    void testPunnetSquareWithSameGenotype() {
        String genotype1 = "AA";
        String genotype2 = "Aa";

        String[][] result = punnettSquare(genotype1, genotype2);

        String[][] expected = {
                {"AA", "Aa"},
                {"AA", "Aa"}
        };

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    void testPunnetSquareWithDifferentLetterCase() {
        String genotype1 = "Aa";
        String genotype2 = "bB";

        String[][] result = punnettSquare(genotype1, genotype2);

        String[][] expected = {
                {"Ab", "AB"},
                {"ab", "aB"}
        };

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    void testPunnetSquareWithDominantRecessive() {
        String genotype1 = "aa";
        String genotype2 = "bB";

        String[][] result = punnettSquare(genotype1, genotype2);

        String[][] expected = {
                {"ab", "aB"},
                {"ab", "aB"}
        };

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    void testBinaryConversion() {
        assertEquals("2", binaryConversion("10"));
        assertEquals("21", binaryConversion("10101"));
        assertEquals("6", binaryConversion("000110"));
        assertEquals("57", binaryConversion("111001"));
        assertEquals("19", binaryConversion("10011"));
        assertEquals("Error: Not a binary number.", binaryConversion("onezero"));
    }

    @Test
    void findOneSubstring() {
        assertEquals(2, firstSubstring("C", "WTC"));
        assertEquals(4, firstSubstring("o", "Hello, World!"));
        assertEquals(7, firstSubstring("6", "3141592654"));
    }

    @Test
    void testFrequencyOfLetter() {
        assertEquals(1, frequencyOf('a', "djdjdadd"));
        assertEquals(0, frequencyOf('x', "djdjdadd"));
        assertEquals(5, frequencyOf('d', "djdjdadd"));
    }


    @Test
    void testHammingDistance() {
        assertEquals(1, hammingDistance("a", "b"));
        assertEquals(1, hammingDistance("ab", "ac"));
        assertEquals(4, hammingDistance("WeThinkCode_", "WeWriteCode_"));
        assertEquals(-1, hammingDistance("unequal", "length of strings"));
    }

    @Test
    void testIsPalindrome() {
        assertTrue(isPalindrome("level"));
        assertFalse(isPalindrome("Hello"));
        assertTrue(isPalindrome(""));
        assertTrue(isPalindrome("A"));
        assertTrue(isPalindrome("Racecar"));
    }

    @Test
    void testsumIntegersWithPositiveNumbers() {
        Collection<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int sum = sumIntegers(numbers);
        assertEquals(15, sum);
    }

    @Test
    void testsumIntegersWithPositiveAndNegativeNumbers() {
        Collection<Integer> numbers = new ArrayList<>(Arrays.asList(-10, 20, -5, 30));
        int sum = sumIntegers(numbers);
        assertEquals(35, sum);
    }

    @Test
    void testsumIntegersWithEmptyCollection() {
        Collection<Integer> numbers = new ArrayList<>();
        int sum = sumIntegers(numbers);
        assertEquals(0, sum);
    }

    @Test
    void testsumIntegersWithSingleNumber() {
        Collection<Integer> numbers = new ArrayList<>(Arrays.asList(7));
        int sum = sumIntegers(numbers);
        assertEquals(7, sum);
    }

    @Test
    void testPascalTriangle() {
        assertEquals(Arrays.asList(1), pascalTriangle(0));
        assertEquals(Arrays.asList(1, 3, 3, 1), pascalTriangle(3));
        assertEquals(Arrays.asList(1, 5, 10, 10, 5, 1), pascalTriangle(5));
        assertEquals(Arrays.asList(1, 10, 45, 120, 210, 252,
                210, 120, 45, 10, 1), pascalTriangle(10));
    }
}





