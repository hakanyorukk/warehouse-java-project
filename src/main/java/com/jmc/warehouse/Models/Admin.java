package com.jmc.warehouse.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Admin {
    private final StringProperty username;
    private final ObjectProperty<LocalDate> dateCreated;

    public Admin(String username, LocalDate date) {
        this.username = new SimpleStringProperty(this, "Username", username);
        this.dateCreated = new SimpleObjectProperty<>(this, "Date", date);
    }

    public StringProperty userNameProperty() {return username;}

    public ObjectProperty<LocalDate> dateProperty() {return dateCreated;}
}
