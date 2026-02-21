package tests;

import java.text.SimpleDateFormat;
import model.Account;
import utils.TestUtils;

public class AccountTest {

    public static void testAccountConstructorAndGetters() throws Exception {
        String accNum = "5495-1234";
        String username = "mike@gmail.com";
        String type = "Standard";
        var date = new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019");

        Account a = new Account(accNum, username, type, date);

        String tc;

        tc = "Account-TC1-getAccount_number";
        if (a.getAccount_number().equals(accNum)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "Mismatch");

        tc = "Account-TC2-getUsername_of_account_holder";
        if (a.getUsername_of_account_holder().equals(username)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "Mismatch");

        tc = "Account-TC3-getAccount_type";
        if (a.getAccount_type().equals(type)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "Mismatch");

        tc = "Account-TC4-getAccount_opening_date";
        if (a.getAccount_opening_date().equals(date)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "Mismatch");
    }

    public static void testSetters() throws Exception {
        var date1 = new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019");
        var date2 = new SimpleDateFormat("dd/MM/yyyy").parse("21/08/2020");

        Account a = new Account("A", "U", "Standard", date1);

        a.setAccount_number("B");
        a.setUsername_of_account_holder("U2");
        a.setAccount_type("Saving");
        a.setAccount_opening_date(date2);

        String tc;

        tc = "Account-Setters-TC1";
        if (a.getAccount_number().equals("B")) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "account_number not set");

        tc = "Account-Setters-TC2";
        if (a.getUsername_of_account_holder().equals("U2")) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "username not set");

        tc = "Account-Setters-TC3";
        if (a.getAccount_type().equals("Saving")) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "type not set");

        tc = "Account-Setters-TC4";
        if (a.getAccount_opening_date().equals(date2)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "date not set");
    }

    public static void main(String[] args) throws Exception {
        testAccountConstructorAndGetters();
        testSetters();
    }
}