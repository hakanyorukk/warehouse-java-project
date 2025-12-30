package com.jmc.warehouse.Services.Owner;

import com.jmc.warehouse.Models.AgentDTO;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Entities.AgentNotificationEntity;
import com.jmc.warehouse.Models.Entities.OwnerEntity;
import com.jmc.warehouse.Models.Entities.WarehouseEntity;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Views.ClimaticConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class CreateWarehouseServiceImpl implements CreateWarehouseService {

    private final Model model;
    private static final Logger logger = LogManager.getLogger(CreateWarehouseServiceImpl.class);

    public CreateWarehouseServiceImpl(Model model) {this.model = model;}

    @Override
    public CreateWarehouseResult createWarehous(String name, String address, String dimensions, Double area, ClimaticConditions climaticCon, AgentDTO selectedAgentDTO) {
        OwnerEntity currentOwner = model.getCurrentOwner();
        AgentEntity assignedAgent = model.getDatabaseDriver().getAgentById(selectedAgentDTO.getAgentId());


        if(name.isEmpty() || address.isEmpty() || dimensions.isEmpty() || climaticCon == null) {
            return CreateWarehouseResult.MISSING_FIELDS;
        } else {
            WarehouseEntity warehouse = new WarehouseEntity(name, address, dimensions, area, LocalDate.now(), currentOwner, climaticCon, assignedAgent);
            boolean success = model.getDatabaseDriver().createWarehouse(warehouse);
            model.setOwnerWarehouses(currentOwner);
            if(success) {
                String warehouseCategory = "New warehouse";
                String warehouseInfo = "Id: " + warehouse.getId() + ", " + name +", Address: " + address +", Area: " + area + ", ";
                AgentNotificationEntity agentNotification = new AgentNotificationEntity(warehouseCategory, warehouseInfo, assignedAgent);
                boolean successAgent = model.getDatabaseDriver().createAgentNotification(agentNotification);

                if(successAgent) {
                    logger.debug("Notification created: {}", agentNotification);
                } else {
                    logger.error("Error creating notification");
                }

                return CreateWarehouseResult.SUCCESS;
            } else {
                return CreateWarehouseResult.ERROR;
            }

        }
    }
}
