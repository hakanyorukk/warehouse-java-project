package com.jmc.warehouse.Views;

import com.jmc.warehouse.Controllers.Agent.AgentNotificationCellController;
import com.jmc.warehouse.Controllers.Owner.OwnerNotificationCellController;
import com.jmc.warehouse.Models.OwnerNotification;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OwnerNotificationCellFactory extends ListCell<OwnerNotification> {
    public OwnerNotificationCellFactory() {}

    private static final Logger logger = LogManager.getLogger(OwnerNotificationCellFactory.class);

    @Override
    protected void updateItem(OwnerNotification ownerNotification, boolean empty) {
        super.updateItem(ownerNotification, empty);

        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Owner/OwnerNotificationCell.fxml"));
            OwnerNotificationCellController controller = new OwnerNotificationCellController(ownerNotification);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                logger.error("Error getting owner notifications: {}", e.getMessage());
            }
        }
    }
}
