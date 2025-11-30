package com.jmc.warehouse.Controllers.Owner;

import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Models.OwnerEntity;
import com.jmc.warehouse.Models.Warehouse;
import com.jmc.warehouse.Views.WarehouseCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class OwnerProfileController implements Initializable {
    public ListView<Warehouse> warehouses_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initDate();
        warehouses_listview.setItems(Model.getInstance().getOwnerWarehouses());
        warehouses_listview.setCellFactory(e-> new WarehouseCellFactory());
    }

    private void initDate() {
        OwnerEntity currentOwner = Model.getInstance().getCurrentOwner();
        if(Model.getInstance().getOwnerWarehouses().isEmpty()) {
            Model.getInstance().setOwnerWarehouses(currentOwner);
        }
    }
}
