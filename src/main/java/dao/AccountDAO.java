package dao;

import model.Account;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private final Connection conn;

    public AccountDAO() {
        this.conn = DBConnection.getConnection();
    }

    public boolean createAccount(Account account) {
        String sql = "INSERT INTO accounts(account_number, customer_id, balance, account_type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, account.getAccountNumber());
            ps.setInt(2, account.getCustomerId());
            ps.setDouble(3, account.getBalance());
            ps.setString(4, account.getAccountType());
            int affected = ps.executeUpdate();
            if (affected == 0) return false;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) account.setAccountId(keys.getInt(1));
            }
            return true;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public Account getAccountByNumber(String accountNumber) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getInt("account_id"),
                            rs.getString("account_number"),
                            rs.getInt("customer_id"),
                            rs.getDouble("balance"),
                            rs.getString("account_type"),
                            rs.getString("created_at")
                    );
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean updateBalance(String accountNumber, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setString(2, accountNumber);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Account> getAllAccounts() {
        List<Account> out = new ArrayList<>();
        String sql = "SELECT * FROM accounts ORDER BY account_id DESC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                out.add(new Account(
                        rs.getInt("account_id"),
                        rs.getString("account_number"),
                        rs.getInt("customer_id"),
                        rs.getDouble("balance"),
                        rs.getString("account_type"),
                        rs.getString("created_at")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return out;
    }
}
