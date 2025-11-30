package com.jmc.warehouse.Controllers.Agent;

import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Models.RentalWarehouse;
import com.jmc.warehouse.Views.AgentMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RentalWarehouseCellController implements Initializable {
    private final RentalWarehouse rentalWarehouse;
    public Label tenant_name;
    public Label monthly_price;
    public Label end_date;
    public Label warehouse_name;
    public Button edit_rentalWarehouse;
    public Label start_date;

    public RentalWarehouseCellController(RentalWarehouse rentalWarehouse) {
        this.rentalWarehouse = rentalWarehouse;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tenant_name.textProperty().bind(rentalWarehouse.tenantNameProperty());
        monthly_price.textProperty().bind(rentalWarehouse.monthlyPriceProperty().asString());
        end_date.textProperty().bind(rentalWarehouse.endDateProperty().asString());
        start_date.textProperty().bind(rentalWarehouse.startDateProperty().asString());
        warehouse_name.textProperty().bind(rentalWarehouse.warehouseNameProperty().asString());
        edit_rentalWarehouse.setOnAction(event -> onEditRentalForm(rentalWarehouse.getId()));
    }

    private void onEditRentalForm(int rentalWarehouseId) {
        Model.getInstance().setRentalWarehouseToEdit(rentalWarehouseId);
        Model.getInstance().getViewFactory().showRentalWarehouseEditWindow();
       // Model.getInstance().getViewFactory().getAgentSelectedMenuItem().set(AgentMenuOptions.CREATE_RENTAL_FORM);
    }
}
