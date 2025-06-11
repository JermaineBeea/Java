package za.co.wethinkcode.bank;

public class BusinessAccount extends Account{

    private Money availableBalance = new Money( (int) 0.00 );
    private String accountName;

    public BusinessAccount() {
        accountName = "Business Account";
    }

    public BusinessAccount(String accountName, Money initialBalance) {
        this.accountName = accountName;
        this.availableBalance = initialBalance;
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
        this.availableBalance = this.availableBalance.subtract(debitAmount);
    }
}
