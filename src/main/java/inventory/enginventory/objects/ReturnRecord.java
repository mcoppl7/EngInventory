package inventory.enginventory.objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReturnRecord {
    private SimpleStringProperty partNumber;                  // the tooling part number
    private  SimpleStringProperty category;                    // the category of the tool
    private  SimpleStringProperty checkoutDate;                // date the tool was checked out
    private SimpleIntegerProperty recordNumber;                // the PrimaryID (record #)
    private SimpleStringProperty shift;                        // shift employee works
    private SimpleStringProperty lineBuild;                     // shaft, main, body, pack
    private SimpleStringProperty lineNumber;                    // line tool is going to
    private SimpleStringProperty employeeName;                  // employee that checked out tool
    private SimpleIntegerProperty quantity;                     // quantity of items checked out (to be returned)

    /**
     * No-Arg Constructor
     */
    public ReturnRecord() {
        this.partNumber = new SimpleStringProperty("");
        this.category = new SimpleStringProperty("");
        this.checkoutDate = new SimpleStringProperty("");
        this.recordNumber = new SimpleIntegerProperty(0);
        this.shift = new SimpleStringProperty("");
        this.lineNumber = new SimpleStringProperty("");
        this.lineBuild = new SimpleStringProperty("");
        this.employeeName = new SimpleStringProperty("");
        this.quantity = new SimpleIntegerProperty(0);
    }

    /**
     * Constructor
     * @param partNumber the 399 part number
     * @param category   the tool category
     * @param checkoutDate  date tool was checked out
     * @param recordNum the primary ID/record number
     */
    public ReturnRecord(String partNumber, String category, String checkoutDate, int recordNum) {
        this.partNumber = new SimpleStringProperty(partNumber);
        this.category = new SimpleStringProperty(category);
        this.checkoutDate = new SimpleStringProperty(checkoutDate);
        this.recordNumber = new SimpleIntegerProperty(recordNum);
        this.shift = new SimpleStringProperty("");
        this.lineNumber = new SimpleStringProperty("");
        this.lineBuild = new SimpleStringProperty("");
        this.employeeName = new SimpleStringProperty("");
        this.quantity = new SimpleIntegerProperty(0);
    }

    public ReturnRecord(String partNumber, String category, String checkoutDate, int recordNum,
                        String shift, String lineBuild, String lineNumber, String employeeName) {
        this.partNumber = new SimpleStringProperty(partNumber);
        this.category = new SimpleStringProperty(category);
        this.checkoutDate = new SimpleStringProperty(checkoutDate);
        this.recordNumber = new SimpleIntegerProperty(recordNum);
        this.shift = new SimpleStringProperty(shift);
        this.lineNumber = new SimpleStringProperty(lineNumber);
        this.lineBuild = new SimpleStringProperty(lineBuild);
        this.employeeName = new SimpleStringProperty(employeeName);
        this.quantity = new SimpleIntegerProperty(0);
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

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setCategory(String category) {
        this.category.set(category);
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
    public int getRecordNumber() {
        return recordNumber.get();
    }

    public SimpleIntegerProperty recordNumberProperty() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber.set(recordNumber);
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

    public String getLineBuild() {
        return lineBuild.get();
    }

    public SimpleStringProperty lineBuildProperty() {
        return lineBuild;
    }

    public void setLineBuild(String lineBuild) {
        this.lineBuild.set(lineBuild);
    }

    public String getLineNumber() {
        return lineNumber.get();
    }

    public SimpleStringProperty lineNumberProperty() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber.set(lineNumber);
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

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }
}


