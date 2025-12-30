package com.jmc.warehouse.Models;

import com.jmc.warehouse.Models.Entities.AgentEntity;
import javafx.beans.property.*;

public class AgentNotification {

    private final IntegerProperty notificationId;
    private final StringProperty category;
    private final StringProperty info;
    private final ObjectProperty<AgentEntity> agent;

    public AgentNotification(Integer notificationId,
                             String category,
                             String info,
                             AgentEntity agent) {

        this.notificationId = new SimpleIntegerProperty(this, "NotificationId", notificationId);
        this.category = new SimpleStringProperty(this, "Category", category);
        this.info = new SimpleStringProperty(this, "Info", info);
        this.agent = new SimpleObjectProperty<>(this, "Agent", agent);
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

    // Agent
    public ObjectProperty<AgentEntity> agentProperty() { return agent; }
    public AgentEntity getAgent() { return agent.get(); }
    public void setAgent(AgentEntity value) { agent.set(value); }
}
