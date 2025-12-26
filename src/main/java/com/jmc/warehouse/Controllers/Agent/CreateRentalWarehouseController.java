package com.jmc.warehouse.Controllers.Agent;

import com.jmc.warehouse.Models.*;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Entities.RentalWarehouseEntity;
import com.jmc.warehouse.Models.Entities.WarehouseEntity;
import com.jmc.warehouse.Services.Agent.CreateRentalWarehouseResult;
import com.jmc.warehouse.Services.Agent.CreateRentalWarehouseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private CreateRentalWarehouseService createRentalWarehouseService;
    private ObservableList<WarehouseDTO> warehouseList = FXCollections.observableArrayList();
    private static final Logger logger = LogManager.getLogger(CreateRentalWarehouseController.class);

    public void setCreateRentalWarehouseService(CreateRentalWarehouseService createRentalWarehouseService) {
        this.createRentalWarehouseService = createRentalWarehouseService;
    }

    public void setWarehouseList(ObservableList<WarehouseDTO> warehouseList) {
        this.warehouseList = warehouseList;

        if (warehouse_selector != null) {
            warehouse_selector.setItems(warehouseList);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        AgentEntity currentAgent = Model.getInstance().getCurrentAgent();

        // Loading warehouses
        List<WarehouseEntity> warehouses = Model.getInstance().getDatabaseDriver().getWarehousesByAgentId(currentAgent.getAgentId());
        List<WarehouseDTO> warehouesesList = new ArrayList<>();
        for(WarehouseEntity warehouse: warehouses) {
            warehouesesList.add(new WarehouseDTO(warehouse.getName(), warehouse.getAddress(), warehouse.getArea(), warehouse.getClimaticConditions(), warehouse.getId()));
        }

        // Assign agents
        ObservableList<WarehouseDTO> warehouseList = FXCollections.observableArrayList(warehouesesList);


         */
        //warehouse_selector.setValue(warehouseL);
        warehouse_selector.setItems(warehouseList);
        create_btn.setOnAction(event -> createRentalForm());
    }


    void createRentalForm() {
        WarehouseDTO selectedWarehouseDTO = (WarehouseDTO) warehouse_selector.getValue();
        String tenantName = tenant_name_fld.getText();
        String monthlyPrice = monthly_price_fld.getText();
        LocalDate startDate = start_date_picker.getValue();
        LocalDate endDate = end_date_picker.getValue();
        logger.info("Create rental form clicked");

        if(createRentalWarehouseService == null) {
            logger.error("CreateRentalWarehouseService was not injected");
            throw new IllegalStateException("CreateRentalWarehouseService was not injected");
        }

        logger.debug("Creating rental form for tenant={}", tenantName);
        CreateRentalWarehouseResult result = createRentalWarehouseService.createRentalForm(
                selectedWarehouseDTO, tenantName, monthlyPrice, startDate, endDate
        );
        error_lbl.setStyle("-fx-text-fill: red; -fx-font-size: 1.3em; -fx-font-weight: bold;");

        if(result == CreateRentalWarehouseResult.MISSING_FIELDS) {
            logger.warn("Rental creation failed: Missing fields");
            error_lbl.setText("Please fill all required fields!");
        } else if(result == CreateRentalWarehouseResult.SUCCESS) {
            logger.info("Rental warehouse created successfully");
            error_lbl.setText("Warehouse rented successfully!");
            error_lbl.setStyle("-fx-text-fill: green;");
            clearFields();
        } else {
            logger.error("Failed to create rental warehouse in database");
            error_lbl.setText("Error creating rental warehouse!");
            error_lbl.setStyle("-fx-text-fill: red;");
        }
    }

    public void clearFields() {
        tenant_name_fld.clear();
        monthly_price_fld.clear();
        start_date_picker.setValue(null);
        end_date_picker.setValue(null);
    }
}
