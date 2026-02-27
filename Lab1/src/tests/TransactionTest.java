package tests;

import java.util.Date;

import model.Transaction;
import utils.TestUtils;

public class TransactionTest {

    private static Transaction buildTransactionFixture() {
        String accountNumber = "A-1001";
        double amount = 50.0;
        Date transactionDate = new Date(0); // stable date for equality checks
        return new Transaction(accountNumber, amount, transactionDate);
    }

    public static void testTransactionConstructor() {
        String testCaseName = "testTransactionConstructor";
        Transaction t = buildTransactionFixture();
        if (t != null)
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testGetAccount_number() {
        Transaction t = buildTransactionFixture();
        String expected = "A-1001";
        String testCaseName = "testGetAccount_number";
        if (expected.equals(t.getAccount_number()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testSetAccount_number() {
        Transaction t = buildTransactionFixture();
        String newValue = "A-2002";
        String testCaseName = "testSetAccount_number";
        t.setAccount_number(newValue);
        if (newValue.equals(t.getAccount_number()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testGetAmount() {
        Transaction t = buildTransactionFixture();
        double expected = 50.0;
        String testCaseName = "testGetAmount";
        if (expected == t.getAmount())
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testSetAmount() {
        Transaction t = buildTransactionFixture();
        double newValue = -20.0;
        String testCaseName = "testSetAmount";
        t.setAmount(newValue);
        if (newValue == t.getAmount())
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testGetTransaction_date() {
        Transaction t = buildTransactionFixture();
        Date expected = new Date(0);
        String testCaseName = "testGetTransaction_date";
        if (expected.equals(t.getTransaction_date()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testSetTransaction_date() {
        Transaction t = buildTransactionFixture();
        Date newValue = new Date(2000);
        String testCaseName = "testSetTransaction_date";
        t.setTransaction_date(newValue);
        if (newValue.equals(t.getTransaction_date()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testToString() {
        Transaction t = buildTransactionFixture();
        String s = t.toString();
        String testCaseName = "testToString";
        if (s != null && s.length() > 0)
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void main(String[] args) {
        testTransactionConstructor();
        testGetAccount_number();
        testSetAccount_number();
        testGetAmount();
        testSetAmount();
        testGetTransaction_date();
        testSetTransaction_date();
        testToString();
    }
}
