package com.jmc.warehouse.Views;

import com.jmc.warehouse.Controllers.Owner.WarehouseCellController;
import com.jmc.warehouse.Models.Warehouse;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class WarehouseCellFactory extends ListCell<Warehouse> {

    public WarehouseCellFactory() {}

    @Override
    protected void updateItem(Warehouse warehouse, boolean empty) {
        super.updateItem(warehouse, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Owner/OwnerWarehouseCell.fxml"));
            WarehouseCellController controller = new WarehouseCellController(warehouse);
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
