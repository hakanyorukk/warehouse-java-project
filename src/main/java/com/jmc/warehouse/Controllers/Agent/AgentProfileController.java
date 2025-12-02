package com.jmc.warehouse.Controllers.Agent;

import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Views.RentalWarehouseCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class AgentProfileController implements Initializable {
    public ListView rental_warehouse_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initDate();
        rental_warehouse_listview.setItems(Model.getInstance().getAgentRentalWarehouses());
        rental_warehouse_listview.setCellFactory(e-> new RentalWarehouseCellFactory());
    }

    private void initDate() {
        AgentEntity currentAgent = Model.getInstance().getCurrentAgent();
        if(Model.getInstance().getAgentRentalWarehouses().isEmpty()) {
            Model.getInstance().setAgentRentalWarehouses(currentAgent);
        }
    }
}
