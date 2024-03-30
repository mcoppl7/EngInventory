package inventory.enginventory.controllers;

import inventory.enginventory.alerts.Alerts;
import inventory.enginventory.alerts.CrashLog;
import inventory.enginventory.database.*;
import inventory.enginventory.objects.CheckoutRecord;
import inventory.enginventory.objects.ToolRecord;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Checkout_Controller implements Initializable {
    @FXML
    private Label toolCategory;
    @FXML
    private Label toolPartName;
    @FXML
    private Button btnClear;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnSubmit;

    @FXML
    private ComboBox<String> cmboBuild;

    @FXML
    private ComboBox<String> cmboLine;

    @FXML
    private ComboBox<String> cmboReason;

    @FXML
    private ComboBox<String> cmboShift;

    @FXML
    private TextField txtCategory;

    @FXML
    private Spinner<Integer> spinnerQuantity;

    @FXML
    private ToggleGroup tglBorrowGroup;

    @FXML
    private ToggleButton tglNo;

    @FXML
    private ToggleButton tglYes;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtPartNumber;
    @FXML
    private TextField txtPartName;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuClose;

    @FXML
    private Menu menuFile;

    @FXML
    private MenuItem menuSetDB_Loc;

    private int tglSelected;                                // toggle button for borrowed. Yes = 1, No = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Focus listener for txtPartNumber
        txtPartNumber.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) { // focus lost
                try {
                ToolRecord tool = DB_Get.SearchTool(txtPartNumber.getText());


                    if(tool.getPartName() != "") {
                        toolPartName.setText(tool.getPartName());
                        toolCategory.setText(tool.getCategory());
                    } else {
                        toolPartName.setText("N/A");
                        toolCategory.setText("N/A");
                    }
                } catch (NullPointerException | SQLException e) {
                    CrashLog.reportCrash(e);
                }
            }
        });

        if (DB_Util.connectionValid()) {
            try {
                cmboShift.setItems(DB_Get.setCmboShift());                                                                    // populate shift combo box
                cmboBuild.setItems(DB_Get.setCmboBuild());                                                                    // populate items for build line
                cmboReason.setItems(FXCollections.observableArrayList("Damaged", "Worn Out", "Missing", "Other"));
                cmboLine.setItems(DB_Get.setCmboLines());                                                                     // set data for combobox for assembly lines
                SpinnerValueFactory<Integer> valueFactory =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);                                  // initialize ValueFactory (1 - 10)
                valueFactory.setValue(1);       // set default to 1
                spinnerQuantity.setValueFactory(valueFactory);                                                          // add value factory to spinner

            } catch (FileNotFoundException | UnsupportedEncodingException | SQLException e) {
                Alerts.Alert_NoDBFound();
                throw new RuntimeException(e);
            }
        } else {
            Alerts.Alert_NoDBFound();
        }
    }

    /**
     * Set the Close Menu Item Action
     * Exits the program
     */
    @FXML
    void menuCloseOnClick() {
        Platform.exit();    // exit the program
    }

    /**
     * Sends all the information on form to the database.
     * tblCheckout is the table where this information is stored.
     * All SQL commands are handled through the Database.java class
     *
     */
    @FXML
    private void btnSubmitClick() throws SQLException {
        CheckoutRecord checkoutRecord = new CheckoutRecord();

        try {
            checkoutRecord.setPartNumber(txtPartNumber.getText());
            checkoutRecord.setPartName(toolPartName.getText());
            checkoutRecord.setCategory(toolCategory.getText());
            checkoutRecord.setQuantity(spinnerQuantity.getValue());
            checkoutRecord.setReason(cmboReason.getValue());
            checkoutRecord.setLineNumber(Integer.parseInt(cmboLine.getValue()));
            checkoutRecord.setLineBuild(cmboBuild.getValue());
            checkoutRecord.setShift(cmboShift.getValue());
            checkoutRecord.setEmployeeName(txtEmployeeName.getText());

            if(tglSelected == 1) {
                checkoutRecord.setBorrowed(1);
            }
            else checkoutRecord.setBorrowed(0);

            if(txtEmployeeName.getText().length() > 0) {
                DB_Insert.InsertCheckout(checkoutRecord);
                ClearForm();
                Transaction.TransactSubtract(checkoutRecord.getQuantity(), checkoutRecord.getPartNumber());
            } else {
                Alerts.EnterRequiredFields();
            }

        } catch (RuntimeException e) {
            Alerts.EnterRequiredFields();
        }
    }

    @FXML
    private void btnClearClick(ActionEvent event) {
        ClearForm();
    }

    private void ClearForm() {
        txtPartNumber.clear();
        toolCategory.setText("Enter a tool to display part name");
        toolPartName.setText("Enter a tool to display part name");
        //listCategory.scrollTo(0);                   // go to top of list
        cmboReason.valueProperty().set(null);           // clear selected reason
        cmboLine.valueProperty().set(null);             // clear line #
        cmboBuild.valueProperty().set(null);            // clear selected build
        cmboShift.valueProperty().set(null);            // clear selected shift
        txtEmployeeName.setText("");                    // clear test for employee name
        spinnerQuantity.getValueFactory().setValue(1);  // set spinner to 1
        tglBorrowGroup.selectToggle(null);              // deselect toggle buttons
    }

    /**
     * Set tglSelected to 1 if "Yes" is selected
     *
     * @param event
     */
    @FXML
    private void tglBtnYes(ActionEvent event) {
        tglSelected = 1;
    }

    /**
     * Set tglSelected to 0 if "No" is selected
     *
     * @param event
     */
    @FXML
    private void tglButtonNo(ActionEvent event) {
        tglSelected = 0;
    }

    @FXML
    private void btnReturn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/inventory/enginventory/return.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Tooling Return");            // Main stage

        // Modality for Return Form
        stage.initOwner(btnReturn.getScene().getWindow());      // the CheckoutForm class window
        stage.initModality(Modality.WINDOW_MODAL);

        stage.show();
    }

    @FXML private void viewBorrowedLog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/inventory/enginventory/borrowedLog.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Borrowed Tooling Log");            // Main stage

        // Modality for Return Form
        stage.initOwner(btnReturn.getScene().getWindow());      // the CheckoutForm class window
        stage.initModality(Modality.NONE);

        stage.show();
    }
}
