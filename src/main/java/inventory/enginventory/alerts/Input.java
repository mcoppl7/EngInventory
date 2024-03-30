package inventory.enginventory.alerts;

import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;

public class Input {
    private static TextInputDialog addItem;
    private static TextInputDialog editItem;
    private static TextInputDialog promptPassword;
    private static FileChooser fileChooser;
    private static final String ADMIN_PASS = "foxShoxForTheWin";

    public static boolean passwordAccepted() {
        promptPassword = new TextInputDialog("");
        promptPassword.setTitle("ADMIN Rights Needed");
        promptPassword.setHeaderText("Enter admin password to continue...");
        promptPassword.setContentText("This utility includes functions that can make or" +
                " break the database. \nYou need admin rights to use these.");

        promptPassword.showAndWait();

        return promptPassword.getEditor().getText().trim().equals(ADMIN_PASS);
    }
}

