package inventory.enginventory.database;

import inventory.enginventory.alerts.CrashLog;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.RetentionPolicy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Transaction {
    private static Connection connection;

    public static void TransactAdd(int quantity, String partNumber) throws SQLException {
        String sql = "UPDATE tblParts Set OnHand = ? WHERE PartNumber = ?";
        PreparedStatement statement = null;

        try {
            connection = DB_Util.ConnectToDB();

            quantity = quantity + DB_Get.GetOnHand(partNumber);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setString(2, partNumber);

            statement.executeUpdate();
        } catch (SQLException | RuntimeException | UnsupportedEncodingException| FileNotFoundException e) {
            CrashLog.reportCrash(e);
        } finally {
            if(statement != null) {
                statement.close();
            }
            if (connection != null) {
                DB_Util.CloseDB();
            }
        }
    }

    public static void TransactSubtract(int quantity, String partNumber) throws SQLException {
        String sql = "UPDATE tblParts Set OnHand = ? WHERE PartNumber = ?";
        PreparedStatement statement = null;

        try {
            connection = DB_Util.ConnectToDB();

            quantity = DB_Get.GetOnHand(partNumber) - quantity;
            statement = connection.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setString(2, partNumber);

            statement.executeUpdate();
        } catch (SQLException | RuntimeException | UnsupportedEncodingException| FileNotFoundException e) {
            CrashLog.reportCrash(e);
        } finally {
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                DB_Util.CloseDB();
            }
        }
    }
}
