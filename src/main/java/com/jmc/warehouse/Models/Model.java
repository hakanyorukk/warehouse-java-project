package com.jmc.warehouse.Models;

import com.jmc.warehouse.Models.Entities.*;
import com.jmc.warehouse.Views.AccountType;
import com.jmc.warehouse.Views.ViewFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

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
   private final Warehouse warehouse;
   private final RentalWarehouse rentalWarehouse;
   private final ObjectProperty<AdminEntity> currentAdmin;
   private final ObjectProperty<AgentEntity> currentAgent;
   private final ObjectProperty<OwnerEntity> currentOwner;

   private final ObservableList<Warehouse> warehouses;
   private final ObservableList<RentalWarehouse> rentalwarehouses;

    private final ObservableList<AgentNotification> agentNotifications;
    private final ObservableList<OwnerNotification> ownerNotifications;


    private static final Logger logger = LogManager.getLogger(Model.class);

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DataBaseDriver();
        this.adminLoginSuccessFlag = false;
        this.ownerLoginSuccessFlag = false;
        this.agentLoginSuccessFlag = false;
        this.owner = new Owner("", "", "", "", "", null,null);
        this.admin = new Admin("", null);
        this.agent = new Agent("", "", "", "", 0.0, null, null);
        this.warehouse = new Warehouse(0, null, "", "", "", 0.0, null, null, null);
        this.rentalWarehouse = new RentalWarehouse(0, null, null,"", null, null, null);
        this.currentAdmin = new SimpleObjectProperty<>();
        this.currentAgent = new SimpleObjectProperty<>();
        this.currentOwner = new SimpleObjectProperty<>();
        this.warehouses = FXCollections.observableArrayList();
        this.rentalwarehouses = FXCollections.observableArrayList();
        this.agentNotifications = FXCollections.observableArrayList();
        this.ownerNotifications = FXCollections.observableArrayList();

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
                logger.info("Owner Credentials successful");
            }
        } catch (Exception e) {
            logger.error("Error evaluating owner credentials: {}", e.getMessage());
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
                logger.info("Admin credentials successful");
            }
        } catch ( Exception e) {
            logger.error("Error evaluating admin credentials: {}", e.getMessage());
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
                logger.info("Agent credentials successful");
            }
        } catch ( Exception e) {
            logger.error("Error evaluating agent credentials: {}", e.getMessage());
        }
    }

    // get warehouse from database
    public ObservableList<Warehouse> getOwnerWarehouses() {
        return warehouses;
    }

    // Load all warehouses for current owner
    public void setOwnerWarehouses(OwnerEntity currentOwner) {
        warehouses.clear();

        List<WarehouseEntity> warehouseEntities =
                databaseDriver.getWarehousesByOwnerId(currentOwner.getOwnerId());

        if(warehouseEntities == null) {
            return;
        }
        for (WarehouseEntity entity : warehouseEntities) {
            Warehouse warehouse = new Warehouse(
                    entity.getId(),
                    entity.getOwnerId(),
                    entity.getName(),
                    entity.getAddress(),
                    entity.getDimensions(),
                    entity.getArea(),
                    entity.getClimaticConditions(),
                    entity.getDateCreated(),
                    entity.getAgent()

            );
            warehouses.add(warehouse);
        }
    }

    // load all RentalWarehouses for current agent
    public ObservableList<RentalWarehouse> getAgentRentalWarehouses() {
        return rentalwarehouses;
    }
    // Load all warehouses for current owner
    public void setAgentRentalWarehouses(AgentEntity currentAgent) {
        rentalwarehouses.clear();

        List<RentalWarehouseEntity> rentalWarehouseEntities =
                databaseDriver.getRentalWarehousesByAgentId(currentAgent.getAgentId());

        if (rentalWarehouseEntities == null) {
            return;
        }
        for (RentalWarehouseEntity entity : rentalWarehouseEntities) {
            RentalWarehouse rentalwarehouse = new RentalWarehouse(
                    entity.getId(),
                    entity.getWarehouseId(),
                    entity.getAgentId(),
                    entity.getTenantName(),
                    entity.getMonthlyPrice(),
                    entity.getStartDate(),
                    entity.getEndDate()
            );
            rentalwarehouses.add(rentalwarehouse);
        }
    }

    // load notifications for agent
    public ObservableList<AgentNotification> getAgentNotifications() {
        return agentNotifications;
    }

    public void setAgentNotifications(AgentEntity currentAgent) {
        agentNotifications.clear();

        List<AgentNotificationEntity> entities =
                databaseDriver.getAgentNotificationsByAgentId(
                        currentAgent.getAgentId()
                );

        if (entities == null) return;

        for (AgentNotificationEntity entity : entities) {
            AgentNotification notification =
                    AgentNotificationConverter.toModel(entity);

            agentNotifications.add(notification);
        }
    }


    public ObservableList<OwnerNotification> getOwnerNotifications() {
        return ownerNotifications;
    }

    public void setOwnerNotifications(OwnerEntity currentOwner) {
        ownerNotifications.clear();

        List<OwnerNotificationEntity> entities =
                databaseDriver.getOwnerNotificationsByOwnerId(
                        currentOwner.getOwnerId()
                );

        if (entities == null) return;

        for (OwnerNotificationEntity entity : entities) {
            OwnerNotification notification =
                    OwnerNotificationConverter.toModel(entity);

            ownerNotifications.add(notification);
        }
    }

    // load notifications for owner


    public RentalWarehouse getRentalWarehouseToEdit() {
        return rentalWarehouse;
    }

    public void setRentalWarehouseToEdit(int rentalWarehouseId) {
        // Implementation for setting rental warehouse to edit
        RentalWarehouseEntity entity = databaseDriver.getRentalWarehouseById(rentalWarehouseId);
        if (entity != null) {
            rentalWarehouse.idProperty().set(entity.getId());
            rentalWarehouse.warehouseNameProperty().set(entity.getWarehouseId());
            rentalWarehouse.agentIdProperty().set(entity.getAgentId());
            rentalWarehouse.tenantNameProperty().set(entity.getTenantName());
            rentalWarehouse.monthlyPriceProperty().set(entity.getMonthlyPrice());
            rentalWarehouse.startDateProperty().set(entity.getStartDate());
            rentalWarehouse.endDateProperty().set(entity.getEndDate());
        }
    }

    public Warehouse getWarehouseToEdit() {
        return warehouse;
    }

    public void setWarehouseToEdit(int warehouseId) {
        WarehouseEntity entity = databaseDriver.getWarehouseById(warehouseId);
        if (entity != null) {
            warehouse.idProperty().set(entity.getId());
            warehouse.ownerIdProperty().set(entity.getOwnerId());
            warehouse.nameProperty().set(entity.getName());
            warehouse.addressProperty().set(entity.getAddress());
            warehouse.dimensionsProperty().set(entity.getDimensions());
            warehouse.areaProperty().set(entity.getArea());
            warehouse.climaticConProperty().set(entity.getClimaticConditions());
            warehouse.dateProperty().set(entity.getDateCreated());
            warehouse.agentProperty().set(entity.getAgent());
        }
    }

    public AdminEntity getCurrentAdmin() {
        return currentAdmin.get();
    }
    public void setCurrentAdmin(AdminEntity admin) {
        this.currentAdmin.set(admin);
    }

    public OwnerEntity getCurrentOwner() {
        return currentOwner.get();
    }
    public void setCurrentOwner(OwnerEntity owner) {
        this.currentOwner.set(owner);
    }

    public AgentEntity getCurrentAgent() {
        return currentAgent.get();
    }
    public void setCurrentAgent(AgentEntity agent) {
        this.currentAgent.set(agent);
    }

    public ObjectProperty<AdminEntity> currentAdminProperty() {
        return currentAdmin;
    }
}
