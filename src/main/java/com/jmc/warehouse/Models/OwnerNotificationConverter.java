package com.jmc.warehouse.Models;

import com.jmc.warehouse.Models.Entities.OwnerNotificationEntity;

public class OwnerNotificationConverter {

    // Convert Entity → JavaFX Model
    public static OwnerNotification toModel(OwnerNotificationEntity entity) {
        return new OwnerNotification(
                entity.getNotificationId(),
                entity.getCategory(),
                entity.getInfo(),
                entity.getOwner()
        );
    }

    // Convert JavaFX Model → Entity
    public static OwnerNotificationEntity toEntity(OwnerNotification model) {
        OwnerNotificationEntity entity = new OwnerNotificationEntity();
        entity.setNotificationId(model.getNotificationId());
        entity.setCategory(model.getCategory());
        entity.setInfo(model.getInfo());
        entity.setOwner(model.getOwner());

        return entity;
    }
}
