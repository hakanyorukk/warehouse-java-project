package com.jmc.warehouse.Services.Owner;

import com.jmc.warehouse.Models.AgentDTO;
import com.jmc.warehouse.Views.ClimaticConditions;

public interface CreateWarehouseService {
    CreateWarehouseResult createWarehous(String name, String address, String dimensions, Double area, ClimaticConditions climaticCon, AgentDTO selectedAgentDTO);
}
