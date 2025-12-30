package com.jmc.warehouse.Services.Agent;

import com.jmc.warehouse.Models.Entities.*;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Models.WarehouseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;


public class CreateRentalWarehouseServiceImpl implements CreateRentalWarehouseService {

    private final Model model;

    private static final Logger logger = LogManager.getLogger(CreateRentalWarehouseServiceImpl.class);


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
                String warehouseCategory = "Warehouse rented, ";
                String warehouseInfo = "by " + currentAgent.getFullName() + "( Id: "+ currentAgent.getAgentId() + "), Warehouse Id: " + selectedWarehouseDTO.getId() + ", " + selectedWarehouseDTO.getName() +", Address: " + selectedWarehouseDTO.getAddress() +", Area: " + selectedWarehouseDTO.getArea() + ", ";
                OwnerNotificationEntity ownerNotification = new OwnerNotificationEntity(warehouseCategory, warehouseInfo, selectedWarehouseDTO.getOwnerId());
                boolean successOwner = model.getDatabaseDriver().createOwnerNotification(ownerNotification);
                if(successOwner) {
                    logger.debug("Notification created: {}", ownerNotification);
                } else {
                    logger.error("Error creating notification");
                }
                return CreateRentalWarehouseResult.SUCCESS;
            } else {
                return CreateRentalWarehouseResult.ERROR;
            }
        }
    }
}
