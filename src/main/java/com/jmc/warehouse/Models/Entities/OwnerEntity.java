package com.jmc.warehouse.Models.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "warehouse_owners")
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Integer ownerId;

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

    @Column(name = "tax_id")
    private String taxId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "admin_id")
    private AdminEntity createdBy;

    @Column(name = "created_at")
    private LocalDate createdAt;


    // No-argument constructor (required by Hibernate)
    public OwnerEntity() {}

    // Constructor with parameters
    public OwnerEntity(String fullName, String username, String email, String password,
                       String phone, String taxId, AdminEntity createdBy, LocalDate createdAt) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.taxId = taxId;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Integer getOwnerId() { return ownerId; }
    public void setOwnerId(Integer ownerId) { this.ownerId = ownerId; }

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

    public String getTaxId() { return taxId; }
    public void setTaxId(String taxId) { this.taxId = taxId; }

    public AdminEntity getCreatedBy() { return createdBy; }
    public void setCreatedBy(AdminEntity createdBy) { this.createdBy = createdBy; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
}