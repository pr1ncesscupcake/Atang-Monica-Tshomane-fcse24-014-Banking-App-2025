package dao;

import model.LoginCredentials;
import util.DBConnection;

import java.sql.*;

public class LoginDAO {

    // VALIDATE LOGIN
    public boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // true if match found

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ADD USER
    public void addUser(LoginCredentials user) {
        String sql = "INSERT INTO users(username, password) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

