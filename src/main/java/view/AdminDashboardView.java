package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.User;
import util.Constants;

public class AdminDashboardView {

    public AdminDashboardView() {
    }

    public Scene getScene(Stage stage) {
        Label header = new Label("Admin Dashboard");
        header.setStyle("-fx-background-color: " + Constants.THEME_PRIMARY + "; -fx-text-fill: " + Constants.THEME_TEXT
                + "; -fx-padding: 10px; -fx-font-size:16px;");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.CENTER);

        Button manageUsersBtn = new Button("Manage Users");
        Button viewReportsBtn = new Button("View Reports");
        Button logoutBtn = new Button("Logout");

        manageUsersBtn.setMaxWidth(Double.MAX_VALUE);
        viewReportsBtn.setMaxWidth(Double.MAX_VALUE);
        logoutBtn.setMaxWidth(Double.MAX_VALUE);

        logoutBtn.setOnAction(e -> {
            LoginView loginView = new LoginView();
            stage.setScene(loginView.getScene(stage));
        });

        VBox content = new VBox(10, manageUsersBtn, viewReportsBtn, logoutBtn);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(content);
        root.setStyle("-fx-background-color: " + Constants.THEME_BG + "; -fx-padding: 10px;");

        return new Scene(root, 600, 400);
    }
}
