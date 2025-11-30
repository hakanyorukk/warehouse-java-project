package com.jmc.warehouse.Controllers.Agent;

import com.jmc.warehouse.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AgentController implements Initializable {

    public BorderPane agent_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAgentSelectedMenuItem().addListener((observable, oldVal, newVal)-> {
            switch(newVal) {
                case CREATE_RENTAL_FORM -> agent_parent.setCenter(Model.getInstance().getViewFactory().getCreateRentalWarehouseView());
                case NOTIFICATIONS -> agent_parent.setCenter(Model.getInstance().getViewFactory().getAgentNotificationsView());
                case REPORTS -> agent_parent.setCenter(Model.getInstance().getViewFactory().getAgentReportsView());
                default -> agent_parent.setCenter(Model.getInstance().getViewFactory().getAgentProfileView());
            }
        });
    }
}
