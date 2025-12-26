package com.jmc.warehouse.Services.Agent;

import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Entities.RentalWarehouseEntity;
import com.jmc.warehouse.Models.Entities.WarehouseEntity;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Models.WarehouseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;


public class CreateRentalWarehouseServiceImpl implements CreateRentalWarehouseService {

    private final Model model;

    public CreateRentalWarehouseServiceImpl(Model model) {this.model = model;}

    @Override
    public CreateRentalWarehouseResult createRentalForm(WarehouseDTO selectedWarehouseDTO, String tenantName, String monthlyPrice, LocalDate startDate, LocalDate endDate) {
        AgentEntity currentAgent = model.getCurrentAgent();
        WarehouseEntity assignedWarehouse = model.getDatabaseDriver().getWarehouseById(selectedWarehouseDTO.getId());
        BigDecimal monthlyPriceDec = new BigDecimal(monthlyPrice.trim());

        if(tenantName.isEmpty() || startDate == null || endDate == null) {
            return CreateRentalWarehouseResult.MISSING_FIELDS;
        } else {
            RentalWarehouseEntity rentalWarehouse = new RentalWarehouseEntity(assignedWarehouse, currentAgent, tenantName, monthlyPriceDec, startDate, endDate);
            boolean success = model.getDatabaseDriver().createRentalWarehouse(rentalWarehouse);
            model.setAgentRentalWarehouses(currentAgent);
            if(success) {
                return CreateRentalWarehouseResult.SUCCESS;
            } else {
                return CreateRentalWarehouseResult.ERROR;
            }
        }
    }
}
