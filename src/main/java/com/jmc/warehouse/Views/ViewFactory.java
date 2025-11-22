package com.jmc.warehouse.Views;

import com.jmc.warehouse.Controllers.Admin.AdminController;
import com.jmc.warehouse.Controllers.Agent.AgentController;
import com.jmc.warehouse.Controllers.Owner.OwnerController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private AccountType loginAccountType;

    // Admin views
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane adminDashboardView;

    // Owner views
    private final ObjectProperty<OwnerMenuOptions> ownerSelectedMenuItem;
    private AnchorPane createOwnerView;

    // Agent Views
    private final ObjectProperty<AgentMenuOptions> agentSelectedMenuItem;
    private AnchorPane createAgentView;

    public ViewFactory() {
        this.loginAccountType = AccountType.ADMIN; // default value
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
        this.ownerSelectedMenuItem = new SimpleObjectProperty<>();
        this.agentSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
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

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }
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

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
        Scene scene = null;
        // in case of the file that looking for does not exist
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
