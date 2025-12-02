package com.jmc.warehouse.Models;

import com.jmc.warehouse.Models.Entities.WarehouseEntity;

public class WarehouseConverter {

    // Convert Entity to JavaFX Model
    public static Warehouse toModel(WarehouseEntity entity) {
        return new Warehouse(
                entity.getId(),
                entity.getOwnerId(),
                entity.getName(),
                entity.getAddress(),
                entity.getDimensions(),
                entity.getArea(),
                entity.getClimaticConditions(),
                entity.getDateCreated(),
                entity.getAgent()

        );
    }

    // Convert JavaFX Model to Entity
    public static WarehouseEntity toEntity(Warehouse model) {
        WarehouseEntity entity = new WarehouseEntity();
        entity.setId(model.getId());
        entity.setOwnerId(model.getOwnerId());
        entity.setName(model.getName());
        entity.setAddress(model.getAddress());
        entity.setDimensions(model.getDimensions());
        entity.setArea(model.getArea());
        entity.setClimaticConditions(model.getClimaticCon());
        entity.setDateCreated(model.getDateCreated());
        entity.setAgent(model.getAgent());

        return entity;
    }
}