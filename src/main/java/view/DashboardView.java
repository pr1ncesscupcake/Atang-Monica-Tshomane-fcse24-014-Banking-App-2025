package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardView {

    public Scene getView(Stage stage) {

        Button customerBtn = new Button("Manage Customers");
        Button accountBtn = new Button("Manage Accounts");
        Button transactionBtn = new Button("View Transactions");

        customerBtn.setOnAction(e -> {
            CustomerView customerView = new CustomerView();
            stage.setScene(customerView.getView(stage));
        });

        accountBtn.setOnAction(e -> {
            AccountView accountView = new AccountView();
            stage.setScene(accountView.getView(stage));
        });

        transactionBtn.setOnAction(e -> {
            TransactionView tv = new TransactionView();
            stage.setScene(tv.getView(stage));
        });

        VBox root = new VBox(20, customerBtn, accountBtn, transactionBtn);
        root.setPadding(new Insets(20));

        return new Scene(root, 400, 300);
    }
}

