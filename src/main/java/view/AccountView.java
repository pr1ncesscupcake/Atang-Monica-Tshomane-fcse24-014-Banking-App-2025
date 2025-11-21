package view;

import controller.AccountController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccountView {

    private AccountController controller = new AccountController();

    public Scene getView(Stage stage) {

        TextField accNumberField = new TextField();
        accNumberField.setPromptText("Account Number");

        TextField customerIdField = new TextField();
        customerIdField.setPromptText("Customer ID");

        TextField balanceField = new TextField();
        balanceField.setPromptText("Initial Balance");

        TextField accountTypeField = new TextField();
        accountTypeField.setPromptText("Account Type");

        Button createBtn = new Button("Create Account");
        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        Button backBtn = new Button("Back");

        Label message = new Label();

        createBtn.setOnAction(e -> {
            controller.createAccount(
                    accNumberField.getText(),
                    Integer.parseInt(customerIdField.getText()),
                    Double.parseDouble(balanceField.getText()),
                    accountTypeField.getText());
            message.setText("Account created!");
        });

        depositBtn.setOnAction(e -> {
            controller.deposit(
                    accNumberField.getText(),
                    Double.parseDouble(balanceField.getText()));
            message.setText("Deposit successful!");
        });

        withdrawBtn.setOnAction(e -> {
            controller.withdraw(
                    accNumberField.getText(),
                    Double.parseDouble(balanceField.getText()));
            message.setText("Withdrawal successful!");
        });

        backBtn.setOnAction(e -> {
            DashboardView db = new DashboardView();
            stage.setScene(db.getView(stage));
        });

        VBox root = new VBox(15, accNumberField, customerIdField, balanceField, accountTypeField,
                createBtn, depositBtn, withdrawBtn, backBtn, message);
        root.setPadding(new Insets(20));

        return new Scene(root, 400, 450);
    }
}
