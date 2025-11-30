package com.jmc.warehouse.Controllers.Owner;

import com.jmc.warehouse.Models.Warehouse;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class OwnerReportCellController implements Initializable {


    private final Warehouse warehouse;
    public Label warehouse_price;

    public OwnerReportCellController(Warehouse warehouse) {
        this.warehouse = warehouse;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
