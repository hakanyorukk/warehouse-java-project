package com.jmc.warehouse.Models;

import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Entities.OwnerEntity;
import com.jmc.warehouse.Views.ClimaticConditions;
import javafx.beans.property.*;

import java.time.LocalDate;

public class Warehouse {
    private final IntegerProperty id;           // ADD THIS
    private final ObjectProperty<OwnerEntity> ownerId;      // ADD THIS
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty dimensions;
    private final DoubleProperty area;
    private final ObjectProperty<ClimaticConditions> climaticCon;
    private final ObjectProperty<LocalDate> dateCreated;
    private final ObjectProperty<AgentEntity> agentId;
   // private final ObjectProperty<Agent> agent;


    public Warehouse(Integer id, OwnerEntity ownerId, String name, String address,
                     String dimensions, Double area, ClimaticConditions climaticCon, LocalDate date, AgentEntity agentId) {
        this.id = new SimpleIntegerProperty(this, "ID", id);
        this.ownerId = new SimpleObjectProperty<>(this, "OwnerID", ownerId);
        this.name = new SimpleStringProperty(this, "Name", name);
        this.address = new SimpleStringProperty(this, "Address", address);
        this.dimensions = new SimpleStringProperty(this, "Dimensions", dimensions);
        this.area = new SimpleDoubleProperty(this, "Area", area);
        this.climaticCon = new SimpleObjectProperty<>(this, "Climatic Conditions", climaticCon);
        this.dateCreated = new SimpleObjectProperty<>(this, "Date", date);
        //this.agent = new SimpleObjectProperty<>(this, "Agent", agent);
        this.agentId = new SimpleObjectProperty<>(this, "AgentId", agentId);

    }

    // ID property methods
    public IntegerProperty idProperty() {return id;}
    public int getId() {return id.get();}
    public void setId(int value) {id.set(value);}

    // Owner ID property methods
    public ObjectProperty<OwnerEntity> ownerIdProperty() {return ownerId;}
    public OwnerEntity getOwnerId() {return ownerId.get();}

    // Existing property methods
    public StringProperty nameProperty() {return name;}
    public String getName() {return name.get();}
    public void setName(String value) {name.set(value);}

    public StringProperty addressProperty() {return address;}
    public String getAddress() {return address.get();}
    public void setAddress(String value) {address.set(value);}

    public StringProperty dimensionsProperty() {return dimensions;}
    public String getDimensions() {return dimensions.get();}
    public void setDimensions(String value) {dimensions.set(value); }

    public DoubleProperty areaProperty() {return area;}
    public double getArea() {return area.get();}
    public void setArea(double value) {area.set(value);}

    public ObjectProperty<ClimaticConditions> climaticConProperty() {return climaticCon;}
    public ClimaticConditions getClimaticCon() {return climaticCon.get();}
    public void setClimaticCon(ClimaticConditions value) {climaticCon.set(value);}

    public ObjectProperty<LocalDate> dateProperty() {return dateCreated;}
    public LocalDate getDateCreated() {return dateCreated.get();}


    public ObjectProperty<AgentEntity> agentProperty() { return agentId;}
    public AgentEntity getAgent() {return  agentId.get();}
    public void setAgent(AgentEntity value) {agentId.set(value);}





}