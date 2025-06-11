package za.co.wethinkcode.bank;

public class SavingsAccount extends Account{

    private final float MINBALANCE = 0;
    private float availableBalance = 0;
    private String accountName;

    public SavingsAccount() {
        accountName = "Savings Account";
    }

    public SavingsAccount(String accountName) {
        this.accountName = accountName;
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
        availableBalance += creditAmount;
    }

    @Override
    public void debitAccount(float debitAmount) {
        if (!insufficientFunds(debitAmount)) availableBalance -= debitAmount;
        else System.out.println("Insufficient Funds");
    }

    @Override
    public void creditAccount(Money creditAmount) {

    }

    @Override
    public void debitAccount(Money debitAmount) {

    }

    private boolean insufficientFunds(float debitAmount) {
        return (availableBalance - debitAmount) < MINBALANCE;
    }
}
