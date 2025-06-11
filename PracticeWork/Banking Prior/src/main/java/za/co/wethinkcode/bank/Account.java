package za.co.wethinkcode.bank;

public abstract class Account {

    public abstract float getAvailableBalance();

    public abstract String accountName();

    public abstract void renameAccount(String accountName);

    public abstract void creditAccount(float creditAmount);

    public abstract void debitAccount(float debitAmount);

    public abstract void creditAccount(Money creditAmount);

    public abstract void debitAccount(Money debitAmount);
}
