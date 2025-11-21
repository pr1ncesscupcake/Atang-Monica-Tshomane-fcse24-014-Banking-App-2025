package dao;

import model.Transaction;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private final Connection conn;

    public TransactionDAO() {
        this.conn = DBConnection.getConnection();
    }

    public boolean addTransaction(Transaction tx) {
        String sql = "INSERT INTO transactions(account_id, amount, transaction_type, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, tx.getAccountId());
            ps.setDouble(2, tx.getAmount());
            ps.setString(3, tx.getTransactionType());
            ps.setString(4, tx.getDescription());
            int affected = ps.executeUpdate();
            if (affected == 0)
                return false;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    // optional: set generated id on tx if you had setter
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Transaction> getTransactionsByAccount(int accountId) {
        List<Transaction> out = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_id = ? ORDER BY created_at DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Transaction(
                            rs.getInt("transaction_id"),
                            rs.getInt("account_id"),
                            rs.getDouble("amount"),
                            rs.getString("transaction_type"),
                            rs.getString("description"),
                            rs.getString("created_at")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }
}
