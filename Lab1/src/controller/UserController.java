package controller;

import java.util.Vector;

import model.Account;
import model.Transaction;
import model.User;

public class UserController {

    public static Vector<User> loadUserData() {
        Vector<User> users = new Vector<User>();

        users.add(new User("mike", "my_passwd", "Mike", "Smith", "07771234567"));
        users.add(new User("james.cameron@gmail.com", "angel", "James", "Cameron", "07777654321"));
        users.add(new User("julia.roberts@gmail.com", "change_me", "Julia", "roberts", "07770123456"));

        return users;
    }

    // New Feature: total balance across all accounts for a given username
    public static double getTotalBalanceForUser(Vector<Account> accounts, Vector<Transaction> transactions, String username) {
        if (username == null) {
            throw new IllegalArgumentException("username cannot be null");
        }

        double total = 0.0;

        for (int i = 0; i < accounts.size(); i++) {
            Account a = accounts.get(i);

            if (username.equals(a.getUsername_of_account_holder())) {
                total += AccountController.getBalance(accounts, transactions, a.getAccount_number());
            }
        }

        return total;
    }
}