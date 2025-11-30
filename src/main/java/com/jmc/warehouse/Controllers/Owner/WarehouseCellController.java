package com.jmc.warehouse.Controllers.Owner;


import com.jmc.warehouse.Models.Warehouse;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class WarehouseCellController implements Initializable {
    public Label warehouse_name;
    public Label warehouse_address;
    public Label warehouse_agent;
    public Button edit_warehouse;
    public Label warehouse_area;

    private final Warehouse warehouse;
    public Label warehouse_dimension;


    public WarehouseCellController(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warehouse_name.textProperty().bind(warehouse.nameProperty());
        warehouse_address.textProperty().bind(warehouse.addressProperty());
        warehouse_area.textProperty().bind(warehouse.nameProperty());
        //warehouse_agent.textProperty().bind(warehouse.agentProperty().asString());
        warehouse_dimension.textProperty().bind(warehouse.dimensionsProperty());
        warehouse_area.textProperty().bind(warehouse.areaProperty().asString());

        warehouse_agent.textProperty().bind(
                warehouse.agentProperty().asString()
        );

    }
}
