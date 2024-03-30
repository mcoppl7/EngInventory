package inventory.enginventory.controllers;

import inventory.enginventory.alerts.Alerts;
import inventory.enginventory.database.DB_Get;
import inventory.enginventory.database.DB_Util;
import inventory.enginventory.database.Database;
import inventory.enginventory.database.Transaction;
import inventory.enginventory.objects.CheckoutRecord;
import inventory.enginventory.objects.ReturnRecord;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Return_Controller implements Initializable {

    @FXML
    private Button btnSearch;

    @FXML
    private ComboBox<String> cmboLineBuild;

    @FXML
    private ComboBox<String> cmboLineNumber;

    @FXML
    private ComboBox<String> cmboShift;

    @FXML
    private TextField txtEmployeeName;

    // Table and Columns
    @FXML
    private TableColumn<ReturnRecord, String> colCheckoutDate;
    @FXML
    private TableColumn<ReturnRecord, String> colCategory;

    @FXML
    private TableColumn<ReturnRecord, String> colPartNumber;
    @FXML
    private TableColumn<ReturnRecord, String> colClickReturn;
    @FXML
    private TableView<ReturnRecord> tblSearchResults;
    @FXML
    private Button btnReturn;
    private static ReturnRecord selectedItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(DB_Util.connectionValid()) {
            try {
                cmboShift.setItems(DB_Get.setCmboShift());                                                                      // Combo box that holds all shifts
                cmboLineNumber.setItems(DB_Get.setCmboLines());                                                                     // set data for combobox for assembly lines
            } catch (FileNotFoundException | UnsupportedEncodingException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            Alerts.Alert_NoDBFound();
        }
    }

    @FXML
    private void btnSearchClick() throws SQLException, FileNotFoundException, UnsupportedEncodingException {
        SearchDatabase();
    }
    /**
     * When search button is clicked query the database for the
     * employee name and shift worked, for tools not returned,
     * and tools borrowed. Populate table.
     * @throws SQLException
     * @throws FileNotFoundException
     */
    private void SearchDatabase() throws SQLException, FileNotFoundException, UnsupportedEncodingException {
        ObservableList<ReturnRecord> results;
        results = Database.SearchCheckoutRecords(txtEmployeeName.getText(), cmboShift.getValue(), cmboLineNumber.getValue());
        colPartNumber.setCellValueFactory(new PropertyValueFactory<>("partNumber"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCheckoutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        try {
            tblSearchResults.setItems(results);
            tableEmpty(results);        // check if no results returned
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * Query the database for the
     * employee name and shift worked, for tools not returned,
     * and tools borrowed. Update table.
     * @throws SQLException
     * @throws FileNotFoundException
     */
    private void UpdateList() throws SQLException, FileNotFoundException, UnsupportedEncodingException {
        ObservableList<ReturnRecord> results;
        results = Database.SearchCheckoutRecords(txtEmployeeName.getText(), cmboShift.getValue(),
                cmboLineNumber.getValue());
        colPartNumber.setCellValueFactory(new PropertyValueFactory<>("partNumber"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCheckoutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        try {
            tblSearchResults.setItems(results);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @FXML
    public void onTableViewElementSelected()
    {
        // Get selected item
        if(!tblSearchResults.getItems().isEmpty()) {        // if the search results list is populated
            selectedItem = tblSearchResults.getSelectionModel().selectedItemProperty().get();
            confirmReturn(selectedItem);
        }
        // Confirm user wants to return tool
        // Table must be populated
    }

    private void confirmReturn(ReturnRecord selectedItem) {
        boolean isReturn = Alerts.ConfirmToolReturn(selectedItem);

        if(isReturn) {
            try {
                Database.SetReturnStatus_True(selectedItem);    // set returned "Yes" in database
                UpdateList();                               // update return list
                SaveReturnToDB(selectedItem);

                // Add item back into inventory
                Transaction.TransactAdd(selectedItem.getQuantity(), selectedItem.getPartNumber());

            } catch (FileNotFoundException | SQLException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void SaveReturnToDB(ReturnRecord selectedItem) {

        try {
            Database.CreateReturn(selectedItem);
        } catch (FileNotFoundException | UnsupportedEncodingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Check if return table has results.
     * Display Alert if empty.
     * @param results the list retrieved from database
     */
    private void tableEmpty(ObservableList<ReturnRecord> results) {
        if(results.isEmpty()) {
            Alerts.NoRecordsFound();
        }
    }
}
