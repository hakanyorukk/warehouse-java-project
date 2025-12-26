package com.jmc.warehouse.Services.Admin;

import com.jmc.warehouse.Models.Entities.AdminEntity;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Model;

import java.time.LocalDate;

public class CreateAgentServiceImpl implements CreateAgentService{

    private final Model model;

    public CreateAgentServiceImpl(Model model) {
        this.model = model;
    }

    @Override
    public CreateAgentResult createAgent(String fullName, String username, String email, String password, String phone, Double commissionRate) {
        AdminEntity currentAdmin = model.getCurrentAdmin();
        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return CreateAgentResult.MISSING_FIELDS;
        } else {
            AgentEntity agent = new AgentEntity(fullName, username, email, password, phone, commissionRate, currentAdmin, LocalDate.now());
            boolean success = model.getDatabaseDriver().createAgent(agent);

            if(success) {
                return CreateAgentResult.SUCCESS;
            } else {
                return CreateAgentResult.ERROR;
            }
        }
    }
}
