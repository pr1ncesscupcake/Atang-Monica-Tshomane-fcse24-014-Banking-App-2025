package view;

import controller.CustomerController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerView {

    private CustomerController controller = new CustomerController();

    public Scene getView(Stage stage) {

        TextField idField = new TextField();
        idField.setPromptText("Customer ID");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");

        Button saveBtn = new Button("Add Customer");
        Button backBtn = new Button("Back");

        Label message = new Label();

        saveBtn.setOnAction(e -> {
            controller.createCustomer(nameField.getText(), emailField.getText(), phoneField.getText());
            message.setText("Customer added!");
        });

        backBtn.setOnAction(e -> {
            DashboardView db = new DashboardView();
            stage.setScene(db.getView(stage));
        });

        VBox root = new VBox(15, idField, nameField, emailField, phoneField, saveBtn, backBtn, message);
        root.setPadding(new Insets(20));

        return new Scene(root, 400, 400);
    }
}
