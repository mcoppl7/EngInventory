package inventory.enginventory.database;

import inventory.enginventory.alerts.Alerts;
import inventory.enginventory.alerts.CrashLog;
import inventory.enginventory.objects.CheckoutRecord;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB_Insert {
    private static Connection connection;

    /**
     * Inserts a checkout record into tblPartCheckout
     * @param checkoutRecord an object representing a CheckoutRecord
     */
    public static void InsertCheckout(CheckoutRecord checkoutRecord) throws SQLException {
        String sql = "INSERT INTO tblPartCheckout ([PartNumber], [PartName], [Category], [Quantity], [PartCondition]," +
                "[Borrowed], [LineNumber], [LineBuild], [Station], [Shift], [EmployeeName], [isReturned])" +
                "VALUES ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

        PreparedStatement statement = null;
        try {
            connection = DB_Util.ConnectToDB();
            statement = connection.prepareStatement(sql);

            statement.setString(1, checkoutRecord.getPartNumber());
            statement.setString(2, checkoutRecord.getPartName());
            statement.setString(3, checkoutRecord.getCategory());
            statement.setInt(4, checkoutRecord.getQuantity());
            statement.setString(5, checkoutRecord.getReason());
            statement.setInt(6, checkoutRecord.getBorrowed());
            statement.setInt(7, checkoutRecord.getLineNumber());
            statement.setString(8, checkoutRecord.getLineBuild());
            statement.setString(9, checkoutRecord.getStation());
            statement.setString(10, checkoutRecord.getShift());
            statement.setString(11, checkoutRecord.getEmployeeName());
            statement.setInt(12, checkoutRecord.getIsReturned());

            statement.execute();
        } catch (FileNotFoundException | UnsupportedEncodingException | SQLException e) {
            CrashLog.reportCrash(e);
        } finally {
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                DB_Util.CloseDB();
            }
        }
        Alerts.TransactionSuccessful();
    }
}
