package model;

/**
 * Investment Account with a higher interest rate.
 */
public class InvestmentAccount extends Account {
    private static final double BASE_INTEREST_RATE = 0.08; // 8%

    public InvestmentAccount(String accountNumber, Customer customer, String branch, double initialDeposit) {
        super(accountNumber, customer, branch, initialDeposit);
    }

    // Constructor used by DAO read operation
    public InvestmentAccount(String accountNumber, Customer customer, String branch, double balance) {
        super(accountNumber, customer, branch, balance);
    }

    @Override
    public void calculateInterest() {
        double interest = this.balance * BASE_INTEREST_RATE;
        this.balance += interest;
        System.out.printf("[Investment] Interest added: $%.2f%n", interest);
    }
}
