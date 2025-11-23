package com.jmc.warehouse.Models;

public class OwnerConverter {

    // Convert Entity to JavaFX Model
    public static Owner toModel(OwnerEntity entity) {
        return new Owner(
                entity.getFullName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getTaxId(),
                entity.getCreatedAt()
        );
    }

    // Convert JavaFX Model to Entity
    public static OwnerEntity toEntity(Owner model) {
        OwnerEntity entity = new OwnerEntity();
        entity.setFullName(model.fullNameProperty().get());
        entity.setUsername(model.usernameProperty().get());
        entity.setEmail(model.emailProperty().get());
        entity.setPhone(model.phoneProperty().get());
        entity.setTaxId(model.taxIdProperty().get());
        entity.setCreatedAt(model.dateProperty().get());
        return entity;
    }
}