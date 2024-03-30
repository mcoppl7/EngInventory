package inventory.enginventory.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main_GUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/inventory/enginventory/checkout_form.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Tooling Checkout & Return");            // Main stage
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());          // do not allow width to decrease
        primaryStage.setMinHeight(primaryStage.getHeight());        // do not allow height to decrease
    }

    public static void main(String[] args) {
        launch();
    }
}
