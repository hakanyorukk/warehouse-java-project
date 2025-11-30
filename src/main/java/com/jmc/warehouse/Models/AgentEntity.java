package com.jmc.warehouse.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "warehouse_agents")
public class AgentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Integer agentId;

    @Column(name = "fullName", length = 150)
    private String fullName;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "commission_rate")
    private Double commissionRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "admin_id")
    private AdminEntity createdBy;

    @Column(name = "created_at")
    private LocalDate createdAt;

    // No-argument constructor
    public AgentEntity() {}

    // Constructor with parameters
    public AgentEntity(String fullName, String username, String email, String password,
                       String phone, Double commissionRate, AdminEntity createdBy, LocalDate createdAt) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.commissionRate = commissionRate;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    // Getters and Setters (same pattern as OwnerEntity)
    public Integer getAgentId() { return agentId; }
    public void setAgentId(Integer agentId) { this.agentId = agentId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Double getCommissionRate() { return commissionRate; }
    public void setCommissionRate(Double commissionRate) { this.commissionRate = commissionRate; }

    public AdminEntity getCreatedBy() { return createdBy; }
    public void setCreatedBy(AdminEntity createdBy) { this.createdBy = createdBy; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return getFullName();
    }

}