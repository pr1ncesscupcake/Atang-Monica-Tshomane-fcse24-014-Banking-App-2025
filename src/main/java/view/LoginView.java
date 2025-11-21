package view;

import controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import util.Constants;

public class LoginView {
    private final LoginController controller = new LoginController();

    public Scene getScene(Stage stage) {
        Label title = new Label("Banking System");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button loginBtn = new Button("Login");
        Label message = new Label();

        loginBtn.setOnAction(e -> {
            String u = username.getText().trim();
            String p = password.getText().trim();
            if (u.isEmpty() || p.isEmpty()) {
                message.setText("Enter username and password.");
                return;
            }
            User user = controller.login(u, p);
            if (user == null) {
                message.setText("Invalid credentials.");
                return;
            }
            switch (user.getRole()) {
                case Constants.ROLE_ADMIN -> {
                    // for simplicity we reuse Employee dashboard for admin operations if needed
                    EmployeeDashboardView ev = new EmployeeDashboardView();
                    stage.setScene(ev.getScene(stage));
                }
                case Constants.ROLE_EMPLOYEE -> {
                    EmployeeDashboardView ev = new EmployeeDashboardView();
                    stage.setScene(ev.getScene(stage));
                }
                case Constants.ROLE_CUSTOMER -> {
                    CustomerDashboardView cv = new CustomerDashboardView(user);
                    stage.setScene(cv.getScene(stage));
                }
                default -> message.setText("Unknown role.");
            }
        });

        VBox root = new VBox(10, title, username, password, loginBtn, message);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        return new Scene(root, 420, 300);
    }
}
