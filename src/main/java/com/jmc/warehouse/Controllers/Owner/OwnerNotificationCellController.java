package com.jmc.warehouse.Controllers.Owner;

import com.jmc.warehouse.Models.OwnerNotification;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class OwnerNotificationCellController implements Initializable {
    private final OwnerNotification ownerNotification;
    public Label notification_category;
    public Label warehouse;
    private static final Logger logger = LogManager.getLogger(OwnerNotificationCellController.class);

    public OwnerNotificationCellController(OwnerNotification ownerNotification) {
        this.ownerNotification = ownerNotification;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notification_category.textProperty().bind(ownerNotification.categoryProperty());
        warehouse.textProperty().bind(ownerNotification.infoProperty());
    }
}
