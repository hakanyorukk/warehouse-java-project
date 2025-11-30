package com.jmc.warehouse.Models;

import com.jmc.warehouse.Models.RentalWarehouseEntity;
import com.jmc.warehouse.Models.RentalWarehouse;

public class RentalWarehouseConverter {

    // Convert Entity to JavaFX Model
    public static RentalWarehouse toModel(RentalWarehouseEntity entity) {
        return new RentalWarehouse(
                entity.getId(),
                entity.getWarehouseId(),
                entity.getAgentId(),
                entity.getTenantName(),
                entity.getMonthlyPrice(),
                entity.getStartDate(),
                entity.getEndDate()
        );
    }

    // Convert JavaFX Model to Entity
    public static RentalWarehouseEntity toEntity(RentalWarehouse model) {
        RentalWarehouseEntity entity = new RentalWarehouseEntity();
        entity.setWarehouseId(model.getWarehouseId());
        entity.setAgentId(model.getAgentId());
        entity.setTenantName(model.getTenantName());
        entity.setMonthlyPrice(model.getMonthlyPrice());
        entity.setStartDate(model.getStartDate());
        entity.setEndDate(model.getEndDate());
        return entity;
    }
}