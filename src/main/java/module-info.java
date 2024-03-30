module inventory.enginventory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires ucanaccess;
    requires org.apache.commons.lang3;


    opens inventory.enginventory to javafx.fxml;
    exports inventory.enginventory.gui;
    exports inventory.enginventory.controllers;
    exports inventory.enginventory.database;
    opens inventory.enginventory.database;
    opens inventory.enginventory.controllers to javafx.fxml;
    opens inventory.enginventory.objects;
}