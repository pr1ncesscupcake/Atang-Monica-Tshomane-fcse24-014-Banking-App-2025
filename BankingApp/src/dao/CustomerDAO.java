package dao;

import com.banking.db.DatabaseConfig;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Data Access Object for Customer entities.
 * Handles persistence operations (CRUD) for Customer objects.
 */
public class CustomerDAO {

    private static final String INSERT_CUSTOMER_SQL =
            "INSERT INTO customers (customer_id, first_name, last_name, address) VALUES (?, ?, ?, ?)";

    /**
     * Saves a new Customer to the database.
     * @param customer The customer object to save.
     * @return true if save was successful, false otherwise.
     */
    public boolean save(Customer customer) {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_CUSTOMER_SQL)) {

            ps.setString(1, customer.getCustomerId());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getLastName());
            ps.setString(4, customer.getAddress());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            // Error code 1062 is for Duplicate Entry (Primary Key violation)
            if (e.getErrorCode() == 1062) {
                // If customer already exists, consider it a success for demonstration
                return true;
            }
            System.err.println("Error saving customer " + customer.getCustomerId() + ": " + e.getMessage());
            return false;
        }
    }
}

