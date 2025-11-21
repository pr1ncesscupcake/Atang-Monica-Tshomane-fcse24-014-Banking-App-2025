package controller;

import dao.UserDAO;
import model.User;

public class AdminController {
    private final UserDAO userDAO = new UserDAO();

    public boolean createEmployee(String username, String password) {
        if (userDAO.usernameExists(username)) return false;
        return userDAO.register(new User(username, password, "EMPLOYEE"));
    }
}
