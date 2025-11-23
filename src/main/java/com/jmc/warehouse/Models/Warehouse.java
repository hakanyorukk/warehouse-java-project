package com.jmc.warehouse.Models;

import com.jmc.warehouse.Views.ClimaticConditions;
import javafx.beans.property.*;

import java.time.LocalDate;

public class Warehouse {
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty dimensions;
    private final DoubleProperty area;
    private final ObjectProperty<ClimaticConditions> climaticCon;
    private final IntegerProperty pricePerMonth;
    private final ObjectProperty<LocalDate> dateCreated;

    public Warehouse(String name, String address, String dimensions, Double area, ClimaticConditions climaticCon, Integer pricePerMonth, LocalDate date) {
        this.name = new SimpleStringProperty(this, "Name", name);
        this.address = new SimpleStringProperty(this, "Address", address);
        this.dimensions = new SimpleStringProperty(this, "Dimensions", dimensions);
        this.area = new SimpleDoubleProperty(this, "Area", area);
        this.climaticCon = new SimpleObjectProperty<>(this, "Climatic Conditions", climaticCon);
        this.pricePerMonth = new SimpleIntegerProperty(this, "Price Per Month", pricePerMonth);
        this.dateCreated = new SimpleObjectProperty<>(this, "Date", date);

    }

    public StringProperty nameProperty() {return name;}

    public StringProperty addressProperty() {return address;}

    public StringProperty dimensionsProperty() {return dimensions;}

    public DoubleProperty areaProperty() {return area;}

    public ObjectProperty<ClimaticConditions> climaticConProperty() {return climaticCon;}

    public IntegerProperty pricerPerMonthProperty() {return pricePerMonth;}

    public ObjectProperty<LocalDate> dateProperty() {return dateCreated;}
}
