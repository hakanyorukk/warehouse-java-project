package com.jmc.warehouse.Controllers.Agent;

import com.jmc.warehouse.Models.AgentNotification;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class AgentNotificationCellController implements Initializable {
    private final AgentNotification agentNotification;
    public Label notification_category;
    public Label warehouse;
    private static final Logger logger = LogManager.getLogger(AgentNotificationCellController.class);

    public AgentNotificationCellController(AgentNotification agentNotification) {
        this.agentNotification = agentNotification;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notification_category.textProperty().bind(agentNotification.categoryProperty());
        warehouse.textProperty().bind(agentNotification.infoProperty());
    }
}
