package com.jmc.warehouse.Services.Agent;

import com.jmc.warehouse.Models.WarehouseDTO;

import java.time.LocalDate;

public interface CreateRentalWarehouseService {
    CreateRentalWarehouseResult createRentalForm(WarehouseDTO selectedWarehouseDTO, String tenantName, String monthlyPrice, LocalDate startDate, LocalDate endDate);
}
