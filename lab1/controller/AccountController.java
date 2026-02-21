package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import model.Account;
import model.Transaction;

public class AccountController {

    public static Vector<Account> loadAccountData() {
        Vector<Account> accounts = new Vector<>();
        try {
            accounts.add(new Account("5495-1234", "mike", "Standard", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019")));
            accounts.add(new Account("5495-1239", "mike", "Standard", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2020")));
            accounts.add(new Account("5495-1291", "mike", "Saving", new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2019")));
            accounts.add(new Account("5495-6789", "David.McDonald@gmail.com", "Saving", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // TODO11 main move-method: getBalance moved here
    public static double getBalance(Vector<Transaction> transactions, String account_number) {
        double total = 0.0;

        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            if (t.getAccount_number().equals(account_number)) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public static java.util.Vector<model.Transaction> getTransactionsForAccount(
        java.util.Vector<model.Transaction> transactions,
        String accountNumber
) {
    java.util.Vector<model.Transaction> results = new java.util.Vector<>();

    for (int i = 0; i < transactions.size(); i++) {
        model.Transaction t = transactions.get(i);
        if (t.getAccount_number().equals(accountNumber)) {
            results.add(t);
        }
    }

    return results;
}
}