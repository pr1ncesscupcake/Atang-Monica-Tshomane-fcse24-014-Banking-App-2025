package controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {

    @Test
    void testLoginSuccess() {
        LoginController controller = new LoginController();

        boolean result = controller.login("admin", "admin123");

        assertTrue(result);
    }

    @Test
    void testLoginFail() {
        LoginController controller = new LoginController();

        boolean result = controller.login("wrong", "wrong");

        assertFalse(result);
    }
}
