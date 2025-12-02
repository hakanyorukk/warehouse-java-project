package com.jmc.warehouse.Controllers.Agent;

import com.jmc.warehouse.Models.*;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Entities.RentalWarehouseEntity;
import com.jmc.warehouse.Models.Entities.WarehouseEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateRentalWarehouseController implements Initializable {
    public TextField tenant_name_fld;
    public TextField monthly_price_fld;
    public DatePicker start_date_picker;
    public DatePicker end_date_picker;
    public ChoiceBox warehouse_selector;
    public Button create_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AgentEntity currentAgent = Model.getInstance().getCurrentAgent();

        // Loading warehouses
        List<WarehouseEntity> warehouses = Model.getInstance().getDatabaseDriver().getWarehousesByAgentId(currentAgent.getAgentId());
        List<WarehouseDTO> warehouesesList = new ArrayList<>();
        for(WarehouseEntity warehouse: warehouses) {
            warehouesesList.add(new WarehouseDTO(warehouse.getName(), warehouse.getAddress(), warehouse.getArea(), warehouse.getClimaticConditions(), warehouse.getId()));
        }

        // Assign agents
        ObservableList<WarehouseDTO> warehouseList = FXCollections.observableArrayList(warehouesesList);
        warehouse_selector.setItems(warehouseList);
        //warehouse_selector.setValue(warehouseL);
        create_btn.setOnAction(event -> createRentalForm(currentAgent));
    }

    public void createRentalForm(AgentEntity currentAgent) {
        WarehouseDTO selectedWarehouseDTO = (WarehouseDTO) warehouse_selector.getValue();
        WarehouseEntity assignedWarehouse = Model.getInstance().getDatabaseDriver().getWarehouseById(selectedWarehouseDTO.getId());
        String tenantName = tenant_name_fld.getText();
        BigDecimal monthlyPrice = new BigDecimal(monthly_price_fld.getText().trim());
        LocalDate startDate = start_date_picker.getValue();
        LocalDate endDate = end_date_picker.getValue();

        error_lbl.setStyle("-fx-text-fill: red; -fx-font-size: 1.3em; -fx-font-weight: bold;");

        if(tenantName.isEmpty() || startDate == null || endDate == null) {
            error_lbl.setText("Please fill all required fields!");
            return;
        }

        // Create Rental warehoue
        RentalWarehouseEntity rentalWarehouse = new RentalWarehouseEntity(assignedWarehouse, currentAgent, tenantName, monthlyPrice, startDate, endDate);
        boolean success = Model.getInstance().getDatabaseDriver().createRentalWarehouse(rentalWarehouse);

        if (success) {
            error_lbl.setText("Warehouse rented successfully!");
            error_lbl.setStyle("-fx-text-fill: green;");
            clearFields();
        } else {
            error_lbl.setText("Error creating rental warehouse!");
            error_lbl.setStyle("-fx-text-fill: red;");
        }
        // Refresh rental warehouses
        Model.getInstance().setAgentRentalWarehouses(currentAgent);
    }

    public void clearFields() {
        tenant_name_fld.clear();
        monthly_price_fld.clear();
        start_date_picker.cancelEdit();
        end_date_picker.cancelEdit();
    }
}
