package com.jmc.warehouse.Services;
import com.jmc.warehouse.Models.Entities.AdminEntity;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Entities.AgentNotificationEntity;
import com.jmc.warehouse.Models.Entities.OwnerEntity;
import com.jmc.warehouse.Models.Model;

import com.jmc.warehouse.Views.AccountType;


public class LoginServiceImpl implements LoginService {

    private final Model model;

    public LoginServiceImpl(Model model) {
        this.model = model;
    }

    @Override
    public LoginResult login(String username, String password, AccountType type) {

        switch (type) {
            case ADMIN -> {
                model.evaluateAdminCred(username, password);
                AdminEntity admin =
                        model.getDatabaseDriver().getAdminData(username, password);

                if (model.getAdminLoginSuccessFlag()) {
                    model.setCurrentAdmin(admin);
                    return LoginResult.SUCCESS;
                }
            }

            case WAREHOUSE_OWNER -> {
                model.evaluateOwnerCred(username, password);
                OwnerEntity owner =
                        model.getDatabaseDriver().getOwnerData(username, password);

                if (model.getOwnerLoginSuccessFlag()) {
                    model.setCurrentOwner(owner);
                    model.setOwnerWarehouses(owner);
                    model.setOwnerNotifications(owner);
                    return LoginResult.SUCCESS;
                }
            }

            case WAREHOUSE_AGENT -> {
                model.evaluateAgentCred(username, password);
                AgentEntity agent =
                        model.getDatabaseDriver().getAgentData(username, password);

                if (model.getAgentLoginSuccessFlag()) {
                    model.setCurrentAgent(agent);
                    model.setAgentRentalWarehouses(agent);
                    model.setAgentNotifications(agent);
                    return LoginResult.SUCCESS;
                }
            }
        }

        return LoginResult.INVALID_CREDENTIALS;
    }

}
