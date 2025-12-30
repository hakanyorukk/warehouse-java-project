package com.jmc.warehouse.Models.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "owner_notifications")
public class OwnerNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer notificationId;

    @Column(name = "category", nullable = false, length = 150)
    private String category;

    @Column(name = "info", nullable = false, length = 150)
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id", nullable = false)
    private OwnerEntity owner;

    public OwnerNotificationEntity() {}

    public OwnerNotificationEntity(String category, String info, OwnerEntity owner) {
        this.category = category;
        this.info = info;
        this.owner = owner;
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

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "OwnerNotificationEntity{" +
                "notificationId=" + notificationId +
                ", category='" + category + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
