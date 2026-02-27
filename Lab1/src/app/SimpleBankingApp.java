package app;

import java.util.Calendar;
import java.util.Vector;

import controller.AccountController;
import controller.AccountDataController;
import controller.UserController;
import model.Account;
import model.Transaction;
import model.User;

public class SimpleBankingApp {

    public static Vector<User> users = new Vector<User>();
    public static Vector<Account> accounts = new Vector<Account>();
    public static Vector<Transaction> transactions = new Vector<Transaction>();

    public static void loadUserData() {
        users = UserController.loadUserData();
    }

    public static void loadAccountData() {
        accounts = AccountDataController.loadAccountData();
    }

    public static void printAllUsers() {
        System.out.println("There are: " + users.size() + " users in the system.");
        System.out.println(String.format("%-25s| %-15s| %-15s| %-15s| %-15s",
                "username", "password", "first_name", "last_name", "mobile_number"));
        System.out.println("-------------------------------------------------------------------------------------------");
        for (int i = 0; i < users.size(); i++)
            System.out.println(users.get(i).toString());
        System.out.println();
    }

    public static void printAllAccounts() {
        System.out.println("There are: " + accounts.size() + " accounts in the system.");

        System.out.println(String.format("%-10s| %-30s| %-10s| %-15s| %-15s",
                "Account #", "username_of_account_holder", "type", "opening_date", "Balance"));
        System.out.println("--------------------------------------------------------------------------------");

        for (int i = 0; i < accounts.size(); i++) {
            String accNum = accounts.get(i).getAccount_number();
            double bal = AccountController.getBalance(accounts, transactions, accNum);

            System.out.println(accounts.get(i).toString() + "| $" + String.format("%.2f", bal));
        }

        System.out.println();
    }

    // Improvement: reject zero-amount transactions
    public static void addTransaction(String account_number, double amount) {
        if (amount == 0.0) {
            throw new IllegalArgumentException("Transaction amount must be non-zero.");
        }

        Transaction aTransaction = new Transaction(account_number, amount, Calendar.getInstance().getTime());
        transactions.add(aTransaction);
    }

    // Delegating wrapper (still OK): now uses account-existence validation too
    public static double getBalance(String account_number) {
        return AccountController.getBalance(accounts, transactions, account_number);
    }

    // Wrapper for the new user feature (helps system-level usage)
    public static double getTotalBalanceForUser(String username) {
        return UserController.getTotalBalanceForUser(accounts, transactions, username);
    }
    
    //wrapper feature 4
    public static Vector<Transaction> getTransactionsForAccount(String account_number) {
        return AccountController.getTransactionsForAccount(accounts, transactions, account_number);
    }

    public static void main(String[] args) {

        loadUserData();
        printAllUsers();

        loadAccountData();
        System.out.println("Accounts: initial state, after loading...");
        printAllAccounts();

        addTransaction("5495-1234", -50.21);
        System.out.println("Account: after the 1st addTransaction function call...");
        printAllAccounts();

        addTransaction("5495-1234", 520.00);

        // This will now be rejected by getBalance checks only when balance is requested.
        // addTransaction itself still adds it, but it will never appear in account balances.
        // We'll test the invalid account behaviour via getBalance.
        addTransaction("9999-1111", 21.00);

        System.out.println("Account: after the 2nd/3rd addTransaction function calls...");
        printAllAccounts();

        System.out.println("Total balance for user mike: " + String.format("%.2f", getTotalBalanceForUser("mike")));
    }
}
