/**
 * The alerts class is a collection of Alert objects that
 * are defined with parameters to provide feedback to the user.
 * @author Mike Coppola
 */

package inventory.enginventory.alerts;

import inventory.enginventory.objects.ReturnRecord;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.util.Optional;


public class Alerts {
    private static Alert dB_LocSaved;                       // confirms database location was saved
    private static Alert noDbFound;                         // file or path for DB missing/incorrect
    private static Alert requiredFields;                    // all required fields not filled out
    private static Alert transactionSuccessful;             // checkout transaction saved to db
    private static Alert noRecordsFound;                    // no records returned by search query
    private static Alert confirmToolReturn;                 // confirm that user wants to return tool

    /**
     * Shows a message confirming the database
     * location was saved successfully.
     * Displays the location selected.
     */
    public static void DbLocSaved(String db_location) {
        dB_LocSaved = new Alert(Alert.AlertType.CONFIRMATION);
        dB_LocSaved.setTitle("Database Path Successfully Saved.");
        dB_LocSaved.setHeaderText("Confirmation");
        dB_LocSaved.setContentText("Database path saved as: " + db_location + "\n\nRestart Application for changes to take effect.");

        // Modality for message window
        Stage stage = (Stage) dB_LocSaved.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        dB_LocSaved.show();
    }

    /**
     * Message that database was not found.
     * db_loc.txt file is missing or path
     * is incorrect.
     */
    public static void Alert_NoDBFound() {
        noDbFound = new Alert(Alert.AlertType.ERROR);
        noDbFound.setTitle("Error");
        noDbFound.setHeaderText("No database found.");
        noDbFound.setContentText("Check database location and set again.");
        Stage stage = (Stage) noDbFound.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    /**
     * Alert Dialog when user submits
     * form without entering all required fields.
     */
    public static void EnterRequiredFields() {
        requiredFields = new Alert(Alert.AlertType.WARNING);
        requiredFields.setTitle("Missing Fields");
        requiredFields.setHeaderText("Cannot Submit Form.");
        requiredFields.setContentText("Please enter in all required fields.");

        // Modality for message window
        Stage stage = (Stage) requiredFields.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        requiredFields.show();
    }

    /**
     * Alert dialog that Checkout transaction
     * was successful and saved in database.
     */
    public static void TransactionSuccessful() {
        transactionSuccessful = new Alert(Alert.AlertType.CONFIRMATION);
        transactionSuccessful.setTitle("Transaction Complete");
        transactionSuccessful.setHeaderText("Form Submitted.");
        transactionSuccessful.setContentText("Thank you for participating in the ENG Checkout/Return Process!");

        // Modality for message window
        Stage stage = (Stage) transactionSuccessful.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        transactionSuccessful.show();
    }

    /**
     * Alert dialog when no results are returned
     * from tooling checkout query to return past
     * checkouts.
     */
    public static void NoRecordsFound(){
        noRecordsFound = new Alert(Alert.AlertType.WARNING);
        noRecordsFound.setTitle("No results");
        noRecordsFound.setHeaderText("No checkout records found");
        noRecordsFound.setContentText("Ensure the name and shift entered is correct and tooling was checked out as borrowed.");

        // Modality for message window
        Stage stage = (Stage) noRecordsFound.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        noRecordsFound.show();
    }

    /**
     * Yes/No dialog that confirms if user wants to
     * complete return tool process.
     * @param rec the record of the return
     * @return true if Yes, false if No selected
     */
    public static boolean ConfirmToolReturn(ReturnRecord rec) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmToolReturn = new Alert(Alert.AlertType.CONFIRMATION,"",yes,no);
        confirmToolReturn.setHeaderText("Confirm you would like to return tooling.");
        confirmToolReturn.setContentText("Part Number: " + rec.getPartNumber() + "\nDescription: " + rec.getCategory());
        confirmToolReturn.setTitle("Tool Return");
        Optional<ButtonType> result = confirmToolReturn.showAndWait();

        if(result.orElse(no) == yes) {
            return true;
        }
        else {
            return false;
        }
    }
}

