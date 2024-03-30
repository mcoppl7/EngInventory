package inventory.enginventory.database;

import inventory.enginventory.alerts.Alerts;
import inventory.enginventory.alerts.CrashLog;
import java.io.*;
import java.sql.*;
import java.util.ResourceBundle;

public class DB_Util {
    private static Connection connection;
    private static final ResourceBundle RB = ResourceBundle.getBundle("inventory.enginventory.util");

    /**
     * Opens connection to database
     * @return the connection
     * Alert if no database found
     */
    public static Connection ConnectToDB() throws FileNotFoundException, UnsupportedEncodingException {

        try {
            // IMPORTANT must include Class.forName for jar to execute
            Class.forName(RB.getString("driverClassName"));
            connection = DriverManager.getConnection(RB.getString("url"));
        } catch (SQLException | ClassNotFoundException e) {
            CrashLog.reportCrash(e);
        }
        return connection;
    }

    /**
     * Closes the connection to database
     */
    public static void CloseDB() {
        try {
            connection.close();
        } catch (SQLException e) {
            CrashLog.reportCrash(e);
        }
    }

    public static boolean connectionValid() {
        try {
            connection = ConnectToDB();
            return connection.isValid(3);
        } catch (SQLException | UnsupportedEncodingException | FileNotFoundException e) {
            Alerts.Alert_NoDBFound();
            return false;
        } finally {
            if(connection != null) {
                CloseDB();
            }
        }
    }
}