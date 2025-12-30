package com.jmc.warehouse.Models;

import com.jmc.warehouse.Models.Entities.OwnerEntity;
import javafx.beans.property.*;

public class OwnerNotification {

    private final IntegerProperty notificationId;
    private final StringProperty category;
    private final StringProperty info;
    private final ObjectProperty<OwnerEntity> owner;

    public OwnerNotification(Integer notificationId,
                             String category,
                             String info,
                             OwnerEntity owner) {

        this.notificationId = new SimpleIntegerProperty(this, "NotificationId", notificationId);
        this.category = new SimpleStringProperty(this, "Category", category);
        this.info = new SimpleStringProperty(this, "Info", info);
        this.owner = new SimpleObjectProperty<>(this, "Owner", owner);
    }

    // Notification ID
    public IntegerProperty notificationIdProperty() { return notificationId; }
    public int getNotificationId() { return notificationId.get(); }

    // Category
    public StringProperty categoryProperty() { return category; }
    public String getCategory() { return category.get(); }
    public void setCategory(String value) { category.set(value); }

    // Info
    public StringProperty infoProperty() { return info; }
    public String getInfo() { return info.get(); }
    public void setInfo(String value) { info.set(value); }

    // Owner
    public ObjectProperty<OwnerEntity> ownerProperty() { return owner; }
    public OwnerEntity getOwner() { return owner.get(); }
    public void setOwner(OwnerEntity value) { owner.set(value); }
}
