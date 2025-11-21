package view;

import controller.CustomerController;
import dao.AccountDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Account;
import model.User;

import java.util.List;

public class CustomerDashboardView {
    private final User user;
    private final CustomerController controller = new CustomerController();

    public CustomerDashboardView(User user) { this.user = user; }

    public Scene getScene(Stage stage) {
        Label title = new Label("Customer Dashboard");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextField acctField = new TextField();
        acctField.setPromptText("Account Number");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        Button listBtn = new Button("My Accounts");
        Button logoutBtn = new Button("Logout");
        Label msg = new Label();

        depositBtn.setOnAction(e -> {
            String acct = acctField.getText().trim();
            double amt;
            try { amt = Double.parseDouble(amountField.getText().trim()); } catch (Exception ex) { msg.setText("Enter valid amount"); return; }
            boolean ok = controller.deposit(acct, amt);
            msg.setText(ok ? "Deposit successful" : "Deposit failed");
        });

        withdrawBtn.setOnAction(e -> {
            String acct = acctField.getText().trim();
            double amt;
            try { amt = Double.parseDouble(amountField.getText().trim()); } catch (Exception ex) { msg.setText("Enter valid amount"); return; }
            boolean ok = controller.withdraw(acct, amt);
            msg.setText(ok ? "Withdraw successful" : "Withdraw failed");
        });

        listBtn.setOnAction(e -> {
            // fetch accounts by searching all accounts for those owned by this customer's id
            // First, need customer id from username. Here assume username is "cust{customerId}"
            String username = user.getUsername();
            int custId = -1;
            if (username.startsWith("cust")) {
                try { custId = Integer.parseInt(username.substring(4)); } catch (Exception ignored) {}
            }
            if (custId == -1) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Cannot resolve your customer ID from username.");
                a.showAndWait();
                return;
            }
            AccountDAO ada = new AccountDAO();
            List<Account> all = ada.getAllAccounts();
            StringBuilder sb = new StringBuilder();
            for (Account a : all) {
                if (a.getCustomerId() == custId) {
                    sb.append("Acct#: ").append(a.getAccountNumber()).append(" | Type: ").append(a.getAccountType()).append(" | Bal: ").append(a.getBalance()).append("\n");
                }
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("My Accounts");
            a.setHeaderText("Accounts for customer " + custId);
            a.getDialogPane().setContent(new TextArea(sb.toString()));
            a.showAndWait();
        });

        logoutBtn.setOnAction(e -> {
            LoginView lv = new LoginView();
            stage.setScene(lv.getScene(stage));
        });

        VBox root = new VBox(10, title, acctField, amountField, new HBox(10, depositBtn, withdrawBtn), listBtn, msg, logoutBtn);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        return new Scene(root, 700, 450);
    }
}
