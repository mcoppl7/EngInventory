package inventory.enginventory.database;

import inventory.enginventory.alerts.Alerts;
import inventory.enginventory.alerts.CrashLog;
import inventory.enginventory.objects.ReturnRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class Database {
    private static Connection connection;

    /**
     * Search the database for matching employee name, shift worked,
     * borrowed tools not returned.
     * @param employeeName the employee who checked out tool
     * @param shift the shift that employee works
     * @param lineNumber the line that the tool went to
     * @return the list of checkouts made in the past
     * @throws SQLException
     * @throws FileNotFoundException
     */
    public static ObservableList SearchCheckoutRecords(String employeeName, String shift, String lineNumber)
            throws SQLException, FileNotFoundException {
        ObservableList<ReturnRecord> results = FXCollections.observableArrayList();
        ReturnRecord record;
        String sql = "SELECT CheckoutID, EmployeeName, Shift, PartNumber, Category, CheckOutDate, Quantity from tblPartCheckout WHERE EmployeeName = ? AND Shift = ?"
                + " AND isReturned = 0 AND Borrowed = 1 AND LineNumber = ?";

        PreparedStatement statement = null;
        try {
            connection = DB_Util.ConnectToDB();

            statement = connection.prepareStatement(sql);
            statement.setString(1,employeeName);
            statement.setString(2,shift);
            statement.setString(3,lineNumber);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                record = new ReturnRecord(rs.getString("PartNumber"), rs.getString("Category"),
                        rs.getString("CheckOutDate")
                                .replace("00","")
                                .replace(":","")
                                .replace(".","")
                        , rs.getInt("CheckoutID"));
                record.setQuantity(rs.getInt("Quantity"));
                results.add(record);
            }
        } catch (SQLException | UnsupportedEncodingException e) {
             CrashLog.reportCrash(e);
        } finally {
            if (statement != null) {
                statement.close();
            }

            if(connection != null) {
                DB_Util.CloseDB();
            }
        }
        return results;
    }

    /**
     * If user returns tool, then set the return status in
     * database to "Yes" at specified record.
     * @param rec the record selected by user
     * @throws FileNotFoundException
     */
    public static void SetReturnStatus_True(ReturnRecord rec) throws FileNotFoundException, UnsupportedEncodingException, SQLException {
        int recordNumber = rec.getRecordNumber();
        String sql = "UPDATE tblPartCheckout SET isReturned = 1 WHERE CheckoutID = ?";

        PreparedStatement preparedStatement = null;

        try {
            connection = DB_Util.ConnectToDB();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, recordNumber);
            preparedStatement.executeUpdate();

        } catch (SQLException | UnsupportedEncodingException e) {
            CrashLog.reportCrash(e);
        } finally {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            if(connection != null) {
                DB_Util.CloseDB();
            }
        }
        Alerts.TransactionSuccessful();
    }

    /**
     * Populates the returnRecord object with the LineBuild,
     * LineNumber, Shift, Category, PartNumber, and Employee Name
     * @param rec the return record
     * @throws FileNotFoundException
     */
    public static void CreateReturn(ReturnRecord rec) throws FileNotFoundException, UnsupportedEncodingException, SQLException {
        String sql = "SELECT PartNumber, Category, LineBuild, LineNumber, Shift, EmployeeName FROM tblPartCheckout WHERE CheckoutID = ?";

        PreparedStatement preparedStatement = null;

        try {
            connection = DB_Util.ConnectToDB();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, rec.getRecordNumber());
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                rec.setPartNumber(rs.getString("PartNumber"));
                rec.setCategory(rs.getString("Category"));
                rec.setLineBuild(rs.getString("LineBuild"));
                rec.setLineNumber(rs.getString("LineNumber"));
                rec.setShift(rs.getString("Shift"));
                rec.setEmployeeName(rs.getString("EmployeeName"));
            }

            preparedStatement.close();
            DB_Util.CloseDB();

            InsertReturnRecord(rec);
        } catch (SQLException e) {
            CrashLog.reportCrash(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if(connection != null) {
                DB_Util.CloseDB();
            }
        }
    }

    /**
     * Inserts the return record into database.
     * @param rec the return record
     * @throws FileNotFoundException
     * @throws SQLException
     */
    public static void InsertReturnRecord(ReturnRecord rec) throws FileNotFoundException, SQLException {
        String sql = "INSERT INTO tblReturn ([PartNumber], [Category], [LineBuild], [LineNumber], [Shift], [EmployeeName])" +
                "VALUES (?,?,?,?,?,?)";

        try {
            connection = DB_Util.ConnectToDB();
        } catch (UnsupportedEncodingException e) {
            CrashLog.reportCrash(e);
            throw new RuntimeException(e);
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, rec.getPartNumber());
            statement.setString(2, rec.getCategory());
            statement.setString(3, rec.getLineBuild());
            statement.setString(4, rec.getLineNumber());
            statement.setString(5, rec.getShift());
            statement.setString(6, rec.getEmployeeName());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                DB_Util.CloseDB();
            }
        }
    }
}
