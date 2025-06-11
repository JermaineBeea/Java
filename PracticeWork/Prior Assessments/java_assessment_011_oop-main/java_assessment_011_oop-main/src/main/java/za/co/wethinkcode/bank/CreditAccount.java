package za.co.wethinkcode.bank;

public class CreditAccount extends Account{

    private Money availableBalance = new Money( (int) 20000.00 );
    private String accountName;

    public CreditAccount() {
        accountName = "Credit Account";
    }

    public CreditAccount(String accountName) {
        this.accountName = accountName;
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
        availableBalance = availableBalance.add(creditAmount);
    }

    @Override
    public void debitAccount( Money debitAmount) {
        availableBalance = availableBalance.subtract(debitAmount);
    }
}
