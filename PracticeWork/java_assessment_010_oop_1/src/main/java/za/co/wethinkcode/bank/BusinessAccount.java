package za.co.wethinkcode.bank;

public class BusinessAccount extends Account{

    private float availableBalance = 0;
    private String accountName;

    public BusinessAccount() {
        accountName = "Business Account";
    }

    public BusinessAccount(String accountName) {
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

    }

    @Override
    public void debitAccount(Money debitAmount) {

    }
}
