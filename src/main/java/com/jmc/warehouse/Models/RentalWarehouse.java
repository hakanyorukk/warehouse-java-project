package com.jmc.warehouse.Models;

import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Entities.WarehouseEntity;
import javafx.beans.property.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalWarehouse {
    private final IntegerProperty id;
    private final ObjectProperty<WarehouseEntity> warehouseId;
    private final ObjectProperty<AgentEntity> agentId;
    private final StringProperty tenantName;
    private final ObjectProperty<BigDecimal> monthlyPrice;
    private final ObjectProperty<LocalDate> startDate;
    private final ObjectProperty<LocalDate> endDate;


    public RentalWarehouse(Integer id, WarehouseEntity warehouseId, AgentEntity agentId,
                           String tenantName, BigDecimal monthlyPrice, LocalDate startDate, LocalDate endDate) {
        this.id = new SimpleIntegerProperty(this, "ID", id);
        this.warehouseId = new SimpleObjectProperty<>(this, "WarehouseID", warehouseId);
        this.agentId = new SimpleObjectProperty<>(this, "AgentID", agentId);
        this.tenantName = new SimpleStringProperty(this, "TenantName", tenantName);
        this.monthlyPrice = new SimpleObjectProperty<>(this, "MonthlyPrice", monthlyPrice);
        this.startDate = new SimpleObjectProperty<>(this, "StartDate", startDate);

        this.endDate = new SimpleObjectProperty<>(this, "EndDate", endDate);

    }

    // ID property methods
    public IntegerProperty idProperty() {return id;}
    public int getId() {return id.get();}
    public void setId(int value) {id.set(value);}

    // Warehouse ID property methods
    public ObjectProperty<WarehouseEntity> warehouseNameProperty() {return warehouseId;}
    public WarehouseEntity getWarehouseId() {return warehouseId.get();}
    public void setWarehouseId(WarehouseEntity value) {warehouseId.set(value);}

    // Agent ID property methods
    public ObjectProperty<AgentEntity> agentIdProperty() {return agentId;}
    public AgentEntity getAgentId() {return agentId.get();}
    public void setAgentId(AgentEntity value) {agentId.set(value);}

    // Tenant Name property methods
    public StringProperty tenantNameProperty() {return tenantName;}
    public String getTenantName() {return tenantName.get();}
    public void setTenantName(String value) {tenantName.set(value);}

    // Monthly Price property methods
    public ObjectProperty<BigDecimal> monthlyPriceProperty() {return monthlyPrice;}
    public BigDecimal getMonthlyPrice() {return monthlyPrice.get();}
    public void setMonthlyPrice(BigDecimal value) {monthlyPrice.set(value);}

    // Start Date property methods
    public ObjectProperty<LocalDate> startDateProperty() {return startDate;}
    public LocalDate getStartDate() {return startDate.get();}
    public void setStartDate(LocalDate value) {startDate.set(value);}

    // End Date property methods
    public ObjectProperty<LocalDate> endDateProperty() {return endDate;}
    public LocalDate getEndDate() {return endDate.get();}
    public void setEndDate(LocalDate value) {endDate.set(value);}
}