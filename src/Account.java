import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account implements AccountService {

    private int balance = 0;
    private List<Transaction> transactions = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Implementation of the deposit function
    @Override
    public void deposit(int amount) {
        // Calculate date manually for each transaction
        balance += amount;
        transactions.add(new Transaction("deposit", amount, balance, getCurrentDate()));
    }

    // Implementation of the withdraw function
    @Override
    public void withdraw(int amount) {
        balance -= amount;
        transactions.add(new Transaction("withdrawal", -amount, balance, getCurrentDate()));
    }

    // Print the statement
    @Override
    public void printStatement() {
        System.out.println("Date       || Amount || Balance");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            System.out.println(dateFormat.format(t.getDate()) + " || " + t.getAmount() + " || " + t.getBalance());
        }
    }

    // Transaction class to store the type, amount, balance, and date of the transaction
    private class Transaction {
        private String type;
        private int amount;
        private int balance;
        private Date date;

        public Transaction(String type, int amount, int balance, Date date) {
            this.type = type;
            this.amount = amount;
            this.balance = balance;
            this.date = date;
        }

        public Date getDate() {
            return date;
        }

        public int getAmount() {
            return amount;
        }

        public int getBalance() {
            return balance;
        }
    }

    // Simulate a custom date for a deposit
    public void depositOnSpecificDate(int amount, String dateStr) {
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
            balance += amount;
            transactions.add(new Transaction("deposit", amount, balance, date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Simulate a custom date for a withdrawal
    public void withdrawOnSpecificDate(int amount, String dateStr) {
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
            balance -= amount;
            transactions.add(new Transaction("withdrawal", -amount, balance, date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to set the current date when adding a transaction
    private Date getCurrentDate() {
        // Manually setting specific dates for the transactions (for the interface methods)
        return new Date();
    }

    public static void main(String[] args) {
        Account account = new Account();

        // Using the methods depositOnSpecificDate and withdrawOnSpecificDate for specific dates
        account.depositOnSpecificDate(1000, "10-01-2012"); // Deposit on 10-01-2012
        account.depositOnSpecificDate(2000, "13-01-2012"); // Deposit on 13-01-2012
        account.withdrawOnSpecificDate(500, "14-01-2012"); // Withdraw on 14-01-2012

        // Print statement
        account.printStatement();
    }
}
