package za.co.wethinkcode.bank;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeThinkBankingTest {

    @Test
    public void testAddingAccount() {
        WeThinkBanking webank = new WeThinkBanking();
        webank.addAccount(new SavingsAccount());
        assertEquals(4, webank.getListOfAccounts().size());
    }

    @Test
    public void testRemoveAccount() {
        WeThinkBanking webank = new WeThinkBanking();
        final List<Account> accounts = webank.getListOfAccounts();
        final int accountCountAtStart = accounts.size();

        final Optional<Account> accountToRemove = accounts.stream()
            .filter( account -> account instanceof SavingsAccount )
            .findFirst();
        accountToRemove.ifPresentOrElse(
            account -> webank.removeAccount(account.accountName()),
            Assertions::fail );

        assertEquals(webank.getListOfAccounts().size(), accountCountAtStart - 1);
    }
}
