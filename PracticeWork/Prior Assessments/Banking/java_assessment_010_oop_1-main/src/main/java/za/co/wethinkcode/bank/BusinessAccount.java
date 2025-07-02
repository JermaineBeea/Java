package za.co.wethinkcode.bank;

public class BusinessAccount extends Account{

    private Money availableBalance = new Money(0);
    private String accountName;

    public BusinessAccount() {
       this("Business Account", new Money(0));
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
    public void creditAccount(Money creditAmount) {
        availableBalance = availableBalance.add(creditAmount);
    }

    @Override
    public void debitAccount(Money debitAmount) {
        availableBalance = availableBalance.subtract(debitAmount);
    }
}
