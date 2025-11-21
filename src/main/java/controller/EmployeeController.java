package controller;

import dao.AccountDAO;
import dao.CustomerDAO;
import dao.UserDAO;
import model.Account;
import model.Customer;
import model.User;
import util.Constants;
import util.PasswordUtil;

public class EmployeeController {
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final UserDAO userDAO = new UserDAO();
    private final AccountDAO accountDAO = new AccountDAO();

    public CreatedCustomerResult createCustomerAndUser(String fullName, String email, String phone,
                                                      boolean createSavings, boolean createCheque, boolean createInvestment) {
        try {
            // Create customer
            Customer created = customerDAO.createCustomer(new Customer(fullName, email, phone));
            if (created == null) return new CreatedCustomerResult(null, null, false, "Failed to create customer");

            // username: cust{customerId}
            String username = "cust" + created.getCustomerId();
            String tempPass = PasswordUtil.generateTemporaryPassword(10);

            User user = new User(username, tempPass, Constants.ROLE_CUSTOMER);
            boolean userCreated = userDAO.register(user);
            if (!userCreated) return new CreatedCustomerResult(created, null, false, "Failed to create login user");

            // Create accounts per options
            if (createSavings) {
                String acctNo = generateAccountNumber(created.getCustomerId(), "SAV");
                accountDAO.createAccount(new Account(acctNo, created.getCustomerId(), 0.0, Constants.ACCOUNT_SAVINGS));
            }
            if (createCheque) {
                String acctNo = generateAccountNumber(created.getCustomerId(), "CHQ");
                accountDAO.createAccount(new Account(acctNo, created.getCustomerId(), 0.0, Constants.ACCOUNT_CHEQUE));
            }
            if (createInvestment) {
                String acctNo = generateAccountNumber(created.getCustomerId(), "INV");
                accountDAO.createAccount(new Account(acctNo, created.getCustomerId(), 0.0, Constants.ACCOUNT_INVESTMENT));
            }

            return new CreatedCustomerResult(created, tempPass, true, "OK");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new CreatedCustomerResult(null, null, false, ex.getMessage());
        }
    }

    private String generateAccountNumber(int customerId, String prefix) {
        long tsPart = System.currentTimeMillis() % 1000000L;
        return prefix + customerId + String.format("%06d", tsPart);
    }

    public static class CreatedCustomerResult {
        private final Customer customer;
        private final String temporaryPassword;
        private final boolean success;
        private final String message;

        public CreatedCustomerResult(Customer customer, String temporaryPassword, boolean success, String message) {
            this.customer = customer;
            this.temporaryPassword = temporaryPassword;
            this.success = success;
            this.message = message;
        }

        public Customer getCustomer() { return customer; }
        public String getTemporaryPassword() { return temporaryPassword; }
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
    }
}
