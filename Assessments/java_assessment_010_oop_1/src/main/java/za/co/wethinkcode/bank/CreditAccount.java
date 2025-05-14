package za.co.wethinkcode.bank;

public class CreditAccount extends Account{

    private float availableBalance = 20000;
    private String accountName;

    public CreditAccount() {
        accountName = "Credit Account";
    }

    public CreditAccount(String accountName) {
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
        availableBalance -= debitAmount;
    }

    @Override
    public void creditAccount(Money creditAmount) {
//        availableBalance += creditAmount;
    }

    @Override
    public void debitAccount(Money debitAmount) {

//        availableBalance -= debitAmount;
    }
}
