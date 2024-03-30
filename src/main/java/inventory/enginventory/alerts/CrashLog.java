/**
 * CrashLog outputs an exception to a .txt file
 * on the path of the EngInventory.jar file
 * @author Mike Coppola
 */

package inventory.enginventory.alerts;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CrashLog {
    private static PrintWriter writer;
    private static File crashfile;
    private static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

    /**
     * Writes an exception to a file with a timestamp
     * @param e an exception thrown by program
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void reportCrash(Exception e) {
        crashfile = new File("crashlog.txt");
        try {
            if(!crashfile.exists()) {
                crashfile.createNewFile();
            }
            else {
                writer = new PrintWriter(new FileWriter(crashfile,true));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        writer.println(timeStamp + "\n");
        writer.println(e.getCause() + "\n" + e.getMessage());

        for(int i = 0; i < e.getStackTrace().length; i++) {
            writer.println(e.getStackTrace()[i].toString());
        }
        writer.close();
    }
}
