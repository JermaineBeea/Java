package za.co.wethinkcode.bank;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BusinessAccountTest {

    @Test
    public void testGetAccountName() {
        BusinessAccount account = new BusinessAccount();
        assertEquals("Business Account", account.accountName());
    }

    @Test
    public void testRenameAccount() {
        BusinessAccount account = new BusinessAccount();
        account.renameAccount("My Savings");
        assertEquals("My Savings", account.accountName());
    }

    @Test
    public void testGetAvailableBalance() {
        BusinessAccount account = new BusinessAccount();
        assertEquals(Money.ZERO, account.getAvailableBalance());
    }

    @Test
    public void testGetAvailableBalanceAfterDeposit() {
        BusinessAccount account = new BusinessAccount();
        account.creditAccount(new Money(100_00));
        assertEquals(new Money(100_00), account.getAvailableBalance());
    }

    @Test
    public void testGetAvailableBalanceAfterDepositandPayment() {
        BusinessAccount account = new BusinessAccount();
        account.creditAccount(new Money(100_00));
        account.debitAccount(new Money(50_00));
        assertEquals(new Money(50_00), account.getAvailableBalance());
    }
}
