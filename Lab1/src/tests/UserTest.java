package tests;

import model.User;
import utils.TestUtils;

public class UserTest {

    public static void testUserConstructor() {

        String username = "abc@gmail.com";
        String password = "pw123";
        String firstName = "Alice";
        String lastName = "Brown";
        String mobile = "07123456789";

        User u = new User(username, password, firstName, lastName, mobile);
        
        assert username.equals(u.getUsername());
        assert password.equals(u.getPassword());
        assert firstName.equals(u.getFirst_name());
        assert lastName.equals(u.getLast_name());
        assert mobile.equals(u.getMobile_number());
        
        String testCaseName;

        testCaseName = "testUserConstructor: username";
        if (username.equals(u.getUsername()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);

        testCaseName = "testUserConstructor: password";
        if (password.equals(u.getPassword()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);

        testCaseName = "testUserConstructor: firstName";
        if (firstName.equals(u.getFirst_name()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);

        testCaseName = "testUserConstructor: lastName";
        if (lastName.equals(u.getLast_name()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);

        testCaseName = "testUserConstructor: mobile";
        if (mobile.equals(u.getMobile_number()))
            TestUtils.printTestPassed(testCaseName);
        else
            TestUtils.printTestFailed(testCaseName);
    }

    public static void main(String[] args) {
        testUserConstructor();
    }
}


