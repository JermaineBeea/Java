package za.co.wethinkcode.bank;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoneyTest
{
    @Test
    void testAddingMoney(){
        final Money expectedAnswer = new Money( 1010 );
        Money startAmount = new Money( 10 );
        assertEquals( expectedAnswer, startAmount.add( new Money( 1_000 )));
    }

    @Test
    void testSubtractingMoney_positiveResult(){
        final Money expectedAnswer = new Money( 9_000 );
        Money startAmount = new Money( 10_000 );
        assertEquals( expectedAnswer, startAmount.subtract( new Money( 1_000 )));
    }

    @Test
    void testSubtractingMoney_negativeResult(){
        final Money expectedAnswer = new Money( -1_000 );
        Money startAmount = new Money( 1_000 );
        assertEquals( expectedAnswer, startAmount.subtract( new Money( 2_000 )));
    }

    @Test
    void testFormatMoney(){
        Money amount = new Money( 15612 );
        assertEquals( "R156.12", amount.formattedAsRands() );
    }

    @Test
    void testCompareMoney_receiverIsLessThanOtherAmount(){
        final Money testAmount = new Money( 10_00 );
        assertTrue( testAmount.compareTo( new Money( 20_00 )) < 0 );
    }

    @Test
    void testCompareMoney_receiverEqualsOtherAmount(){
        final Money testAmount = new Money( 10_00 );
        assertTrue( testAmount.compareTo( new Money( 10_00 )) == 0 );
    }

    @Test
    void testCompareMoney_receiverIsGreaterThanOtherAmount(){
        final Money testAmount = new Money( 100_00 );
        assertTrue( testAmount.compareTo( new Money( 20_00 )) > 0 );
    }

    @Test
    void testMoneyIsImplementedUsingLong(){
        final Field[] fields = Money.class.getDeclaredFields();
        assertTrue( Arrays.stream( fields )
            .anyMatch( field -> field.getType().equals( Long.TYPE )));
    }
}
