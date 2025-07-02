package za.co.wethinkcode.bank;

public class CreditAccount extends Account{

    private static Money availableBalance = new Money(20000);
    private String accountName;

    public CreditAccount() {
        this("Credit Account");
    }

    public CreditAccount(String accountName) {
        this.accountName = accountName;
    }

    public CreditAccount(String accountName, float initialBalance) {
    }

    @Override
    public Money getAvailableBalance() {
        return availableBalance;
    }

    @Override
    public String accountName() {
        return accountName;
    }

    @Override
    public void renameAccount(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public void creditAccount(Money creditAmount) {
    }

    @Override
    public void debitAccount(Money debitAmount) {
    }
}
