package com.jmc.warehouse.Models;

import com.jmc.warehouse.Models.Entities.AdminEntity;
import javafx.beans.property.*;

import java.time.LocalDate;

public class Agent {
    private final StringProperty fullName;
    private final StringProperty username;
    private final StringProperty email;
    private final StringProperty phone;
    private final DoubleProperty commissionRate;
    private final ObjectProperty<AdminEntity> createdBy;
    private final ObjectProperty<LocalDate> dateCreated;

    public Agent(String fullName, String username, String email, String phone, Double commissionRate, AdminEntity createdBy, LocalDate dateCreated) {
        this.fullName = new SimpleStringProperty(this, "Full Name", fullName);
        this.username = new SimpleStringProperty(this, "Username", username);
        this.email = new SimpleStringProperty(this, "Email", email);
        this.phone = new SimpleStringProperty(this, "Phone", phone);
        this.commissionRate = new SimpleDoubleProperty(this, "Commission Rate", commissionRate);
        this.createdBy = new SimpleObjectProperty<>(this, "Created By", createdBy);
        this.dateCreated = new SimpleObjectProperty<>(this, "Date", dateCreated);
    }

    public StringProperty fullNameProperty() {return fullName;}

    public StringProperty usernameProperty() {return username;}

    public StringProperty emailProperty() {return email;}

    public StringProperty phoneProperty() {return phone;}

    public DoubleProperty commissionRateProperty() {return commissionRate;}

    public ObjectProperty<AdminEntity> createdByProperty() {return createdBy;}

    public  ObjectProperty<LocalDate> dateProperty() {return dateCreated;}

    @Override
    public String toString() {
        return fullName.get();
    }

}
