package com.jmc.warehouse.Models;

import com.jmc.warehouse.Models.Entities.AgentNotificationEntity;

public class AgentNotificationConverter {

    // Convert Entity → JavaFX Model
    public static AgentNotification toModel(AgentNotificationEntity entity) {
        return new AgentNotification(
                entity.getNotificationId(),
                entity.getCategory(),
                entity.getInfo(),
                entity.getAgent()
        );
    }

    // Convert JavaFX Model → Entity
    public static AgentNotificationEntity toEntity(AgentNotification model) {
        AgentNotificationEntity entity = new AgentNotificationEntity();
        entity.setNotificationId(model.getNotificationId());
        entity.setCategory(model.getCategory());
        entity.setInfo(model.getInfo());
        entity.setAgent(model.getAgent());

        return entity;
    }
}
