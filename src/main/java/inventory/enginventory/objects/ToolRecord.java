package inventory.enginventory.objects;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ToolRecord {
    private SimpleStringProperty partNumber;
    private SimpleStringProperty category;
    private SimpleIntegerProperty rev;
    private SimpleStringProperty description;
    private SimpleStringProperty vendor;
    private SimpleDoubleProperty cost;
    private SimpleBooleanProperty archive;
    private SimpleIntegerProperty archiveInt;
    private SimpleStringProperty station;
    private  SimpleStringProperty location;
    private SimpleIntegerProperty reorderPoint;
    private SimpleIntegerProperty onHand;
    private SimpleStringProperty pdfLocation;
    private SimpleStringProperty partName;
    private SimpleIntegerProperty partID;

    public ToolRecord() {
        this.partNumber = new SimpleStringProperty("N/A");
        this.category = new SimpleStringProperty("N/A");
        this.rev = new SimpleIntegerProperty(0);
        this.description = new SimpleStringProperty("");
        this.vendor = new SimpleStringProperty("");
        this.cost = new SimpleDoubleProperty(0);
        this.archive = new SimpleBooleanProperty(false);
        this.station = new SimpleStringProperty("");
        this.location = new SimpleStringProperty("");
        this.reorderPoint = new SimpleIntegerProperty(0);
        this.onHand = new SimpleIntegerProperty(0);
        this.pdfLocation = new SimpleStringProperty("");
        this.partName = new SimpleStringProperty("");
        this.partID = new SimpleIntegerProperty(0);
        this.archiveInt = new SimpleIntegerProperty(0);
    }

    public ToolRecord(String partNumber) {
        this.partNumber = new SimpleStringProperty(partNumber);
        this.category = new SimpleStringProperty("N/A");
        this.rev = new SimpleIntegerProperty(0);
        this.description = new SimpleStringProperty("");
        this.vendor = new SimpleStringProperty("");
        this.cost = new SimpleDoubleProperty(0);
        this.archive = new SimpleBooleanProperty(false);
        this.station = new SimpleStringProperty("");
        this.location = new SimpleStringProperty("");
        this.reorderPoint = new SimpleIntegerProperty(0);
        this.onHand = new SimpleIntegerProperty(0);
        this.pdfLocation = new SimpleStringProperty("");
        this.partName = new SimpleStringProperty("");
        this.partID = new SimpleIntegerProperty(0);
        this.archiveInt = new SimpleIntegerProperty(0);
    }

    public ToolRecord(String partNumber, int onHand) {
        this.location = new SimpleStringProperty(partNumber);
        this.onHand = new SimpleIntegerProperty(onHand);
        this.partNumber = new SimpleStringProperty("N/A");
        this.category = new SimpleStringProperty("N/A");
        this.rev = new SimpleIntegerProperty(0);
        this.description = new SimpleStringProperty("");
        this.vendor = new SimpleStringProperty("");
        this.cost = new SimpleDoubleProperty(0);
        this.archive = new SimpleBooleanProperty(false);
        this.station = new SimpleStringProperty("");
        this.reorderPoint = new SimpleIntegerProperty(0);
        this.pdfLocation = new SimpleStringProperty("");
        this.partName = new SimpleStringProperty("");
        this.partID = new SimpleIntegerProperty(0);
        this.archiveInt = new SimpleIntegerProperty(0);
    }

    public ToolRecord(SimpleStringProperty partNumber, SimpleStringProperty category, SimpleIntegerProperty rev, SimpleStringProperty description,
                      SimpleStringProperty vendor, SimpleDoubleProperty cost, SimpleBooleanProperty archive,
                      SimpleStringProperty station, SimpleStringProperty location, SimpleIntegerProperty reorderPoint,
                      SimpleIntegerProperty onHand, SimpleStringProperty pdfLocation) {
        this.partNumber = partNumber;
        this.category = category;
        this.rev = rev;
        this.description = description;
        this.vendor = vendor;
        this.cost = cost;
        this.archive = archive;
        this.station = station;
        this.location = location;
        this.reorderPoint = reorderPoint;
        this.onHand = onHand;
        this.pdfLocation = pdfLocation;
    }

    public ToolRecord(SimpleStringProperty partNumber, SimpleStringProperty category, SimpleIntegerProperty rev, SimpleStringProperty description,
                      SimpleStringProperty vendor, SimpleDoubleProperty cost, SimpleBooleanProperty archive,
                      SimpleStringProperty station, SimpleStringProperty location, SimpleIntegerProperty reorderPoint,
                      SimpleIntegerProperty onHand, SimpleStringProperty pdfLocation, SimpleStringProperty partName) {
        this.partNumber = partNumber;
        this.category = category;
        this.rev = rev;
        this.description = description;
        this.vendor = vendor;
        this.cost = cost;
        this.archive = archive;
        this.station = station;
        this.location = location;
        this.reorderPoint = reorderPoint;
        this.onHand = onHand;
        this.pdfLocation = pdfLocation;
        this.partName = partName;
    }

    public ToolRecord(String partNumber, String category, int rev, String description, String vendor, double cost, String station,
                      String location, int reOrderPoint, int onHand, String pdfLocation, Boolean archive) {
        this.partNumber = new SimpleStringProperty(partNumber);
        this.category = new SimpleStringProperty(category);
        this.rev = new SimpleIntegerProperty(rev);
        this.description = new SimpleStringProperty(description);
        this.vendor = new SimpleStringProperty(vendor);
        this.cost = new SimpleDoubleProperty(cost);
        this.archive = new SimpleBooleanProperty(archive);
        this.station = new SimpleStringProperty(station);
        this.location = new SimpleStringProperty(location);
        this.reorderPoint = new SimpleIntegerProperty(reOrderPoint);
        this.onHand = new SimpleIntegerProperty(onHand);
        this.pdfLocation = new SimpleStringProperty(pdfLocation);
    }

    public ToolRecord(String partNumber, String category, int rev, String description, String vendor, double cost, String station,
                      String location, int reOrderPoint, int onHand, String pdfLocation, Boolean archive, String partName, int partID) {
        this.partNumber = new SimpleStringProperty(partNumber);
        this.category = new SimpleStringProperty(category);
        this.rev = new SimpleIntegerProperty(rev);
        this.description = new SimpleStringProperty(description);
        this.vendor = new SimpleStringProperty(vendor);
        this.cost = new SimpleDoubleProperty(cost);
        this.archive = new SimpleBooleanProperty(archive);
        this.station = new SimpleStringProperty(station);
        this.location = new SimpleStringProperty(location);
        this.reorderPoint = new SimpleIntegerProperty(reOrderPoint);
        this.onHand = new SimpleIntegerProperty(onHand);
        this.pdfLocation = new SimpleStringProperty(pdfLocation);
        this.partName = new SimpleStringProperty(partName);
        this.partID = new SimpleIntegerProperty(partID);
    }

    public int getPartID() {
        return partID.get();
    }

    public SimpleIntegerProperty partIDProperty() {
        return partID;
    }

    public ToolRecord(String partNumber, String description, int onHand) {
        this.partNumber = new SimpleStringProperty(partNumber);
        this.description = new SimpleStringProperty(description);
        this.onHand = new SimpleIntegerProperty(onHand);
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

    public int getRev() {
        return rev.get();
    }

    public SimpleIntegerProperty revProperty() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev.set(rev);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getVendor() {
        return vendor.get();
    }

    public SimpleStringProperty vendorProperty() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor.set(vendor);
    }

    public double getCost() {
        return cost.get();
    }

    public SimpleDoubleProperty costProperty() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }

    public boolean isArchive() {
        return archive.get();
    }

    public SimpleBooleanProperty archiveProperty() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive.set(archive);
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

    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public int getReorderPoint() {
        return reorderPoint.get();
    }

    public SimpleIntegerProperty reorderPointProperty() {
        return reorderPoint;
    }

    public void setReorderPoint(int reorderPoint) {
        this.reorderPoint.set(reorderPoint);
    }

    public int getOnHand() {
        return onHand.get();
    }

    public SimpleIntegerProperty onHandProperty() {
        return onHand;
    }

    public void setOnHand(int onHand) {
        this.onHand.set(onHand);
    }

    public String getPdfLocation() {
        return pdfLocation.get();
    }

    public SimpleStringProperty pdfLocationProperty() {
        return pdfLocation;
    }

    public void setPdfLocation(String pdfLocation) {
        this.pdfLocation.set(pdfLocation);
    }

    @Override
    public String toString() {
        return "ToolRecord{" +
                "partNumber=" + partNumber +
                ",partName=" + partName +
                ", category=" + category +
                ", rev=" + rev +
                ", description=" + description +
                ", vendor=" + vendor +
                ", cost=" + cost +
                ", archive=" + archive +
                ", station=" + station +
                ", location=" + location +
                ", reorderPoint=" + reorderPoint +
                ", onHand=" + onHand +
                ", pdfLocation=" + pdfLocation +
                '}';
    }

    public String getPartName() {
        return partName.get();
    }

    public SimpleStringProperty partNameProperty() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName.set(partName);
    }

    public int getArchiveInt() {
        return archiveInt.get();
    }

    public SimpleIntegerProperty archiveIntProperty() {
        return archiveInt;
    }

    public void setArchiveInt(int archiveInt) {
        this.archiveInt.set(archiveInt);
    }

    public void setPartID(int partID) {
        this.partID.set(partID);
    }
}
