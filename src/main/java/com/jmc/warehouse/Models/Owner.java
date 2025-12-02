package com.jmc.warehouse.Models;

import com.jmc.warehouse.Models.Entities.AdminEntity;
import javafx.beans.property.*;

import java.time.LocalDate;

public class Owner {
    private final StringProperty fullName;
    private final StringProperty username;
    private final StringProperty email;
    private final StringProperty phone;
    private final StringProperty taxId;
    private final ObjectProperty<AdminEntity> createdBy;
    private final ObjectProperty<LocalDate> dateCreated;

    public Owner(String fullName, String username, String email, String phone, String taxId, AdminEntity createdBy, LocalDate dateCreated) {
        this.fullName = new SimpleStringProperty(this, "Full Name", fullName);
        this.username = new SimpleStringProperty(this, "Username", username);
        this.email = new SimpleStringProperty(this, "Email", email);
        this.phone = new SimpleStringProperty(this, "Phone", phone);
        this.taxId = new SimpleStringProperty(this, "Tax Id", taxId);
        this.createdBy = new SimpleObjectProperty<>(this, "Created By", createdBy);
        this.dateCreated = new SimpleObjectProperty<>(this, "Date", dateCreated);
    }

    public StringProperty fullNameProperty() {return fullName;}

    public StringProperty usernameProperty() {return username;}

    public StringProperty emailProperty() {return email;}

    public StringProperty phoneProperty() {return phone;}

    public StringProperty taxIdProperty() {return taxId;}

    public ObjectProperty<AdminEntity> createdByProperty() {return createdBy;}

    public  ObjectProperty<LocalDate> dateProperty() {return dateCreated;}
}
