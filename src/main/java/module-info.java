module com.jmc.warehouse {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jmc.warehouse to javafx.fxml;
    exports com.jmc.warehouse;
    exports com.jmc.warehouse.Views;
    exports com.jmc.warehouse.Models;
    exports com.jmc.warehouse.Controllers;
    exports com.jmc.warehouse.Controllers.Client;
    exports com.jmc.warehouse.Controllers.Admin;


}