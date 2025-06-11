package za.co.wethinkcode.bank;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTest {

    @Test
    public void getAccountName() {
        CreditAccount account = new CreditAccount();
        assertEquals("Credit Account", account.accountName());
    }

    @Test
    public void renameAccount() {
        CreditAccount account = new CreditAccount();
        account.renameAccount("My Credit");
        assertEquals("My Credit", account.accountName());
    }

    @Test
    public void getAvailableBalance() {
        final Money expectedBalance = new Money( 20000, 0 );
        CreditAccount account = new CreditAccount();
        assertEquals( expectedBalance, account.getAvailableBalance() );
    }

    @Test
    public void getAvailableBalanceAfterPayment() {
        final Money expectedBalance = new Money( 19500, 0 );
        CreditAccount account = new CreditAccount();
        account.debitAccount(new Money(500, 0));
        assertEquals( expectedBalance, account.getAvailableBalance() );
    }

    @Test
    public void getAvailableBalanceAfterPaymentAndDeposit() {
        final Money expectedBalance = new Money( 19750, 0 );
        CreditAccount account = new CreditAccount();
        account.debitAccount(new Money(500, 0));
        account.creditAccount(new Money(250, 0));
        assertEquals( expectedBalance, account.getAvailableBalance() );
    }
}
