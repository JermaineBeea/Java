package za.co.wethinkcode.bank;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SavingsAccountTest {

    @Test
    public void getAccountName() {
        SavingsAccount account = new SavingsAccount();
        assertEquals("Savings Account", account.accountName());
    }

    @Test
    public void renameAccount() {
        SavingsAccount account = new SavingsAccount();
        account.renameAccount("My Savings");
        assertEquals("My Savings", account.accountName());
    }

    @Test
    public void getAvailableBalance() {
        final Money expectedBalance = new Money( 0, 0 );
        SavingsAccount account = new SavingsAccount();
        assertEquals( expectedBalance, account.getAvailableBalance() );
    }

    @Test
    public void getAvailableBalanceAfterDeposit() {
        final Money expectedBalance = new Money( 100, 0 );
        SavingsAccount account = new SavingsAccount();
        account.creditAccount(new Money(100, 0));
        assertEquals( expectedBalance, account.getAvailableBalance() );
    }

    @Test
    public void getAvailableBalanceAfterDepositandPayment() {
        final Money expectedBalance = new Money( 50, 0 );
        SavingsAccount account = new SavingsAccount();
        account.creditAccount(new Money(100));
        account.debitAccount(new Money(50));
        assertEquals( expectedBalance, account.getAvailableBalance() );
    }

    @Test
    public void balanceMayNotBeLessThan0() {
        final Money expectedBalance = new Money( 0, 0 );
        SavingsAccount account = new SavingsAccount();
        account.debitAccount(new Money(50));
        assertEquals( expectedBalance, account.getAvailableBalance() );
    }
}
