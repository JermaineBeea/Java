package za.co.wethinkcode.bank;

import java.util.ArrayList;
import java.util.Arrays;

import za.co.wethinkcode.io.Io;
import za.co.wethinkcode.io.StandardIo;

public class WeThinkBanking {

    private final ArrayList<Account> listOfAccounts = new ArrayList<>( Arrays.asList(
        new SavingsAccount(),
        new CreditAccount(),
        new BusinessAccount()));
    private final String[] mainMenuOptions = {
        "Withdraw from Account",
        "Deposit into Account",
        "Create new Account",
        "Remove Account",
        "Exit" };

    private final Io io;

    public WeThinkBanking(){
        this( new StandardIo() );
    }

    public WeThinkBanking( Io anIo ){
        if( anIo == null ) throw new RuntimeException();
        io = anIo;
    }

    public void welcome(){
        io.println( "Welcome to WeThinkBanking_!");
        boolean exit = false;
        while (!exit) {
            exit = mainMenuOptions();
        }
        io.println("Thank you. Goodbye!");
    }

    private boolean mainMenuOptions() {
        displayAccounts(false);
        return displayMainMenuOptions();
    }

    private boolean displayMainMenuOptions() {
        io.println("Main Menu:");
        for (int i = 0; i < mainMenuOptions.length;  i++) {
            io.print("\t[");
            io.print( Integer.toString(i + 1));
            io.print( "] " );
            io.println( mainMenuOptions[ i ] );
        }

        int choice = io.anyInteger( "Please choose an option from the above: " );
        switch (choice) {
            case 1 -> doWithdrawMenu();
            case 2 -> doDepositMenu();
            case 3 -> doCreateNewAccountMenu();
            case 4 -> doRemoveAccountMenu();
            case 5 -> {
                return true;
            }
            default -> {
                return false;
            }
        }
        return false;
    }

    private void doDepositMenu() {
//        io.println();
        io.println("Deposit Menu");
        displayAccounts(true);
        int inputValidated = io.anyInteger("Which Account would you like to make a deposit into? ");
        float depositAmount = io.anyFloat("How much would you like to deposit? ");
        Account account = listOfAccounts.get(inputValidated-1);
        account.creditAccount(depositAmount);
        printAccountBalance(account);
    }

    private void doWithdrawMenu() {
        io.println("Withdrawal Menu");
        displayAccounts(true);
        int inputValidated = io.anyInteger("Which Account would you like to withdraw from? ");
        float withdrawAmount = io.anyFloat("How much would you like to withdraw? ");
        Account account = listOfAccounts.get(inputValidated-1);
        account.debitAccount(withdrawAmount);
        printAccountBalance(account);
    }

    private void displayAccounts(boolean showOptions) {
        io.println("");
        if(!showOptions){
            io.println("Your accounts:");
        }
        for( int i = 0; i < getListOfAccounts().size(); i++ ){
            Account account = getListOfAccounts().get( i );
            if(showOptions){
                io.print("\t[");
                io.print(Integer.toString( i + 1 ));
                io.print("] ");
            }
            io.print(account.accountName());
            io.print(" - Balance: ");
            io.println(formatAccountOutput( account));
        }
        io.println("");
    }

    public ArrayList<Account> getListOfAccounts() {
        return listOfAccounts;
    }

    private void doCreateNewAccountMenu() {
        io.println("");
        io.println("Create Account");
        String accountName = io.anyString("What would you like to name this account? ");
        io.println("What account would you like to create?");
        io.println("\t- Savings");
        io.println("\t- Credit");
        io.println("\t- Business");
        String accountType = io.anyString("Enter the account type: ").toLowerCase().trim();
        openNewAccount(accountName, accountType);
        Account newAccount = listOfAccounts.get(listOfAccounts.size()-1);
        io.println("");
        io.println("New Account '" + newAccount.accountName() + "' created. Balance: " + formatAccountOutput(newAccount));
    }

    public void addAccount(Account account) {
        listOfAccounts.add(account);
    }

    private void openNewAccount(String accountName, String accountType) {
        if (accountType.contains("savings")) {
            addAccount(accountName.isBlank() ? new SavingsAccount() : new SavingsAccount(accountName));
        } else if (accountType.contains("credit")) {
            addAccount(accountName.isBlank() ? new CreditAccount() : new CreditAccount(accountName));
        } else if (accountType.contains("business")) {
            addAccount(accountName.isBlank() ? new BusinessAccount() : new BusinessAccount(accountName));
        } else {
            addAccount(new SavingsAccount("New Account"));
        }
    }

    private void addAccount(Account account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAccount'");
    }

    private void doRemoveAccountMenu() {
        io.println("Remove Account");
        displayAccounts(false);
        String accountToRemove = io.anyString("Which account would you like to remove? (Enter Account Name) ");
        removeAccount(accountToRemove);
    }

    public void removeAccount(String accountName) {
        int index = -1;
        for (Account account: listOfAccounts) {
            if (account.accountName().contains(accountName)) {
                index = listOfAccounts.indexOf(account);
                io.println(account.accountName() + " removed.");
            }
        }
        if (index != -1) listOfAccounts.remove(index);
    }

    private void printAccountBalance(Account account) {
        io.print("Your balance is: ");
        io.println(formatAccountOutput(account));
    }

    private String formatAccountOutput(Account account) {
        return "R" + String.format("%.2f", account.getAvailableBalance());
    }
}

