package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    private String account_number;
    private double amount;
    private Date transaction_date;

    public Transaction(String account_number, double amount, Date transaction_date) {
        this.account_number = account_number;
        this.amount = amount;
        this.transaction_date = transaction_date;
    }

    public String getAccount_number() { return account_number; }
    public void setAccount_number(String account_number) { this.account_number = account_number; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getTransaction_date() { return transaction_date; }
    public void setTransaction_date(Date transaction_date) { this.transaction_date = transaction_date; }

    @Override
    public String toString() {
        String d = (transaction_date == null)
                ? "null"
                : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(transaction_date);

        return String.format("Transaction [account_number=%s, amount=%.2f, transaction_date=%s]",
                account_number, amount, d);
    }
}

