package tests;

import app.SimpleBankingApp;
import utils.TestUtils;

public class SimpleBankingAppTest {

    
	// system under test (SUT):
	//static SimpleBankingApp mainApp = new SimpleBankingApp ();

	// this test method (test case) verifies if the data load feature of the class (unit or component) 
	// under test (UUT) works properly
	public static void testLoadData() {
		// Reminder: the classical Four-Phase test pattern (Setup-Exercise-Verify-Teardown
		// http://xunitpatterns.com/Four%20Phase%20Test.html 
		
		// 1-Setup phase: none
		
		// 2-Exercise phase
		SimpleBankingApp.users = controller.UserController.loadUserData();

		// 3-Verify phase
		// we see in the load function of the UUT that we have loaded 3 users, so let's verify that
		if (SimpleBankingApp.users.size() == 3)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "testLoadData: loadUserData: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "testLoadData: loadUserData: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);

		// The above only verification is basic (simple, weak) 
		// To do STRONGER verification, we would need more assertions for user names and account balances, etc.
		
		SimpleBankingApp.accounts = controller.AccountController.loadAccountData();
		if (SimpleBankingApp.accounts.size() == 4)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "testLoadData: loadAccountData: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "testLoadData: loadAccountData: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);
		
		// 4-Teardown phase: if our goal was to only test the load, as Teardown (mainApp.accounts)
		// we would have deleted the loaded deleted from memory (variables users, and accounts), but we want
		// to use those data in the other tests, thus, we do not do any Teardown in this test case
	}
	
	// this test method (test case) verifies if the Deposit feature works properly
	public static void testDeposits() {
		// 1-Setup phase
		double balanceBefore = SimpleBankingApp.getBalance("5495-1234"); 
		double depositAmount = 50.21;
		
		// 2-Exercise phase
		SimpleBankingApp.addTransaction("5495-1234", depositAmount);
		double balanceAfter = SimpleBankingApp.getBalance("5495-1234");
		
		// 3-verify
		assert balanceBefore + depositAmount == balanceAfter;
		if (balanceBefore + depositAmount == balanceAfter)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "testDeposits: TC1 passed"+ TestUtils.TEXT_COLOR_RESET);
		else {
			System.out.println(TestUtils.TEXT_COLOR_RED + "testDeposits: TC1 FAILED XXX: balanceBefore + depositAmount != balanceAfter");
			System.out.format("testDeposits: balanceBefore = %.2f ; depositAmount = %.2f ; balanceAfter = %.2f %s\n", 
					balanceBefore , depositAmount , balanceAfter, TestUtils.TEXT_COLOR_RESET);
		}
		
		// 4-tear-down: put the system state back in where it was
		// read more about the tear-down phase of test cases: http://xunitpatterns.com/Four%20Phase%20Test.html
		SimpleBankingApp.addTransaction("5495-1234", -depositAmount);
	}

	// this test method (test case) verifies if the Withdraw feature works properly
	// TODO
	public static void testWithdrawals() {
    // 1-Setup phase
    double balanceBefore = SimpleBankingApp.getBalance("5495-1234");
    double withdrawalAmount = 20.00;

    // 2-Exercise phase
    SimpleBankingApp.addTransaction("5495-1234", -withdrawalAmount);
    double balanceAfter = SimpleBankingApp.getBalance("5495-1234");

    // 3-Verify
    if (balanceBefore - withdrawalAmount == balanceAfter) {
        System.out.println(TestUtils.TEXT_COLOR_GREEN + "testWithdrawals: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
    } else {
        System.out.println(TestUtils.TEXT_COLOR_RED + "testWithdrawals: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);
        System.out.format("balanceBefore=%.2f withdrawal=%.2f balanceAfter=%.2f %s\n",
                balanceBefore, withdrawalAmount, balanceAfter, TestUtils.TEXT_COLOR_RESET);
    }

    // 4-Teardown: revert the withdrawal so state is restored
    SimpleBankingApp.addTransaction("5495-1234", withdrawalAmount);
	}

	public static void testRejectZeroAmountTransaction() {
		String tc = "testRejectZeroAmountTransaction";
		try {
			SimpleBankingApp.addTransaction("5495-1234", 0.0);
			TestUtils.printTestFailed(tc, "Expected IllegalArgumentException for zero amount");
		} catch (IllegalArgumentException ex) {
			TestUtils.printTestPassed(tc);
		}
	}

	public static void testRejectInvalidAccountTransaction() {
		String tc = "testRejectInvalidAccountTransaction";
		try {
			SimpleBankingApp.addTransaction("9999-1111", 10.0);
			TestUtils.printTestFailed(tc, "Expected IllegalArgumentException for invalid account");
		} catch (IllegalArgumentException ex) {
			TestUtils.printTestPassed(tc);
		}
	}

	public static void testGetTransactionsForAccount() {
		String tc = "testGetTransactionsForAccount";
	
		// Ensure clean state
		SimpleBankingApp.transactions.clear();
		SimpleBankingApp.users = controller.UserController.loadUserData();
		SimpleBankingApp.accounts = controller.AccountController.loadAccountData();
	
		// Add known transactions
		SimpleBankingApp.addTransaction("5495-1234", 10.0);
		SimpleBankingApp.addTransaction("5495-1234", -5.0);
		SimpleBankingApp.addTransaction("5495-1239", 99.0);
	
		java.util.Vector<model.Transaction> tx =
				controller.AccountController.getTransactionsForAccount(
						SimpleBankingApp.transactions,
						"5495-1234"
				);
	
		if (tx.size() == 2) {
			utils.TestUtils.printTestPassed(tc);
		} else {
			utils.TestUtils.printTestFailed(tc, "Expected 2 but got " + tx.size());
		}
	}

	private static void resetAppState() {
		SimpleBankingApp.users = controller.UserController.loadUserData();
		SimpleBankingApp.accounts = controller.AccountController.loadAccountData();
		SimpleBankingApp.transactions.clear();
	}
	
	
	public static void main(String[] args) {
		resetAppState();
		testLoadData();
		testDeposits();
		testWithdrawals();
	
		resetAppState();
		testRejectZeroAmountTransaction();
	
		resetAppState();
		testRejectInvalidAccountTransaction();
	
		resetAppState();
		testGetTransactionsForAccount();
	}
}
