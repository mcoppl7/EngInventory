package inventory.enginventory.database;

import inventory.enginventory.alerts.CrashLog;
import inventory.enginventory.objects.CheckoutRecord;
import inventory.enginventory.objects.ToolRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Get {
    private static Connection connection;
    /**
     * Retrieves values from database and
     * stores in ObervableList
     * @return the list with data from database
     * Contains Line Build
     */
    @SuppressWarnings("SpellCheckingInspection")
    @FXML
    public static ObservableList<String> setCmboBuild() throws SQLException {
        ObservableList<String> build = FXCollections.observableArrayList();
        PreparedStatement statement = null;

        try {
            connection = DB_Util.ConnectToDB();
            statement = connection.prepareStatement("SELECT * from tblLineBuild");
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                build.add(rs.getString("LineBuild"));
            }
        } catch (SQLException | RuntimeException | FileNotFoundException | UnsupportedEncodingException e) {
            CrashLog.reportCrash(e);
        } finally {
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                DB_Util.CloseDB();
            }
        }
        return build;
    }

    /**
     * Retrieves values from database and
     * stores in ObervableList
     * @return the list with data from database
     * Contains Assembly lines
     */
    @FXML
    public static ObservableList<String> setCmboLines() throws FileNotFoundException, UnsupportedEncodingException, SQLException {
        ObservableList<String> lines = FXCollections.observableArrayList();

        PreparedStatement statement = null;
        try {
            connection = DB_Util.ConnectToDB();
            statement = connection.prepareStatement("SELECT * from tblLines");
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                lines.add(rs.getString("Line"));
            }
        } catch (SQLException e) {
            CrashLog.reportCrash(e);
        } finally {
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                DB_Util.CloseDB();
            }
        }
        return lines;
    }

    /**
     * Retrieves values from database and
     * stores in ObervableList
     * @return the list with data from database
     * Contains Employee Shifts
     */
    @FXML
    public static ObservableList<String> setCmboShift() throws FileNotFoundException, UnsupportedEncodingException, SQLException {
        ObservableList<String> shifts = FXCollections.observableArrayList();
        PreparedStatement statement = null;

        try {
            connection = DB_Util.ConnectToDB();
            statement = connection.prepareStatement("SELECT * from tblShifts");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                shifts.add(rs.getString("Shift"));
            }
        } catch (UnsupportedEncodingException | SQLException e) {
            CrashLog.reportCrash(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                DB_Util.CloseDB();
            }
        }
        return shifts;
    }

    /**
     * Looks up a part name by the part number
     * @param partNumber the name of the part corresponding to the
     * part number.
     * @return a part name or "N/A" if not found.
     */
    public static ToolRecord SearchTool(String partNumber) throws SQLException {
        String sql = "SELECT PartNumber, PartName, Category, REV, Description, Vendor, Cost, " +
                "Obsolete, Station, ReOrderPoint, OnHand, HeatTreat, PDFLocation FROM tblParts WHERE PartNumber = ?";
        ToolRecord toolRecord = new ToolRecord();
        PreparedStatement statement = null;
        try {
            connection = DB_Util.ConnectToDB();
            statement = connection.prepareStatement(sql);
            statement.setString(1, partNumber);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                toolRecord.setPartName(rs.getString("PartName"));
                toolRecord.setArchive(rs.getBoolean("Obsolete"));
                toolRecord.setCategory(rs.getString("Category"));
                toolRecord.setRev(rs.getInt("REV"));
                toolRecord.setVendor(rs.getString("Vendor"));
                toolRecord.setDescription(rs.getString("Description"));
                toolRecord.setReorderPoint(rs.getInt("ReOrderPoint"));
                toolRecord.setOnHand(rs.getInt("OnHand"));
            }
        } catch (SQLException | RuntimeException | FileNotFoundException | UnsupportedEncodingException e) {
            CrashLog.reportCrash(e);
        } finally {
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                DB_Util.CloseDB();
            }
        }
        return toolRecord;
    }


    /**
     * Returns the current on hand amount for a part. Gets
     * data from tblParts "On Hand" column
     * @param partNumber part or tool number.
     * @return the quantity on hand from "On Hand" column.
     */
    public static int GetOnHand(String partNumber) throws SQLException {
        String sql = "SELECT OnHand FROM tblParts WHERE PartNumber = ?";
        PreparedStatement statement = null;
        int onHand = -1;

        try {
            connection = DB_Util.ConnectToDB();
            statement = connection.prepareStatement(sql);
            statement.setString(1, partNumber);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                onHand = rs.getInt("OnHand");
            }
        } catch (SQLException | FileNotFoundException | RuntimeException | UnsupportedEncodingException e) {
            CrashLog.reportCrash(e);
        } finally {
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                DB_Util.CloseDB();
            }
        }
        return onHand;
    }

    public static ObservableList<CheckoutRecord> LoadNotReturnedData() throws SQLException {
        ObservableList<CheckoutRecord> notReturnedData = FXCollections.observableArrayList();
        CheckoutRecord record;
        String sql = "SELECT CheckoutID, PartNumber, Quantity, LineNumber, LineBuild, Shift, EmployeeName, CheckoutDate " +
                "FROM tblPartCheckout WHERE Borrowed = 1 AND isReturned = 0 " +         // 0 = NO, 1 = YES
                "AND CheckoutDate <= CURDATE() AND CheckoutDate >= CURDATE() - 7 ";     // get tools not returned within the past 7 days

        PreparedStatement statement = null;
        try {
            connection = DB_Util.ConnectToDB();
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {

                record = new CheckoutRecord(rs.getInt("CheckoutID"), rs.getString("PartNumber"), rs.getInt("Quantity"), rs.getInt("LineNumber"),
                        rs.getString("LineBuild"), rs.getString("Shift"), rs.getString("EmployeeName"));
                record.setCheckoutDate(rs.getString("CheckoutDate")
                        .replace("00", "")
                        .replace(":","")
                        .replace(".",""));
                notReturnedData.add(record);
            }
        } catch (UnsupportedEncodingException | SQLException | FileNotFoundException e) {
            CrashLog.reportCrash(e);
        } finally {
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                DB_Util.CloseDB();
            }
        }
        return notReturnedData;
    }
}
