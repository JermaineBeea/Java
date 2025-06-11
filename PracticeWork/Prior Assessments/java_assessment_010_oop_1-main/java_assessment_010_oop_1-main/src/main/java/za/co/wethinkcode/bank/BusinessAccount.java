package za.co.wethinkcode.bank;

public class BusinessAccount extends Account{

    private float availableBalance = 0;
    private String accountName;

    public BusinessAccount() {
       this("Business Account", 0);
    }


    public BusinessAccount(String accountName, float initialBalance) {
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
            throw new IllegalArgumentException("Credit amount must be positive");        }
    }

    @Override
    public void debitAccount(float debitAmount) {
        if (debitAmount > 0) {
            if (availableBalance >= debitAmount) {
                availableBalance -= debitAmount;
        } else {
            throw new IllegalArgumentException("Debit amount exceeds available balance");
        }
        availableBalance -= debitAmount;
        } else {
            throw new IllegalArgumentException("Debit amount must be positive");   
        }    
    }
}
