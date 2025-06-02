package za.co.wethinkcode.bank;

public class CreditAccount extends Account{

    private static float availableBalance = 20000;
    private String accountName;

    public CreditAccount() {
        this("Credit Account", availableBalance);
    }

    public CreditAccount(String accountName) {
        this(accountName, availableBalance);
    }

    public CreditAccount(String accountName, float initialBalance) {
        this.accountName = accountName;
        this.availableBalance = initialBalance;
    }

    @Override
    public float getAvailableBalance() {
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
    public void creditAccount(float creditAmount) {
        if (creditAmount > 0) {
            availableBalance += creditAmount;
        } else {
            throw new IllegalArgumentException("Credit amount must be positive");
        }
    }

    @Override
    public void debitAccount(float debitAmount) {
        if (debitAmount > 0) {
            availableBalance -= debitAmount;
        } else {
            throw new IllegalArgumentException("Debit amount must be positive");
        }
    }
}
