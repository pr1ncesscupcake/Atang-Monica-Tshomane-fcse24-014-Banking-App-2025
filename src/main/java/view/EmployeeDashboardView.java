package view;

import controller.EmployeeController;
import dao.CustomerDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Customer;

import java.util.List;

public class EmployeeDashboardView {
    private final EmployeeController controller = new EmployeeController();

    public Scene getScene(Stage stage) {
        // Sidebar
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(200);
        Label appLabel = new Label("BankingSys");
        appLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Button btnCreate = new Button("Create Customer");
        Button btnList = new Button("List Customers");
        Button btnAccounts = new Button("List Accounts");
        Button btnLogout = new Button("Logout");
        btnCreate.setMaxWidth(Double.MAX_VALUE);
        btnList.setMaxWidth(Double.MAX_VALUE);
        btnAccounts.setMaxWidth(Double.MAX_VALUE);
        btnLogout.setMaxWidth(Double.MAX_VALUE);
        sidebar.getChildren().addAll(appLabel, btnCreate, btnList, btnAccounts, new Separator(), btnLogout);

        // Content area (Create Customer form)
        GridPane content = new GridPane();
        content.setPadding(new Insets(20));
        content.setVgap(10);
        content.setHgap(10);

        Label header = new Label("Create Customer");
        header.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        TextField nameField = new TextField();
        nameField.setPromptText("Full name");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");

        CheckBox cbSavings = new CheckBox("Savings");
        CheckBox cbCheque = new CheckBox("Cheque");
        CheckBox cbInvest = new CheckBox("Investment");

        Button saveBtn = new Button("Save");
        Label msg = new Label();

        content.add(header, 0, 0, 2, 1);
        content.add(new Label("Name"), 0, 1);
        content.add(nameField, 1, 1);
        content.add(new Label("Email"), 0, 2);
        content.add(emailField, 1, 2);
        content.add(new Label("Phone"), 0, 3);
        content.add(phoneField, 1, 3);
        content.add(new Label("Accounts to create"), 0, 4);
        HBox checks = new HBox(10, cbSavings, cbCheque, cbInvest);
        content.add(checks, 1, 4);
        content.add(saveBtn, 1, 5);
        content.add(msg, 1, 6);

        saveBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            if (name.isEmpty() || email.isEmpty()) {
                msg.setText("Name and email required.");
                return;
            }
            EmployeeController.CreatedCustomerResult res = controller.createCustomerAndUser(
                    name, email, phone, cbSavings.isSelected(), cbCheque.isSelected(), cbInvest.isSelected());
            if (res == null || !res.isSuccess()) {
                msg.setText("Failed: " + (res == null ? "unknown" : res.getMessage()));
                return;
            }
            Customer created = res.getCustomer();
            String tempPass = res.getTemporaryPassword();
            msg.setText("Created ID: " + created.getCustomerId());

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Customer Created");
            info.setHeaderText("Customer created successfully");
            info.setContentText("Customer ID: " + created.getCustomerId()
                    + "\nUsername: cust" + created.getCustomerId()
                    + "\nTemporary password: " + tempPass);
            info.showAndWait();

            nameField.clear();
            emailField.clear();
            phoneField.clear();
            cbSavings.setSelected(false);
            cbCheque.setSelected(false);
            cbInvest.setSelected(false);
        });

        btnList.setOnAction(e -> {
            // show simple list dialog
            CustomerDAO cdao = new CustomerDAO();
            List<Customer> list = cdao.getAllCustomers();
            StringBuilder sb = new StringBuilder();
            for (Customer c : list)
                sb.append("ID: ").append(c.getCustomerId()).append(" | ").append(c.getName()).append(" | ")
                        .append(c.getEmail()).append("\n");
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Customers");
            a.setHeaderText("Existing customers");
            a.getDialogPane().setContent(new TextArea(sb.toString()));
            a.showAndWait();
        });

        btnAccounts.setOnAction(e -> {
            // show accounts using AccountDAO
            dao.AccountDAO adao = new dao.AccountDAO();
            StringBuilder sb = new StringBuilder();
            for (model.Account acc : adao.getAllAccounts()) {
                sb.append("Acct#: ").append(acc.getAccountNumber())
                        .append(" | CustID: ").append(acc.getCustomerId())
                        .append(" | Type: ").append(acc.getAccountType())
                        .append(" | Bal: ").append(acc.getBalance()).append("\n");
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Accounts");
            a.setHeaderText("All Accounts");
            a.getDialogPane().setContent(new TextArea(sb.toString()));
            a.showAndWait();
        });

        btnLogout.setOnAction(e -> {
            LoginView lv = new LoginView();
            stage.setScene(lv.getScene(stage));
        });

        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setCenter(content);
        BorderPane.setAlignment(sidebar, Pos.CENTER_LEFT);

        return new Scene(root, 900, 600);
    }
}
