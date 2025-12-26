module com.jmc.warehouse {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires javafx.base;
    requires org.apache.logging.log4j;
    //requires com.jmc.warehouse;


    opens com.jmc.warehouse.Models.Entities to org.hibernate.orm.core;

    opens com.jmc.warehouse to javafx.fxml;
    exports com.jmc.warehouse;
    exports com.jmc.warehouse.Views;
    exports com.jmc.warehouse.Models;
    exports com.jmc.warehouse.Controllers;
    exports com.jmc.warehouse.Controllers.Agent;
    exports com.jmc.warehouse.Controllers.Owner;
    exports com.jmc.warehouse.Controllers.Admin;
    exports com.jmc.warehouse.Models.Entities;


}