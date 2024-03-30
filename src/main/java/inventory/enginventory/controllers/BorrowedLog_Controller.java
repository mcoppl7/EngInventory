package inventory.enginventory.controllers;

import inventory.enginventory.database.DB_Get;
import inventory.enginventory.objects.CheckoutRecord;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BorrowedLog_Controller implements Initializable {
    @FXML
    private TableColumn<CheckoutRecord, String> colCheckoutDate;

    @FXML
    private TableColumn<CheckoutRecord, Integer> colLine;
    @FXML
    private TableColumn<CheckoutRecord, String> colLineBuild;

    @FXML
    private TableColumn<CheckoutRecord, String> colName;

    @FXML
    private TableColumn<CheckoutRecord, String> colPartNumber;

    @FXML
    private TableColumn<CheckoutRecord, String> colPartName;

    @FXML
    private TableColumn<CheckoutRecord, Integer> colQuantity;

    @FXML
    private TableColumn<CheckoutRecord, String> colShift;

    @FXML
    private Label lblLastUpdated;

    @FXML
    private AnchorPane paneMain;

    @FXML
    private TableView<CheckoutRecord> tblBorrowedLog;

    private ObservableList<CheckoutRecord> checkoutRecords;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**
     * Loads Checkout records from tblPartsCheckout in database
     * and sets all the table rows for table view tblCheckouts
     */
    private void LoadData() throws SQLException {
        checkoutRecords = DB_Get.LoadNotReturnedData();

        colPartNumber.setCellValueFactory(new PropertyValueFactory<>("partNumber"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colLine.setCellValueFactory(new PropertyValueFactory<>("lineNumber"));
        colLineBuild.setCellValueFactory(new PropertyValueFactory<>("lineBuild"));
        colShift.setCellValueFactory(new PropertyValueFactory<>("shift"));
        colName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colCheckoutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        colPartName.setCellValueFactory(new PropertyValueFactory<>("partName"));

        tblBorrowedLog.setItems(checkoutRecords);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*
            Refresh the borrowed log using a timed task
            Close the thread if the window is closed or crashes
         */

        /*
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    paneMain.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST,
                            windowEvent -> timer.cancel());
                    LocalDateTime current = LocalDateTime.now();
                    lblLastUpdated.setText(dtf.format(current));
                     try {
                         LoadData();
                     } catch (IOException e) {
                         timer.cancel();
                         Stage stage = (Stage) paneMain.getScene().getWindow();
                         stage.close();
                        CrashLog.reportCrash(e);
                    }
                });
            }
        }, 0, 10000); */
    }
}
