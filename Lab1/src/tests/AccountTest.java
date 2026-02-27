package tests;

import java.util.Date;

import model.Account;
import utils.TestUtils;

public class AccountTest {

    private static Account buildAccountFixture() {
        String accountNumber = "A-1001";
        String usernameHolder = "abc@gmail.com";
        String accountType = "Standard";
        Date openingDate = new Date(0); // fixed date for stable equality checks
        return new Account(accountNumber, usernameHolder, accountType, openingDate);
    }

    public static void testAccountConstructor() {
        String testCaseName = "testAccountConstructor";
        Account a = buildAccountFixture();
        if (a != null)
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testGetAccount_number() {
        Account a = buildAccountFixture();
        String expected = "A-1001";
        String testCaseName = "testGetAccount_number";
        if (expected.equals(a.getAccount_number()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testSetAccount_number() {
        Account a = buildAccountFixture();
        String newValue = "A-2002";
        String testCaseName = "testSetAccount_number";
        a.setAccount_number(newValue);
        if (newValue.equals(a.getAccount_number()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testGetUsername_of_account_holder() {
        Account a = buildAccountFixture();
        String expected = "abc@gmail.com";
        String testCaseName = "testGetUsername_of_account_holder";
        if (expected.equals(a.getUsername_of_account_holder()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testSetUsername_of_account_holder() {
        Account a = buildAccountFixture();
        String newValue = "new@gmail.com";
        String testCaseName = "testSetUsername_of_account_holder";
        a.setUsername_of_account_holder(newValue);
        if (newValue.equals(a.getUsername_of_account_holder()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testGetAccount_type() {
        Account a = buildAccountFixture();
        String expected = "Standard";
        String testCaseName = "testGetAccount_type";
        if (expected.equals(a.getAccount_type()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testSetAccount_type() {
        Account a = buildAccountFixture();
        String newValue = "Saving";
        String testCaseName = "testSetAccount_type";
        a.setAccount_type(newValue);
        if (newValue.equals(a.getAccount_type()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testGetAccount_opening_date() {
        Account a = buildAccountFixture();
        Date expected = new Date(0);
        String testCaseName = "testGetAccount_opening_date";
        if (expected.equals(a.getAccount_opening_date()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testSetAccount_opening_date() {
        Account a = buildAccountFixture();
        Date newValue = new Date(1000);
        String testCaseName = "testSetAccount_opening_date";
        a.setAccount_opening_date(newValue);
        if (newValue.equals(a.getAccount_opening_date()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void testToString() {
        Account a = buildAccountFixture();
        String s = a.toString();
        String testCaseName = "testToString";
        if (s != null && s.length() > 0)
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void main(String[] args) {
        testAccountConstructor();
        testGetAccount_number();
        testSetAccount_number();
        testGetUsername_of_account_holder();
        testSetUsername_of_account_holder();
        testGetAccount_type();
        testSetAccount_type();
        testGetAccount_opening_date();
        testSetAccount_opening_date();
        testToString();
    }
}

