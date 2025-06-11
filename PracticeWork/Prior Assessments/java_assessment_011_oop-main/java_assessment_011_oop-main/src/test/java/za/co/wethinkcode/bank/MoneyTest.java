package za.co.wethinkcode.bank;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest
{
    // First 4 tests just test our ability to create a Money object from a String.

    @Test
    void createMoneyFromString_aPositiveNumber_withCents(){
        final Money expectedValue = new Money( 1020, 30 );
        assertEquals( expectedValue, new Money( "1020.30" ));
    }

    @Test
    void createMoneyFromString_aPositiveNumber_noCents(){
        final Money expectedValue = new Money( 1020, 0 );
        assertEquals( expectedValue, new Money( "1020" ));
    }

    @Test
    void createMoneyFromString_aNegativeNumber_withCents(){
        final Money expectedValue = new Money( -1020, 30 );
        assertEquals( expectedValue, new Money( "-1020.30" ));
    }

    @Test
    void createMoneyFromString_aNegativeNumber_noCents(){
        final Money expectedValue = new Money( -1020, 0 );
        assertEquals( expectedValue, new Money( "-1020" ));
    }

    // Now we can test the actual Money operations...

    @Test
    void testAddingMoney_centsLessThan100(){
        Money startAmount = new Money( 10, 20 );
        Money result = startAmount.add( new Money( 1000, 10 ));
        assertEquals( new Money( 1010, 30 ), result );
    }

    @Test
    void testAddingMoney_centsExceed100(){
        Money startAmount = new Money( 10, 20 );
        Money result = startAmount.add( new Money( 1000, 90 ));
        assertEquals( new Money( 1011, 10 ), result );
    }

    @Test
    void testSubtractingMoney_noCentsUnderflow(){
        Money startAmount = new Money( "1000.50" );
        assertEquals( new Money( "-1000.30" ), startAmount.subtract( new Money( "2000.20" )));
    }

    @Test
    void testSubtractingMoney_centsUnderflow(){
        Money startAmount = new Money( "10000" );
        assertEquals( new Money( "9000.50" ), startAmount.subtract( new Money( "999.50" )));
    }

    @Test
    void testSubtractingMoney_centsResultIsZero(){
        Money startAmount = new Money( "1000.99" );
        assertEquals( new Money( "1000" ), startAmount.subtract( new Money( "0.99" )));
    }

    @Test
    void formatMoney(){
        Money amount = new Money( "156.12" );
        assertEquals( "R156.12", amount.formattedAsRands() );
    }

    @Test
    void compareMoney_receiverIsLessThanOtherAmount(){
        final Money testAmount = new Money( 10_00 );
        assertTrue( testAmount.compareTo( new Money( 20_00 )) < 0 );
    }

    @Test
    void compareMoney_receiverEqualsOtherAmount(){
        final Money testAmount = new Money( 10_00 );
        assertEquals( 0, testAmount.compareTo( new Money( 10_00 ) ) );
    }

    @Test
    void compareMoney_receiverIsGreaterThanOtherAmount(){
        final Money testAmount = new Money( 100_00 );
        assertTrue( testAmount.compareTo( new Money( 20_00 )) > 0 );
    }

    @Test
    void moneyIsImplementedUsingBigIntegerAndInt(){
        final Field[] fields = Money.class.getDeclaredFields();
        assertTrue( Arrays.stream( fields )
            .anyMatch( field -> field.getType().equals( BigInteger.class )));
        assertTrue( Arrays.stream( fields )
            .anyMatch( field -> field.getType().equals( Integer.TYPE )));
    }
}
