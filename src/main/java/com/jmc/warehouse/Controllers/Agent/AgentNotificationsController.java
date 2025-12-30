package com.jmc.warehouse.Controllers.Agent;

import com.jmc.warehouse.Models.AgentNotification;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Views.AgentNotificationCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class AgentNotificationsController implements Initializable {
    public ListView<AgentNotification> agent_notifications_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initDate();
        agent_notifications_listview.setItems(Model.getInstance().getAgentNotifications());
        agent_notifications_listview.setCellFactory(e-> new AgentNotificationCellFactory());
    }

    private void initDate() {
        AgentEntity currentAgent = Model.getInstance().getCurrentAgent();
       if(Model.getInstance().getAgentNotifications().isEmpty()) {
           Model.getInstance().setAgentNotifications(currentAgent);
        }
    }
}
