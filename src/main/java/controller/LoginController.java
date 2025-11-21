package controller;

import dao.UserDAO;
import model.User;

public class LoginController {
    private final UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }
}
