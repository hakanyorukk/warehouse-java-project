package com.jmc.warehouse.Views;

import com.jmc.warehouse.Controllers.Admin.AdminController;
import com.jmc.warehouse.Controllers.Agent.AgentController;
import com.jmc.warehouse.Controllers.Agent.EditRentalWarehouseController;
import com.jmc.warehouse.Controllers.LoginController;
import com.jmc.warehouse.Controllers.Owner.EditWarehouseController;
import com.jmc.warehouse.Controllers.Owner.OwnerController;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Services.LoginService;
import com.jmc.warehouse.Services.LoginServiceImpl;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
                e.printStackTrace();
            }
        }
        return adminDashboardView;
    }
    public AnchorPane getCreateAgentView() {
        if(createAgentView == null) {
            try {
                createAgentView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateAgent.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  createAgentView;
    }
    public AnchorPane getCreateOwnerView() {
        if(createOwnerView == null) {
            try {
                createOwnerView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateOwner.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  createOwnerView;
    }

    // Owner views
    public AnchorPane getCreateWarehouseView() {
        if(createWarehouseView == null) {
            try {
                createWarehouseView = new FXMLLoader(getClass().getResource("/Fxml/Owner/CreateWarehouse.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  createWarehouseView;
    }
    public AnchorPane getOwnerProfileView() {
        if(ownerProfileView == null) {
            try {
                ownerProfileView = new FXMLLoader(getClass().getResource("/Fxml/Owner/Profile.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ownerProfileView;
    }
    public AnchorPane getOwnerReportsView() {
        if(ownerReportsView == null) {
            try {
                ownerReportsView = new FXMLLoader(getClass().getResource("/Fxml/Owner/OwnerReports.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ownerReportsView;
    }
    public AnchorPane getOwnerNotificationsView() {
        if(ownerNotificationsView == null) {
            try {
                ownerNotificationsView = new FXMLLoader(getClass().getResource("/Fxml/Owner/OwnerNotification.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ownerNotificationsView;
    }

    // Agent views
    public AnchorPane getCreateRentalWarehouseView() {
        if(createRentalWarehouseView == null) {
            try {
                createRentalWarehouseView = new FXMLLoader(getClass().getResource("/Fxml/Agent/CreateRentalWarehouse.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return createRentalWarehouseView;
    }
    public AnchorPane getAgentProfileView() {
        if(agentProfileView == null) {
            try {
                agentProfileView = new FXMLLoader(getClass().getResource("/Fxml/Agent/Profile.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return agentProfileView;
    }
    public AnchorPane getAgentReportsView() {
        if(agentReportsView == null) {
            try {
                agentReportsView = new FXMLLoader(getClass().getResource("/Fxml/Agent/AgentReports.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return agentReportsView;
    }
    public AnchorPane getAgentNotificationsView() {
        if(agentNotificationsView == null) {
            try {
                agentNotificationsView = new FXMLLoader(getClass().getResource("/Fxml/Agent/AgentNotifications.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return agentNotificationsView;
    }

    public void showLoginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
            Scene scene = new Scene(loader.load());

            // ✅ Get controller created by JavaFX
            LoginController controller = loader.getController();

            // ✅ Create real service
            LoginService loginService =
                    new LoginServiceImpl(Model.getInstance());

            // ✅ Inject dependency
            controller.setLoginService(loginService);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Warehouse");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
