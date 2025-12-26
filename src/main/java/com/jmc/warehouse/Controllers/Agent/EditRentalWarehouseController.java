package com.jmc.warehouse.Controllers.Agent;

import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Models.RentalWarehouse;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditRentalWarehouseController implements Initializable {
    public TextField tenant_name_fld;
    public TextField monthly_price_fld;
    public DatePicker start_date_picker;
    public DatePicker end_date_picker;
    public Button create_btn;
    public Label error_lbl;
    private final RentalWarehouse rentalWarehouse;
    private static final Logger logger = LogManager.getLogger(EditRentalWarehouseController.class);

    public EditRentalWarehouseController(RentalWarehouse rentalWarehouse) {
        this.rentalWarehouse = rentalWarehouse;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //tenant_name.textProperty().bind(rentalWarehouse.tenantNameProperty());
        //monthly_price.textProperty().bind(rentalWarehouse.monthlyPriceProperty().asString());
        tenant_name_fld.setText(rentalWarehouse.getTenantName());
        monthly_price_fld.setText(String.valueOf(rentalWarehouse.getMonthlyPrice()));
        start_date_picker.setValue(rentalWarehouse.getStartDate());
        end_date_picker.setValue(rentalWarehouse.getEndDate());

        create_btn.setOnAction(event -> onEditRentalWarehouse());
    }

    private void onEditRentalWarehouse() {
        String tenantName = tenant_name_fld.getText();
        String monthlyPriceStr = monthly_price_fld.getText();
        BigDecimal monthlyPrice = new BigDecimal(monthly_price_fld.getText().trim());
        LocalDate startDate = start_date_picker.getValue();
        LocalDate endDate = end_date_picker.getValue();
        logger.info("Edit button clicked");
        try {
            monthlyPrice = BigDecimal.valueOf(Double.parseDouble(monthlyPriceStr));
        } catch (NumberFormatException e) {
            logger.error("Invalid monthly price");
            error_lbl.setText("Invalid monthly price.");
            return;
        }
        if (tenantName.isEmpty() || start_date_picker.getValue() == null || end_date_picker.getValue() == null) {
            logger.warn("Editing rental warehouse failed: missing fields");
            error_lbl.setText("Please fill in all fields.");
            return;
        }
        rentalWarehouse.setTenantName(tenantName);
        rentalWarehouse.setMonthlyPrice(monthlyPrice);
        rentalWarehouse.setStartDate(start_date_picker.getValue());
        rentalWarehouse.setEndDate(end_date_picker.getValue());

        //Model.getInstance().updateRentalWarehouse(rentalWarehouse);
        boolean success = Model.getInstance().getDatabaseDriver().updateRentalWarehouse(this.rentalWarehouse.getId(), tenantName, monthlyPrice, startDate, endDate);

        if (success) {
            logger.info("Rental warehouse edited successfully");
            error_lbl.setText("Rental warehouse edited successfully!");
            error_lbl.setStyle("-fx-text-fill: green;");
            //clearFields();
        } else {
            logger.error("Failed to edit");
            error_lbl.setText("Error editing rental warehouse!");
            error_lbl.setStyle("-fx-text-fill: red;");
        }

        AgentEntity currentAgent = Model.getInstance().getCurrentAgent();
        Model.getInstance().setAgentRentalWarehouses(currentAgent);
    }

    public void clearFields() {
        tenant_name_fld.clear();
        monthly_price_fld.clear();
        start_date_picker.cancelEdit();
        end_date_picker.cancelEdit();
    }
}
