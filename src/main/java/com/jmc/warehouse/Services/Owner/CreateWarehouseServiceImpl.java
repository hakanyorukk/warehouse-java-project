package com.jmc.warehouse.Services.Owner;

import com.jmc.warehouse.Models.AgentDTO;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Entities.OwnerEntity;
import com.jmc.warehouse.Models.Entities.WarehouseEntity;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Views.ClimaticConditions;

import java.time.LocalDate;

public class CreateWarehouseServiceImpl implements CreateWarehouseService {

    private final Model model;

    public CreateWarehouseServiceImpl(Model model) {this.model = model;}

    @Override
    public CreateWarehouseResult createWarehous(String name, String address, String dimensions, Double area, ClimaticConditions climaticCon, AgentDTO selectedAgentDTO) {
        OwnerEntity currentOwner = model.getCurrentOwner();
        AgentEntity assignedAgent = model.getDatabaseDriver().getAgentById(selectedAgentDTO.getAgentId());

        if(name.isEmpty() || address.isEmpty() || dimensions.isEmpty() || climaticCon == null) {
            return CreateWarehouseResult.MISSING_FIELDS;
        } else {
            WarehouseEntity warehouse = new WarehouseEntity(name, address, dimensions, area, LocalDate.now(), currentOwner,climaticCon, assignedAgent);
            boolean success = Model.getInstance().getDatabaseDriver().createWarehouse(warehouse);
            model.setOwnerWarehouses(currentOwner);
            if(success) {
                return CreateWarehouseResult.SUCCESS;
            } else {
                return CreateWarehouseResult.ERROR;
            }

        }
    }
}
