package za.co.wethinkcode.bank;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SavingsAccountTest {

    @Test
    public void testGetAccountName() {
        SavingsAccount account = new SavingsAccount();
        assertEquals("Savings Account", account.accountName());
    }

    @Test
    public void testRenameAccount() {
        SavingsAccount account = new SavingsAccount();
        account.renameAccount("My Savings");
        assertEquals("My Savings", account.accountName());
    }

    @Test
    public void testGetAvailableBalance() {
        SavingsAccount account = new SavingsAccount();
        assertEquals(Money.ZERO, account.getAvailableBalance());
    }

    @Test
    public void testGetAvailableBalanceAfterDeposit() {
        SavingsAccount account = new SavingsAccount();
        account.creditAccount(new Money(100_00));
        assertEquals(new Money(100_00), account.getAvailableBalance());
    }

    @Test
    public void testGetAvailableBalanceAfterDepositandPayment() {
        SavingsAccount account = new SavingsAccount();
        account.creditAccount(new Money(100_00));
        account.debitAccount(new Money(50_00));
        assertEquals(new Money(50_00), account.getAvailableBalance());
    }

    @Test
    public void testBalanceMayNotBeLessThan0() {
        SavingsAccount account = new SavingsAccount();
        account.debitAccount(new Money(50_00));
        assertEquals(Money.ZERO, account.getAvailableBalance());
    }
}
