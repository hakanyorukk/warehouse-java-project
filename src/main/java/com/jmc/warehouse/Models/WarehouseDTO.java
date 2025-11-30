package com.jmc.warehouse.Models;

import com.jmc.warehouse.Views.ClimaticConditions;

public class WarehouseDTO {
    private String name;
    private String address;
    private Double area;
    private ClimaticConditions climaticCon;
    private Integer id;

    public WarehouseDTO(String name, String address, Double area, ClimaticConditions climaticCon, Integer id) {
        this.name = name;
        this.address = address;
        this.area = area;
        this.climaticCon = climaticCon;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Double getArea() {
        return area;
    }

    public ClimaticConditions getClimaticCon() {
        return climaticCon;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + " " + address + " " + "Area: " + area + " " + climaticCon + " ID: " + id;
    }
}
