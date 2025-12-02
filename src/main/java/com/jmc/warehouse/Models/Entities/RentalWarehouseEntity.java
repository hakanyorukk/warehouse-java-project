package com.jmc.warehouse.Models.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "warehouse_rental")
public class RentalWarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private WarehouseEntity warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", referencedColumnName = "agent_id")
    private AgentEntity agent;

    @Column(name = "tenant_name", length = 100, nullable = false)
    private String tenantName;

    @Column(name = "monthly_price", precision = 5, scale = 2, nullable = false)
    private BigDecimal monthlyPrice;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    // Default constructor (required by JPA)
    public RentalWarehouseEntity() {}

    // Constructor with all fields
    public RentalWarehouseEntity(WarehouseEntity warehouse, AgentEntity agent,
                                 String tenantName, BigDecimal monthlyPrice,LocalDate startDate, LocalDate endDate) {
        this.warehouse = warehouse;
        this.agent = agent;
        this.tenantName = tenantName;
        this.monthlyPrice = monthlyPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public WarehouseEntity getWarehouseId() {return warehouse;}
    public void setWarehouseId(WarehouseEntity warehouseId) {this.warehouse = warehouseId;}

    public AgentEntity getAgentId() {return agent;}
    public void setAgentId(AgentEntity agentId) {this.agent = agentId;}

    public String getTenantName() {return tenantName;}
    public void setTenantName(String tenantName) {this.tenantName = tenantName;}

    public BigDecimal getMonthlyPrice() {return monthlyPrice;}
    public void setMonthlyPrice(BigDecimal monthlyPrice) {this.monthlyPrice = monthlyPrice;}

    public LocalDate getStartDate() {return startDate;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    public LocalDate getEndDate() {return endDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}
}