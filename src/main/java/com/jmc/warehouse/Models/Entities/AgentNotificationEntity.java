package com.jmc.warehouse.Models.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "agent_notifications")
public class AgentNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer notificationId;

    @Column(name = "category", nullable = false, length = 150)
    private String category;

    @Column(name = "info", nullable = false, length = 150)
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", referencedColumnName = "agent_id", nullable = false)
    private AgentEntity agent;

    public AgentNotificationEntity() {}

    public AgentNotificationEntity(String category, String info, AgentEntity agent) {
        this.category = category;
        this.info = info;
        this.agent = agent;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public AgentEntity getAgent() {
        return agent;
    }

    public void setAgent(AgentEntity agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "AgentNotificationEntity{" +
                "notificationId=" + notificationId +
                ", category='" + category + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
