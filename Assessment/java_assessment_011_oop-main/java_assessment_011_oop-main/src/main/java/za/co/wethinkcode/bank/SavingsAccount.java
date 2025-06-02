package za.co.wethinkcode.bank;

public class SavingsAccount extends Account{

    private String accountName;
    private Money availableBalance;

    public SavingsAccount() {
        this.accountName = "Savings Account";
        this.availableBalance = new Money(0, 0);
    }

    public SavingsAccount(String accountName) {
        this.accountName = accountName;
        this.availableBalance = new Money(0, 0);
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
    public void creditAccount( Money creditAmount) {
        this.availableBalance = this.availableBalance.add(creditAmount);
    }

    @Override
    public void debitAccount( Money debitAmount) {
        if (!insufficientFunds(debitAmount))
            this.availableBalance = this.availableBalance.subtract(debitAmount);
        else
            System.out.println("Insufficient Funds");
    }

    private boolean insufficientFunds(Money debitAmount) {
        return availableBalance.compareTo(debitAmount) < 0;
    }
}
