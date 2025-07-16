import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ATMApp {
    public static void main(String[] args) {
        ATMSystem atmSystem = new ATMSystem();
        Account userAccount = null;

        while (userAccount == null) {
            userAccount = atmSystem.authenticateUser();
        }

        ATM atm = new ATM(userAccount);
        atm.showMenu();
    }
}

class ATM {
    private Account currentAccount;

    public ATM(Account account) {
        this.currentAccount = account;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void showTransactionHistory() {
        currentAccount.displayTransactionHistory();
    }

    private void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        currentAccount.withdraw(amount);
    }

    private void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        currentAccount.deposit(amount);
    }

    private void transfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter recipient account ID: ");
        String recipientId = scanner.next();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        currentAccount.transfer(amount, recipientId);
    }
}


class Account {
    private String accountId;
    private String pin;
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public Account(String accountId, String pin, double initialBalance) {
        this.accountId = accountId;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdraw", amount));
            System.out.println("Successfully withdrew $" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void transfer(double amount, String recipientId) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Transfer to " + recipientId, amount));
            System.out.println("Successfully transferred $" + amount + " to Account ID: " + recipientId);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void displayTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
        System.out.println("Current balance: $" + balance);
    }

    public String getAccountId() {
        return accountId;
    }
}


class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + ": $" + amount;
    }
}

class ATMSystem {
    private HashMap<String, Account> accounts;

    public ATMSystem() {
        accounts = new HashMap<>();
        accounts.put("1001", new Account("1001", "1234", 5000.0));
        accounts.put("1002", new Account("1002", "5678", 3000.0));
    }

    public Account authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID: ");
        String userId = scanner.next();
        System.out.print("Enter user PIN: ");
        String pin = scanner.next();

        if (accounts.containsKey(userId) && accounts.get(userId).authenticate(pin)) {
            System.out.println("Authentication successful!");
            return accounts.get(userId);
        } else {
            System.out.println("Invalid user ID or PIN. Please try again.");
            return null;
        }
    }
}
