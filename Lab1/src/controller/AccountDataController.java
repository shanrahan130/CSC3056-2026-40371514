package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import model.Account;

public class AccountDataController {

    public static Vector<Account> loadAccountData() {
        Vector<Account> accounts = new Vector<Account>();

        try {
            accounts.add(new Account("5495-1234", "mike", "Standard",
                    new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019")));

            accounts.add(new Account("5495-1239", "mike", "Standard",
                    new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2020")));

            accounts.add(new Account("5495-1291", "mike", "Saving",
                    new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2019")));

            accounts.add(new Account("5495-6789", "David.McDonald@gmail.com", "Saving",
                    new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019")));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return accounts;
    }
}
