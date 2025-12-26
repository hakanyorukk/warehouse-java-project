package com.jmc.warehouse.Services.Admin;

import com.jmc.warehouse.Models.Entities.AdminEntity;
import com.jmc.warehouse.Models.Entities.AgentEntity;

import java.time.LocalDate;

public interface CreateAgentService {
    CreateAgentResult createAgent(String fullName, String username, String email, String password, String phone, Double commisionRate);
}
