package app;

import java.util.Vector;
import model.Transaction;

public class FeatureDemo {

    public static void main(String[] args) {

        SimpleBankingApp.loadUserData();
        SimpleBankingApp.loadAccountData();

        // Feature 1: zero transaction rejected
        try {
            SimpleBankingApp.addTransaction("5495-1234", 0.0);
            System.out.println("ERROR: zero transaction was allowed (should not happen)");
        } catch (IllegalArgumentException ex) {
            System.out.println("Feature 1 OK: " + ex.getMessage());
        }

        // Feature 2: unknown account rejected by getBalance
        try {
            SimpleBankingApp.getBalance("9999-1111");
            System.out.println("ERROR: unknown account balance was allowed (should not happen)");
        } catch (IllegalArgumentException ex) {
            System.out.println("Feature 2 OK: " + ex.getMessage());
        }

        // Feature 3: total balance for user
        double total = SimpleBankingApp.getTotalBalanceForUser("mike");
        System.out.println("Feature 3 OK: total balance for mike = " 
                + String.format("%.2f", total));

        // feature 4: retrieve transactions for account

       
        SimpleBankingApp.addTransaction("5495-1234", 25.00);
        SimpleBankingApp.addTransaction("5495-1234", -10.00);

        Vector<Transaction> txs =
                SimpleBankingApp.getTransactionsForAccount("5495-1234");

        System.out.println("Feature 4 OK: transactions count = " + txs.size());
    }
}