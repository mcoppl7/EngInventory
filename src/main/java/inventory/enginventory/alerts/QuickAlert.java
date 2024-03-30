package inventory.enginventory.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class QuickAlert extends Alert {
    private static Alert alert;
    public QuickAlert(AlertType alertType) {
        super(alertType);
    }

    public QuickAlert(AlertType alertType, String s, ButtonType... buttonTypes) {
        super(alertType, s, buttonTypes);
    }

    public QuickAlert(AlertType type, String title, String header, String content) {
        super(type);
        alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
    }

    public void showQuickAlert() {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
    }

    public boolean okSelected() {
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public String yesNoAlertBox() {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().set(0, yes);
        alert.getButtonTypes().set(1, no);
        Optional<ButtonType> result = alert.showAndWait();

        if(result.orElse(ButtonType.NO) == no) {
            return "NO";
        } else {
            return "YES";
        }
    }

    public String getFilePathBox() {
        ButtonType browse = new ButtonType("Browse", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().set(0,browse);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.orElse(ButtonType.CANCEL) == browse) {

        }

        return null;
    }

    public void setContent(String content) {
        alert.setContentText(content);
    }

}

