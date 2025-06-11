package za.co.wethinkcode.bank;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BusinessAccountTest {

    @Test
    public void getAccountName() {
        BusinessAccount account = new BusinessAccount();
        assertEquals("Business Account", account.accountName());
    }

    @Test
    public void renameAccount() {
        BusinessAccount account = new BusinessAccount();
        account.renameAccount("My Savings");
        assertEquals("My Savings", account.accountName());
    }

    @Test
    public void getAvailableBalance() {
        final Money expectedBalance = new Money( 0, 0 );
        BusinessAccount account = new BusinessAccount();
        assertEquals( expectedBalance, account.getAvailableBalance() );
    }

    @Test
    public void getAvailableBalanceAfterDeposit() {
        final Money expectedBalance = new Money( 100, 0 );
        BusinessAccount account = new BusinessAccount();
        account.creditAccount(new Money(100));
        assertEquals( expectedBalance, account.getAvailableBalance() );
    }

    @Test
    public void getAvailableBalanceAfterDepositandPayment() {
        final Money expectedBalance = new Money( 50, 0 );
        BusinessAccount account = new BusinessAccount();
        account.creditAccount(new Money(100));
        account.debitAccount(new Money(50));
        assertEquals( expectedBalance, account.getAvailableBalance() );
    }
}
