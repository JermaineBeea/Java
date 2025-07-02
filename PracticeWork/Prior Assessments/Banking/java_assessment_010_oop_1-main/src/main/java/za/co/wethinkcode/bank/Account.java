package za.co.wethinkcode.bank;

public abstract class Account {

    public abstract Money getAvailableBalance();

    public abstract String accountName();

    public abstract void renameAccount(String accountName);

    public abstract void creditAccount(Money creditAmount);

    public abstract void debitAccount(Money debitAmount);
}
