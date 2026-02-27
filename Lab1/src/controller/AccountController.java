package controller;

import java.util.Vector;

import model.Account;
import model.Transaction;

public class AccountController {

    public static boolean accountExists(Vector<Account> accounts, String account_number) {
        if (account_number == null) return false;

        for (int i = 0; i < accounts.size(); i++) {
            Account a = accounts.get(i);
            if (account_number.equals(a.getAccount_number())) {
                return true;
            }
        }
        return false;
    }
    //feature 4 get all transactions for a  given account
    public static Vector<Transaction> getTransactionsForAccount(Vector<Account> accounts,
            Vector<Transaction> transactions,
            String account_number) {

              if (!accountExists(accounts, account_number)) {
               throw new IllegalArgumentException("Account number does not exist: " + account_number);
          }

                  Vector<Transaction> result = new Vector<Transaction>();

                   for (int i = 0; i < transactions.size(); i++) {
                  Transaction t = transactions.get(i);

                   if (account_number.equals(t.getAccount_number())) {
                      result.add(t);
               }
            }

return result;
}
    

    public static double getBalance(Vector<Account> accounts, Vector<Transaction> transactions, String account_number) {
    //feature 3 if account number doesn't exist
        if (!accountExists(accounts, account_number)) {
            throw new IllegalArgumentException("Account number does not exist: " + account_number);
        }

        double total = 0.0;

        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            if (account_number.equals(t.getAccount_number())) {
                total += t.getAmount();
            }
        }

        return total;
    }
}