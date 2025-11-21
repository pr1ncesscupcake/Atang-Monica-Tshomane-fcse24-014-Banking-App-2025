package service;

import dao.UserDAO;
import model.User;

public class LoginService {
    private final UserDAO userDAO = new UserDAO();

    public User authenticate(String username, String password) {
        return userDAO.login(username, password);
    }

    public boolean register(User user) {
        if (userDAO.usernameExists(user.getUsername())) return false;
        return userDAO.register(user);
    }
}
