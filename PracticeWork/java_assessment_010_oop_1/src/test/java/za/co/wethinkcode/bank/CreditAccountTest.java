package za.co.wethinkcode.bank;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTest {

    @Test
    public void testGetAccountName() {
        CreditAccount account = new CreditAccount();
        assertEquals("Credit Account", account.accountName());
    }

    @Test
    public void testRenameAccount() {
        CreditAccount account = new CreditAccount();
        account.renameAccount("My Credit");
        assertEquals("My Credit", account.accountName());
    }

    @Test
    public void testGetAvailableBalance() {
        CreditAccount account = new CreditAccount();
        assertEquals(new Money(20000_00), account.getAvailableBalance());
    }

    @Test
    public void testGetAvailableBalanceAfterPayment() {
        CreditAccount account = new CreditAccount();
        account.debitAccount(new Money(500_00));
        assertEquals(new Money(19500_00), account.getAvailableBalance());
    }

    @Test
    public void testGetAvailableBalanceAfterPaymentAndDeposit() {
        CreditAccount account = new CreditAccount();
        account.debitAccount(new Money(500_00));
        account.creditAccount(new Money(250_00));
        assertEquals(new Money(19750_00), account.getAvailableBalance());
    }
}
