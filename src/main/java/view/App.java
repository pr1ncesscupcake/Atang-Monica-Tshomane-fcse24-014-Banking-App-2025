package view;

import javafx.application.Application;
import javafx.stage.Stage;
import util.DatabaseSeeder;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Ensure DB schema and default users exist
        DatabaseSeeder.initialize();

        LoginView login = new LoginView();
        stage.setScene(login.getScene(stage));
        stage.setTitle("Banking System");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
