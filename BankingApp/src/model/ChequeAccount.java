package model;

/**
 * Cheque Account with zero interest and additional employer details.
 */
public class ChequeAccount extends Account {
    private final String employerName;
    private final String employerAddress;

    // Constructor used by Bank factory (initial state)
    public ChequeAccount(String accountNumber, Customer customer, String branch, String employerName, String employerAddress) {
        super(accountNumber, customer, branch, 0.00);
        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    // Constructor used by DAO read operation (with balance and details from DB)
    public ChequeAccount(String accountNumber, Customer customer, String branch, double balance, String employerName, String employerAddress) {
        super(accountNumber, customer, branch, balance);
        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    @Override
    public void calculateInterest() {
        // Cheque accounts typically yield no interest
    }

    // Getters for ChequeAccount-specific details
    public String getEmployerName() {
        return employerName;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }
}
