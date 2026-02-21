package tests;

import model.User;
import utils.TestUtils;

public class UserTests {

    public static void testUserConstructor() {
        // Setup
        String test_username = "mike@gmail.com";
        String test_password = "my_passwd";
        String test_first_name = "Mike";
        String test_last_name = "Smith";
        String test_mobile_number = "07771234567";

        // Exercise
        User testUser = new User(test_username, test_password, test_first_name, test_last_name, test_mobile_number);

        // Verify (TODO1: 5 getter checks using if/else)
        String tc;

        tc = "TC1-getUsername";
        if (testUser.getUsername().equals(test_username)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "Expected " + test_username + " but got " + testUser.getUsername());

        tc = "TC2-getPassword";
        if (testUser.getPassword().equals(test_password)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "Expected " + test_password + " but got " + testUser.getPassword());

        tc = "TC3-getFirst_name";
        if (testUser.getFirst_name().equals(test_first_name)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "Expected " + test_first_name + " but got " + testUser.getFirst_name());

        tc = "TC4-getLast_name";
        if (testUser.getLast_name().equals(test_last_name)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "Expected " + test_last_name + " but got " + testUser.getLast_name());

        tc = "TC5-getMobile_number";
        if (testUser.getMobile_number().equals(test_mobile_number)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "Expected " + test_mobile_number + " but got " + testUser.getMobile_number());

        // TODO5: assertions (enable -ea in Run Configurations)
        assert testUser.getUsername().equals(test_username);
        assert testUser.getPassword().equals(test_password);
        assert testUser.getFirst_name().equals(test_first_name);
        assert testUser.getLast_name().equals(test_last_name);
        assert testUser.getMobile_number().equals(test_mobile_number);

        System.out.println("All Java assertions passed (none failed).");
    }

    public static void main(String[] args) {
        // TODO2: constructor tests moved out of main into method
        testUserConstructor();
    }
}