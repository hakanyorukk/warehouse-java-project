package com.jmc.warehouse.Views;

import com.jmc.warehouse.Controllers.Admin.AdminController;
import com.jmc.warehouse.Controllers.Admin.CreateAgent;
import com.jmc.warehouse.Controllers.Admin.CreateOwner;
import com.jmc.warehouse.Controllers.Agent.AgentController;
import com.jmc.warehouse.Controllers.Agent.CreateRentalWarehouseController;
import com.jmc.warehouse.Controllers.Agent.EditRentalWarehouseController;
import com.jmc.warehouse.Controllers.LoginController;
import com.jmc.warehouse.Controllers.Owner.CreateWarehouseController;
import com.jmc.warehouse.Controllers.Owner.EditWarehouseController;
import com.jmc.warehouse.Controllers.Owner.OwnerController;
import com.jmc.warehouse.Models.AgentDTO;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Entities.WarehouseEntity;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Models.WarehouseDTO;
import com.jmc.warehouse.Services.Admin.CreateAgentService;
import com.jmc.warehouse.Services.Admin.CreateAgentServiceImpl;
import com.jmc.warehouse.Services.Admin.CreateOwnerService;
import com.jmc.warehouse.Services.Admin.CreateOwnerServiceImpl;
import com.jmc.warehouse.Services.Agent.CreateRentalWarehouseService;
import com.jmc.warehouse.Services.Agent.CreateRentalWarehouseServiceImpl;
import com.jmc.warehouse.Services.LoginService;
import com.jmc.warehouse.Services.LoginServiceImpl;
import com.jmc.warehouse.Services.Owner.CreateWarehouseService;
import com.jmc.warehouse.Services.Owner.CreateWarehouseServiceImpl;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ViewFactory {

    private AccountType loginAccountType;

    // Admin views
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane adminDashboardView;
    private AnchorPane createOwnerView;
    private AnchorPane createAgentView;

    // Owner views
    private final ObjectProperty<OwnerMenuOptions> ownerSelectedMenuItem;
    private AnchorPane createWarehouseView;
    private AnchorPane ownerProfileView;
    private AnchorPane ownerReportsView;
    private AnchorPane ownerNotificationsView;

    // Agent Views
    private final ObjectProperty<AgentMenuOptions> agentSelectedMenuItem;
    private AnchorPane createRentalWarehouseView;
    private AnchorPane agentProfileView;
    private AnchorPane agentReportsView;
    private AnchorPane agentNotificationsView;

    private static final Logger logger = LogManager.getLogger(ViewFactory.class);


    public ViewFactory() {
        this.loginAccountType = AccountType.ADMIN; // default value
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
        this.ownerSelectedMenuItem = new SimpleObjectProperty<>();
        this.agentSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }
    public void setLoginAccountType(AccountType loginAccountType) {
        logger.debug("Login Account type changed={}", loginAccountType);
        this.loginAccountType = loginAccountType;
    }

    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }
    public ObjectProperty<OwnerMenuOptions> getOwnerSelectedMenuItem() {
        return ownerSelectedMenuItem;
    }
    public  ObjectProperty<AgentMenuOptions> getAgentSelectedMenuItem() {
        return agentSelectedMenuItem;
    }

    // Admin Views
    public AnchorPane getAdminDashboardView() {
        if(adminDashboardView == null) {
            try {
                adminDashboardView = new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminDashboard.fxml")).load();
            } catch (Exception e) {
                logger.error("Error getting admin dashboard view: {}", e.getMessage());
            }
        }
        return adminDashboardView;
    }
    public AnchorPane getCreateAgentView() {
        if (createAgentView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateAgent.fxml"));
                createAgentView = loader.load();
                CreateAgent controller = loader.getController();
                CreateAgentService service =
                        new CreateAgentServiceImpl(Model.getInstance());
                controller.setCreateAgentService(service);

            } catch (Exception e) {
                logger.error("Error creating agent view: {}", e.getMessage());
            }
        }
        return createAgentView;
    }
    public AnchorPane getCreateOwnerView() {
        if(createOwnerView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateOwner.fxml"));
                createOwnerView = loader.load();
                CreateOwner controller = loader.getController();
                CreateOwnerService service = new CreateOwnerServiceImpl(Model.getInstance());
                controller.setCreateOwnerService(service);
            } catch (Exception e) {
                logger.error("Error creating owner view: {}", e.getMessage());
            }
        }
        return  createOwnerView;
    }

    // Owner views
    public AnchorPane getCreateWarehouseView() {
        if(createWarehouseView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Owner/CreateWarehouse.fxml"));
                createWarehouseView = loader.load();
                CreateWarehouseController controller = loader.getController();
                // Loading current agents
                List<AgentEntity> agents = Model.getInstance().getDatabaseDriver().getAllAgents();
                List<AgentDTO> agentDTOs = new ArrayList<>();

                for(AgentEntity agent: agents) {
                    agentDTOs .add(new AgentDTO(agent.getFullName(), agent.getAgentId(), agent.getCommissionRate()));
                }
                ObservableList<AgentDTO> agentList = FXCollections.observableArrayList(agentDTOs );
                ObservableList<ClimaticConditions> climaticCons = FXCollections.observableArrayList(ClimaticConditions.AMBIENT, ClimaticConditions.COOLED, ClimaticConditions.HUMIDITY_CONTROLLED, ClimaticConditions.HEATED);

                CreateWarehouseService service = new CreateWarehouseServiceImpl(Model.getInstance());
                controller.setCreateWarehouseService(service);
                controller.setAgentList(agentList);
                controller.setClimaditicCons(climaticCons);

            // loading climatic conditions

            } catch (Exception e) {
                logger.error("Error creating warehouse view: {}", e.getMessage());
            }
        }
        return  createWarehouseView;
    }
    public AnchorPane getOwnerProfileView() {
        if(ownerProfileView == null) {
            try {
                ownerProfileView = new FXMLLoader(getClass().getResource("/Fxml/Owner/Profile.fxml")).load();
            } catch (Exception e) {
                logger.error("Error getting owner profile view: {}", e.getMessage());
            }
        }
        return ownerProfileView;
    }
    public AnchorPane getOwnerReportsView() {
        if(ownerReportsView == null) {
            try {
                ownerReportsView = new FXMLLoader(getClass().getResource("/Fxml/Owner/OwnerReports.fxml")).load();
            } catch (Exception e) {
                logger.error("Error getting owner reports view: {}", e.getMessage());
            }
        }
        return ownerReportsView;
    }
    public AnchorPane getOwnerNotificationsView() {
        if(ownerNotificationsView == null) {
            try {
                ownerNotificationsView = new FXMLLoader(getClass().getResource("/Fxml/Owner/OwnerNotification.fxml")).load();
            } catch (Exception e) {
                logger.error("Error getting owner notifications view: {}", e.getMessage());
            }
        }
        return ownerNotificationsView;
    }

    // Agent views
    public AnchorPane getCreateRentalWarehouseView() {
        if(createRentalWarehouseView == null) {
            try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Agent/CreateRentalWarehouse.fxml"));
                createRentalWarehouseView = loader.load();
                CreateRentalWarehouseController controller = loader.getController();

                AgentEntity currentAgent = Model.getInstance().getCurrentAgent();
                // Loading warehouses
                List<WarehouseEntity> warehouses = Model.getInstance().getDatabaseDriver().getWarehousesByAgentId(currentAgent.getAgentId());
                List<WarehouseDTO> warehouesesList = new ArrayList<>();
                for(WarehouseEntity warehouse: warehouses) {
                    warehouesesList.add(new WarehouseDTO(warehouse.getName(), warehouse.getAddress(), warehouse.getArea(), warehouse.getClimaticConditions(), warehouse.getId(), warehouse.getOwnerId()));
                }

                // Assign agents
                ObservableList<WarehouseDTO> warehouseList = FXCollections.observableArrayList(warehouesesList);

                CreateRentalWarehouseService service = new CreateRentalWarehouseServiceImpl(Model.getInstance());
                controller.setCreateRentalWarehouseService(service);
                controller.setWarehouseList(warehouseList);

            } catch (Exception e) {
                logger.error("Error creating rental warehouse view: {}", e.getMessage());
            }
        }
        return createRentalWarehouseView;
    }
    public AnchorPane getAgentProfileView() {
        if(agentProfileView == null) {
            try {
                agentProfileView = new FXMLLoader(getClass().getResource("/Fxml/Agent/Profile.fxml")).load();
            } catch (Exception e) {
                logger.error("Error getting agent profile dashboard view: {}", e.getMessage());
            }
        }
        return agentProfileView;
    }
    public AnchorPane getAgentReportsView() {
        if(agentReportsView == null) {
            try {
                agentReportsView = new FXMLLoader(getClass().getResource("/Fxml/Agent/AgentReports.fxml")).load();
            } catch (Exception e) {
                logger.error("Error getting agent reports view: {}", e.getMessage());
            }
        }
        return agentReportsView;
    }
    public AnchorPane getAgentNotificationsView() {
        if(agentNotificationsView == null) {
            try {
                agentNotificationsView = new FXMLLoader(getClass().getResource("/Fxml/Agent/AgentNotifications.fxml")).load();
            } catch (Exception e) {
                logger.error("Error getting agent notifications view: {}", e.getMessage());
            }
        }
        return agentNotificationsView;
    }

    public void showLoginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
            Scene scene = new Scene(loader.load());

            // Get controller created by JavaFX
            LoginController controller = loader.getController();

            // Create real service
            LoginService loginService =
                    new LoginServiceImpl(Model.getInstance());

            // Inject dependency
            controller.setLoginService(loginService);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Warehouse");
            stage.show();

        } catch (Exception e) {
            logger.error("Error showing login window: {}", e.getMessage());
        }
    }
    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }
    public void showOwnerWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Owner/Owner.fxml"));
        OwnerController ownerController = new OwnerController();
        loader.setController(ownerController);
        createStage(loader);
    }
    public void showAgentWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Agent/Agent.fxml"));
        AgentController agentController = new AgentController();
        loader.setController(agentController);
        createStage(loader);
    }
    public void showRentalWarehouseEditWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Agent/EditRentalWarehouse.fxml"));
        EditRentalWarehouseController editRentalWarehouseController = new EditRentalWarehouseController(Model.getInstance().getRentalWarehouseToEdit());
        loader.setController(editRentalWarehouseController);
        createStage(loader);
    }
    public void showWarehouseEditWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Owner/EditWarehouse.fxml"));
        EditWarehouseController editWarehouseController = new EditWarehouseController(Model.getInstance().getWarehouseToEdit());
        loader.setController(editWarehouseController);
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            logger.error("Error creating stage: {}", e.getMessage());
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Warehouse");
        stage.show();
    }
    public void closeStage(Stage stage) {
        stage.close();
    }
}
