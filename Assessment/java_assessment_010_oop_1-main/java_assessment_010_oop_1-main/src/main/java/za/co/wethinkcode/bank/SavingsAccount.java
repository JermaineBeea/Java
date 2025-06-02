package za.co.wethinkcode.bank;

public class SavingsAccount extends Account{

    private final float MINBALANCE = 0;
    private float availableBalance = 0;
    private String accountName;
    
    public SavingsAccount() {
        accountName = "Savings Account";
    }
    public SavingsAccount(String accountName, float initialBalance) {
        this.accountName = accountName;
        this.availableBalance = initialBalance >= MINBALANCE ? initialBalance : MINBALANCE;
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
        if (creditAmount > 0) {
            availableBalance += creditAmount;
        } else {
            throw new IllegalArgumentException("Credit amount must be positive"); 
        }
    }
    
    public void debitAccount(float debitAmount) {
        if (debitAmount > 0) {
            if (!insufficientFunds(debitAmount)) {
                availableBalance -= debitAmount;
            } else {
                throw new IllegalArgumentException("Insufficient funds");
            }
        } else {
            throw new IllegalArgumentException("Debit amount must be positive");
        }
    }
    
    private boolean insufficientFunds(float debitAmount) {
        return (availableBalance - debitAmount) < MINBALANCE;
    }

}
