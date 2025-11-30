package com.jmc.warehouse.Views;

import com.jmc.warehouse.Controllers.Agent.RentalWarehouseCellController;
import com.jmc.warehouse.Models.RentalWarehouse;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class RentalWarehouseCellFactory extends ListCell<RentalWarehouse> {

    public RentalWarehouseCellFactory() {}

    @Override
    protected void updateItem(RentalWarehouse rentalWarehouse, boolean empty) {
        super.updateItem(rentalWarehouse, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Agent/AgentRentalWarehouseCell.fxml"));
            RentalWarehouseCellController controller = new RentalWarehouseCellController(rentalWarehouse);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
