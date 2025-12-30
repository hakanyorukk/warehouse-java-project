package com.jmc.warehouse.Views;

import com.jmc.warehouse.Controllers.Agent.AgentNotificationCellController;
import com.jmc.warehouse.Models.AgentNotification;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AgentNotificationCellFactory extends ListCell<AgentNotification> {
    public AgentNotificationCellFactory() {}

    private static final Logger logger = LogManager.getLogger(AgentNotificationCellFactory.class);

    @Override
    protected void updateItem(AgentNotification agentNotification, boolean empty) {
        super.updateItem(agentNotification, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Agent/AgentNotificationCell.fxml"));
            AgentNotificationCellController controller = new AgentNotificationCellController(agentNotification);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                logger.error("Error getting agent notifications: {}", e.getMessage());
            }
        }
    }
}
