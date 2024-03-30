package inventory.enginventory.objects;

import inventory.enginventory.alerts.CrashLog;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class CheckoutRecord {
    private SimpleIntegerProperty recordNumber;
    private SimpleStringProperty partNumber;
    private SimpleStringProperty category;
    private SimpleIntegerProperty quantity;
    private SimpleIntegerProperty borrowed;
    private SimpleIntegerProperty lineNumber;
    private SimpleStringProperty lineBuild;
    private SimpleStringProperty station;
    private SimpleStringProperty shift;
    private SimpleStringProperty employeeName;
    private SimpleIntegerProperty isReturned;
    private SimpleIntegerProperty transactionExcluded;
    private SimpleStringProperty checkoutDate;
    private SimpleStringProperty checkoutTime;
    private SimpleStringProperty reason;
    private SimpleStringProperty partName;

    // int as String variables
    private SimpleStringProperty borrowedAsString;
    private SimpleStringProperty returnedAsString;

    public CheckoutRecord() {
        // Default Values
        this.category = new SimpleStringProperty("");
        this.borrowed = new SimpleIntegerProperty(0);
        this.station = new SimpleStringProperty("");
        this.isReturned = new SimpleIntegerProperty(0);
        this.transactionExcluded = new SimpleIntegerProperty(0);
        this.reason = new SimpleStringProperty("");
        this.checkoutDate = new SimpleStringProperty("");
        this.checkoutTime = new SimpleStringProperty("");
        this.partName = new SimpleStringProperty("");

        this.partNumber = new SimpleStringProperty("");
        this.quantity = new SimpleIntegerProperty(0);
        this.lineNumber = new SimpleIntegerProperty(0);
        this.lineBuild = new SimpleStringProperty("");
        this.shift = new SimpleStringProperty("");
        this.employeeName = new SimpleStringProperty("");
    }

    //////////////////
    // Constructors //
    //////////////////
    public CheckoutRecord(int recordNumber, String partNumber, int quantity, int lineNumber, String lineBuild, String shift, String employeeName) {
        this.recordNumber = new SimpleIntegerProperty(recordNumber);
        this.partNumber = new SimpleStringProperty(partNumber);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.lineNumber = new SimpleIntegerProperty(lineNumber);
        this.lineBuild = new SimpleStringProperty(lineBuild);
        this.shift = new SimpleStringProperty(shift);
        this.employeeName = new SimpleStringProperty(employeeName);

        // Default Values
        this.category = new SimpleStringProperty("");
        this.borrowed = new SimpleIntegerProperty(0);
        this.station = new SimpleStringProperty("");
        this.isReturned = new SimpleIntegerProperty(0);
        this.transactionExcluded = new SimpleIntegerProperty(0);
        this.reason = new SimpleStringProperty("");
        this.checkoutDate = new SimpleStringProperty("");
        this.checkoutTime = new SimpleStringProperty("");
        this.partName = new SimpleStringProperty("");
    }

    public CheckoutRecord(int recordNumber, String partNumber, int quantity, int lineNumber, String lineBuild, String shift, String employeeName,
                          int borrowed, int returned) {
        this.recordNumber = new SimpleIntegerProperty(recordNumber);
        this.partNumber = new SimpleStringProperty(partNumber);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.lineNumber = new SimpleIntegerProperty(lineNumber);
        this.lineBuild = new SimpleStringProperty(lineBuild);
        this.shift = new SimpleStringProperty(shift);
        this.employeeName = new SimpleStringProperty(employeeName);
        this.borrowed = new SimpleIntegerProperty(borrowed);
        this.borrowedAsString = borrowedToString(borrowed);
        this.isReturned = new SimpleIntegerProperty(returned);
        this.returnedAsString = returnedToString(returned);

        // Default Values
        this.category = new SimpleStringProperty("");
        this.station = new SimpleStringProperty("");
        this.transactionExcluded = new SimpleIntegerProperty(0);
        this.reason = new SimpleStringProperty("");
        this.checkoutDate = new SimpleStringProperty("");
        this.checkoutTime = new SimpleStringProperty("");
        this.partName = new SimpleStringProperty("");
    }

    //////////////////////
    // Utility Methods //
    /////////////////////

    /**
     * Converts integer 0 or 1 yo "Yes" or "No"
     * @param borrowed an int 0 or 1
     * @return SimpleStringProperty "Yes" if 1 or "No" if 0
     */
    private SimpleStringProperty borrowedToString(int borrowed) {
        if(borrowed == 0) {
            return new SimpleStringProperty("No");
        }
        else {
            return new SimpleStringProperty("Yes");
        }
    }

    /**
     * Converts integer 0 or 1 yo "Yes" or "No"
     * @param returned an int 0 or 1
     * @return SimpleStringProperty "Yes" if 1 or "No" if 0
     */
    private SimpleStringProperty returnedToString(int returned) {
        if(returned == 0) {
            return new SimpleStringProperty("No");
        }
        else {
            return new SimpleStringProperty("Yes");
        }
    }

    /**
     * Used to display the date in a tableview in JavaFX.
     * Do not use to UPDATE or INSERT into Access Database.
     * Only for SELECT queries
     * Formats date as MM-DD-YYYY
     * @param checkoutDate
     */
    public void setcheckoutDateFromDB(String checkoutDate) {
        if(!(checkoutDate == null) || checkoutDate.length() > 0) {
            // date from database is in form yyyy-mm-dd 00:00:00:00
            // remove 00:00:00:00 from String
            int index = checkoutDate.indexOf(" ");
            checkoutDate = checkoutDate.substring(0,index);
        }

        this.checkoutDate.set(checkoutDate);
    }

    /**
     * Used to display the date in a tableview in JavaFX.
     * Do not use to UPDATE or INSERT into Access Database.
     * Only for SELECT queries
     * Formats time as hh:mm
     * @param checkoutTime
     */
    public void setcheckoutTimeFromDB(String checkoutTime) throws FileNotFoundException, UnsupportedEncodingException {
        if(!(checkoutTime == null) || checkoutTime.length() > 0) {
            // time from database is 1899-12-30 hh:mm:ss.000000
            // remove 1899-12-30 and ss.000000 from String

            try {
                int begin = checkoutTime.indexOf(" ");
                int end = checkoutTime.indexOf(".");
                checkoutTime = checkoutTime.substring(begin,end);
            } catch (StringIndexOutOfBoundsException e) {
                CrashLog.reportCrash(e);
            }
        }
        this.checkoutTime.set(checkoutTime);
    }
    @Override
    public String toString() {
        return "CheckoutRecord{" +
                "recordNumber=" + recordNumber +
                ", partNumber=" + partNumber +
                ", category=" + category +
                ", quantity=" + quantity +
                ", borrowed=" + borrowed +
                ", lineNumber=" + lineNumber +
                ", lineBuild=" + lineBuild +
                ", station=" + station +
                ", shift=" + shift +
                ", employeeName=" + employeeName +
                ", isReturned=" + isReturned +
                ", transactionExcluded=" + transactionExcluded +
                '}';
    }

    //////////////////////////
    // Getters and Setters //
    /////////////////////////


    public String getPartName() {
        return partName.get();
    }

    public SimpleStringProperty partNameProperty() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName.set(partName);
    }

    public String getReturnedAsString() {
        return returnedAsString.get();
    }

    public SimpleStringProperty returnedAsStringProperty() {
        return returnedAsString;
    }

    public void setReturnedAsString(String returnedAsString) {
        this.returnedAsString.set(returnedAsString);
    }

    public String getBorrowedAsString() {
        return borrowedAsString.get();
    }

    public SimpleStringProperty borrowedAsStringProperty() {
        return borrowedAsString;
    }

    public void setBorrowedAsString(String borrowedAsString) {
        this.borrowedAsString.set(borrowedAsString);
    }

    public String getReason() {
        return reason.get();
    }

    public SimpleStringProperty reasonProperty() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason.set(reason);
    }

    public int getRecordNumber() {
        return recordNumber.get();
    }

    public SimpleIntegerProperty recordNumberProperty() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber.set(recordNumber);
    }

    public String getPartNumber() {
        return partNumber.get();
    }

    public SimpleStringProperty partNumberProperty() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber.set(partNumber);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public int getBorrowed() {
        return borrowed.get();
    }

    public SimpleIntegerProperty borrowedProperty() {
        return borrowed;
    }

    public void setBorrowed(int borrowed) {
        this.borrowed.set(borrowed);
    }

    public int getLineNumber() {
        return lineNumber.get();
    }

    public SimpleIntegerProperty lineNumberProperty() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber.set(lineNumber);
    }

    public String getLineBuild() {
        return lineBuild.get();
    }

    public SimpleStringProperty lineBuildProperty() {
        return lineBuild;
    }

    public void setLineBuild(String lineBuild) {
        this.lineBuild.set(lineBuild);
    }

    public String getStation() {
        return station.get();
    }

    public SimpleStringProperty stationProperty() {
        return station;
    }

    public void setStation(String station) {
        this.station.set(station);
    }

    public String getShift() {
        return shift.get();
    }

    public SimpleStringProperty shiftProperty() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift.set(shift);
    }

    public String getEmployeeName() {
        return employeeName.get();
    }

    public SimpleStringProperty employeeNameProperty() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName.set(employeeName);
    }

    public int getIsReturned() {
        return isReturned.get();
    }

    public SimpleIntegerProperty isReturnedProperty() {
        return isReturned;
    }

    public void setIsReturned(int isReturned) {
        this.isReturned.set(isReturned);
    }

    public int getTransactionExcluded() {
        return transactionExcluded.get();
    }

    public SimpleIntegerProperty transactionExcludedProperty() {
        return transactionExcluded;
    }

    public void setTransactionExcluded(int transactionExcluded) {
        this.transactionExcluded.set(transactionExcluded);
    }

    public String getCheckoutDate() {
        return checkoutDate.get();
    }

    public SimpleStringProperty checkoutDateProperty() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate.set(checkoutDate);
    }

    public String getCheckoutTime() {
        return checkoutTime.get();
    }

    public SimpleStringProperty checkoutTimeProperty() {
        return checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime.set(checkoutTime);
    }
}
