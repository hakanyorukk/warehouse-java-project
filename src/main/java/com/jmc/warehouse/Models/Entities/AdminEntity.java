package com.jmc.warehouse.Models.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "admins")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "created_at")
    private LocalDate createdAt;

    // No-argument constructor
    public AdminEntity() {}

    // Constructor with parameters
    public AdminEntity(String username, String password, LocalDate createdAt) {
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    public Integer getAgentId() { return adminId; }
    public void setAgentId(Integer agentId) { this.adminId = agentId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }


    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
}