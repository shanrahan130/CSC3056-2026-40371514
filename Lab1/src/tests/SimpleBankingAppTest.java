package tests;

import app.SimpleBankingApp;
import utils.TestUtils;

public class SimpleBankingAppTest {

    public static void testLoadData() {

        SimpleBankingApp.loadUserData();

        if (SimpleBankingApp.users.size() == 3)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testLoadData: loadUserData: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "testLoadData: loadUserData: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);

        SimpleBankingApp.loadAccountData();

        if (SimpleBankingApp.accounts.size() == 4)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testLoadData: loadAccountData: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "testLoadData: loadAccountData: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);
    }

    public static void testDeposits() {

        double balanceBefore = SimpleBankingApp.getBalance("5495-1234");
        double depositAmount = 50.21;

        SimpleBankingApp.addTransaction("5495-1234", depositAmount);
        double balanceAfter = SimpleBankingApp.getBalance("5495-1234");

        assert balanceBefore + depositAmount == balanceAfter;

        if (balanceBefore + depositAmount == balanceAfter)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testDeposits: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "testDeposits: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);

        // teardown
        SimpleBankingApp.addTransaction("5495-1234", -depositAmount);
    }

    public static void testWithdrawals() {

        double balanceBefore = SimpleBankingApp.getBalance("5495-1234");
        double withdrawalAmount = 10.00;

        SimpleBankingApp.addTransaction("5495-1234", -withdrawalAmount);
        double balanceAfter = SimpleBankingApp.getBalance("5495-1234");

        assert balanceBefore - withdrawalAmount == balanceAfter;

        if (balanceBefore - withdrawalAmount == balanceAfter)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testWithdrawals: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "testWithdrawals: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);

        // teardown
        SimpleBankingApp.addTransaction("5495-1234", withdrawalAmount);
    }

    // Feature 1 test: addTransaction rejects zero amount
    public static void testAddTransactionRejectsZeroAmount() {

        boolean threw = false;

        try {
            SimpleBankingApp.addTransaction("5495-1234", 0.0);
        } catch (IllegalArgumentException ex) {
            threw = true;
        }

        assert threw;

        if (threw)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testAddTransactionRejectsZeroAmount: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "testAddTransactionRejectsZeroAmount: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);
    }

    // Feature 2 test: getBalance rejects non-existent account number
    public static void testGetBalanceRejectsUnknownAccount() {

        boolean threw = false;

        try {
            SimpleBankingApp.getBalance("9999-1111");
        } catch (IllegalArgumentException ex) {
            threw = true;
        }

        assert threw;

        if (threw)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testGetBalanceRejectsUnknownAccount: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "testGetBalanceRejectsUnknownAccount: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);
    }

    // Feature 3 test: total balance across user's accounts
    public static void testTotalBalanceForUser() {

        // Setup: ensure known starting point (no transactions assumed after teardown in earlier tests)

        // pick mike's accounts: from loadAccountData, mike has 3 accounts:
        // 5495-1234, 5495-1239, 5495-1291
        // add transactions across two of them and verify total.

        double before = SimpleBankingApp.getTotalBalanceForUser("mike");

        SimpleBankingApp.addTransaction("5495-1234", 100.00);
        SimpleBankingApp.addTransaction("5495-1291", 40.00);

        double after = SimpleBankingApp.getTotalBalanceForUser("mike");

        assert before + 140.00 == after;

        if (before + 140.00 == after)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testTotalBalanceForUser: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "testTotalBalanceForUser: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);

        // teardown
        SimpleBankingApp.addTransaction("5495-1234", -100.00);
        SimpleBankingApp.addTransaction("5495-1291", -40.00);
    }
    
    
 // Feature 4 test: retrieve all transactions for an account
    
    public static void testGetTransactionsForAccount() {

        // Setup
        double amount1 = 25.00;
        double amount2 = -10.00;

        SimpleBankingApp.addTransaction("5495-1234", amount1);
        SimpleBankingApp.addTransaction("5495-1234", amount2);

        // Execute
        java.util.Vector<model.Transaction> txs =
                SimpleBankingApp.getTransactionsForAccount("5495-1234");

        boolean found1 = false;
        boolean found2 = false;

        for (model.Transaction t : txs) {
            if (t.getAmount() == amount1) found1 = true;
            if (t.getAmount() == amount2) found2 = true;
        }

        boolean passed = found1 && found2;

        assert passed;

        if (passed)
            System.out.println(utils.TestUtils.TEXT_COLOR_GREEN +
                    "testGetTransactionsForAccount: TC1 passed" +
                    utils.TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(utils.TestUtils.TEXT_COLOR_RED +
                    "testGetTransactionsForAccount: TC1 FAILED" +
                    utils.TestUtils.TEXT_COLOR_RESET);

        // Teardown
        SimpleBankingApp.addTransaction("5495-1234", -amount1);
        SimpleBankingApp.addTransaction("5495-1234", -amount2);
    }

    public static void main(String[] args) {

        testLoadData();
        testDeposits();
        testWithdrawals();

        testAddTransactionRejectsZeroAmount();
        testGetBalanceRejectsUnknownAccount();
        testTotalBalanceForUser();
        testGetTransactionsForAccount();
    }
}
		