package tests;

import java.text.SimpleDateFormat;
import model.Transaction;
import utils.TestUtils;

public class TransactionTest {

    public static void testConstructorAndGetters() throws Exception {
        String accNum = "5495-1234";
        double amt = 50.21;
        var date = new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019");

        Transaction t = new Transaction(accNum, amt, date);

        String tc;

        tc = "Transaction-TC1-getAccount_number";
        if (t.getAccount_number().equals(accNum)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "Mismatch");

        tc = "Transaction-TC2-getAmount";
        if (t.getAmount() == amt) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "Mismatch");

        tc = "Transaction-TC3-getTransaction_date";
        if (t.getTransaction_date().equals(date)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "Mismatch");
    }

    public static void testSetters() throws Exception {
        var date1 = new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019");
        var date2 = new SimpleDateFormat("dd/MM/yyyy").parse("21/08/2020");

        Transaction t = new Transaction("A", 1.0, date1);

        t.setAccount_number("B");
        t.setAmount(-20.0);
        t.setTransaction_date(date2);

        String tc;

        tc = "Transaction-Setters-TC1";
        if (t.getAccount_number().equals("B")) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "account_number not set");

        tc = "Transaction-Setters-TC2";
        if (t.getAmount() == -20.0) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "amount not set");

        tc = "Transaction-Setters-TC3";
        if (t.getTransaction_date().equals(date2)) TestUtils.printTestPassed(tc);
        else TestUtils.printTestFailed(tc, "date not set");
    }

    public static void main(String[] args) throws Exception {
        testConstructorAndGetters();
        testSetters();
    }
}