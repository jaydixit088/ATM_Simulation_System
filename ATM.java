import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private int pin;
    private double balance;

    public Account(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class ATM {
    private Map<Integer, Account> accounts;
    private Scanner scanner;

    public ATM() {
        this.accounts = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void addAccount(int accountNumber, int pin, double balance) {
        accounts.put(accountNumber, new Account(accountNumber, pin, balance));
    }

    public void start() {
        while (true) {
            System.out.println("Enter account number:");
            int accountNumber = scanner.nextInt();
            System.out.println("Enter PIN:");
            int pin = scanner.nextInt();
    
            if (accounts.containsKey(accountNumber)) {
                Account account = accounts.get(accountNumber);
                if (account.getPin() == pin) {
                    System.out.println("Login successful!");
                    if (mainMenu(account)) {
                        break; // Exit the loop if mainMenu returns true
                    }
                } else {
                    System.out.println("Invalid PIN. Please try again.");
                }
            } else {
                System.out.println("Account not found. Please try again.");
            }
        }
    }

    private boolean mainMenu(Account account) {
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Withdrawal");
            System.out.println("2. Deposit");
            System.out.println("3. Balance Inquiry");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    withdrawal(account);
                    break;
                case 2:
                    deposit(account);
                    break;
                case 3:
                    balanceInquiry(account);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return true; // Return true to exit the loop
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void withdrawal(Account account) {
        System.out.println("Enter withdrawal amount:");
        double amount = scanner.nextDouble();

        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds. Please try again.");
        } else {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
        }
    }

    private void deposit(Account account) {
        System.out.println("Enter deposit amount:");
        double amount = scanner.nextDouble();

        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance());
    }

    private void balanceInquiry(Account account) {
        System.out.println("Your current balance is: " + account.getBalance());
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.addAccount(1234, 1234, 1000.0);
        atm.addAccount(5678, 5678, 500.0);
        atm.start();
    }
}