package com.jmc.warehouse.Models;

import com.jmc.warehouse.Views.AccountType;
import com.jmc.warehouse.Views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DataBaseDriver databaseDriver;
    private AccountType loginAccountType = AccountType.ADMIN;
    private boolean ownerLoginSuccessFlag;
    private boolean agentLoginSuccessFlag;
    private boolean adminLoginSuccessFlag;
    // owner, agent & admin
   private final Owner owner;
   private final Agent agent;
   private final Admin admin;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DataBaseDriver();
        this.adminLoginSuccessFlag = false;
        this.ownerLoginSuccessFlag = false;
        this.agentLoginSuccessFlag = false;
        this.owner = new Owner("", "", "", "", "", null);
        this.admin = new Admin("", null);
        this.agent = new Agent("", "", "", "", 0.0, null);
    }

    public static synchronized Model getInstance() {
        if(model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
    public DataBaseDriver getDatabaseDriver() {return databaseDriver;}
    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    // owner method section
    public boolean getOwnerLoginSuccessFlag() {return this.ownerLoginSuccessFlag;}
    public void setOwnerLoginSuccessFlag(boolean flag) {this.ownerLoginSuccessFlag = flag;}
    public Owner getOwner() {return owner;}
    public void evaluateOwnerCred(String username, String password) {
        // Get OwnerEntity from database using Hibernate
        OwnerEntity ownerEntity = databaseDriver.getOwnerData(username, password);
        try {
            // Check if owner exists (similar to resultSet.isBeforeFirst())
            if (ownerEntity != null) {
                // Set properties in the JavaFX Owner object
                this.owner.fullNameProperty().set(ownerEntity.getFullName());
                this.owner.usernameProperty().set(ownerEntity.getUsername());
                this.owner.emailProperty().set(ownerEntity.getEmail());
                this.owner.phoneProperty().set(ownerEntity.getPhone());
                this.owner.taxIdProperty().set(ownerEntity.getTaxId());
                this.owner.dateProperty().set(ownerEntity.getCreatedAt());

                // Set login success flag
                this.ownerLoginSuccessFlag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //admin method section
    public boolean getAdminLoginSuccessFlag() {return this.adminLoginSuccessFlag;}
    public void setAdminLoginSuccessFlag(boolean flag) {this.adminLoginSuccessFlag = flag;}
    public Admin getAdmin() {return admin;}
    public void evaluateAdminCred(String username, String password) {
        // Get AdminEntity from database using Hibernate
        AdminEntity adminEntity = databaseDriver.getAdminData(username, password);
        try{
            if(adminEntity != null) {
                this.admin.userNameProperty().set(adminEntity.getUsername());
                this.admin.dateProperty().set(adminEntity.getCreatedAt());

                this.adminLoginSuccessFlag = true;
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    //agent method section
    public boolean getAgentLoginSuccessFlag() {return this.agentLoginSuccessFlag;}
    public void setAgentLoginSuccessFlag(boolean flag) {this.agentLoginSuccessFlag = flag;}
    public Agent getAgent() {return agent;}
    public void evaluateAgentCred(String username, String password) {
        // Get AdminEntity from database using Hibernate
        AgentEntity agentEntity = databaseDriver.getAgentData(username, password);
        try{
            if(agentEntity != null) {
                this.agent.fullNameProperty().set(agentEntity.getFullName());
                this.agent.usernameProperty().set(agentEntity.getUsername());
                this.agent.emailProperty().set(agentEntity.getEmail());
                this.agent.phoneProperty().set(agentEntity.getPhone());
                this.agent.commissionRateProperty().set(agentEntity.getCommissionRate());
                this.agent.dateProperty().set(agentEntity.getCreatedAt());
                this.agentLoginSuccessFlag = true;
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }


}
