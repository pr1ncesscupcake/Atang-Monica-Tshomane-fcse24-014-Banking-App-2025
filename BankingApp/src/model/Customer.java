package model;

/**
 * Represents a customer of the bank.
 */
public class Customer {
    private final String customerId;
    private final String firstName;
    private final String lastName;
    private final String address;

    public Customer(String customerId, String firstName, String lastName, String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    // Getters
    public String getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("Customer [ID: %s, Name: %s %s]", customerId, firstName, lastName);
    }
}
