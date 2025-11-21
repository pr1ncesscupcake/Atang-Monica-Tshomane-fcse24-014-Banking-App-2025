package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import controller.TransactionController;
import model.Transaction;

public class TransactionView {

    private TransactionController controller = new TransactionController();

    public Scene getView(Stage stage) {

        TextField accNumberField = new TextField();
        accNumberField.setPromptText("Enter Account Number");

        TextArea result = new TextArea();
        result.setEditable(false);

        Button loadBtn = new Button("Load Transactions");
        Button backBtn = new Button("Back");

        loadBtn.setOnAction(e -> {
            var transactions = controller.getTransactionsForAccount(accNumberField.getText());

            StringBuilder sb = new StringBuilder();
            for (Transaction t : transactions) {
                sb.append("ID: ").append(t.getTransactionId())
                  .append(" | Type: ").append(t.getType())
                  .append(" | Amount: ").append(t.getAmount())
                  .append("\n");
            }
            result.setText(sb.toString());
        });

        backBtn.setOnAction(e -> {
            DashboardView db = new DashboardView();
            stage.setScene(db.getView(stage));
        });

        VBox root = new VBox(15, accNumberField, loadBtn, result, backBtn);
        root.setPadding(new Insets(20));

        return new Scene(root, 500, 450);
    }
}

