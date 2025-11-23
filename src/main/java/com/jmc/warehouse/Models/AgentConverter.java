package com.jmc.warehouse.Models;

public class AgentConverter {

    // Convert Entity to JavaFX Model
    public static Agent toModel(AgentEntity entity) {
        return new Agent(
                entity.getFullName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getCommissionRate(),
                entity.getCreatedAt()
        );
    }

    // Convert JavaFX Model to Entity
    public static AgentEntity toEntity(Agent model) {
        AgentEntity entity = new AgentEntity();
        entity.setFullName(model.fullNameProperty().get());
        entity.setUsername(model.usernameProperty().get());
        entity.setEmail(model.emailProperty().get());
        entity.setPhone(model.phoneProperty().get());
        entity.setCommissionRate(model.commissionRateProperty().get());
        entity.setCreatedAt(model.dateProperty().get());
        return entity;
    }
}