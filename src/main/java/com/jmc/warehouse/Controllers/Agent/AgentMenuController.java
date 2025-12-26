package com.jmc.warehouse.Controllers.Agent;

import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Views.AdminMenuOptions;
import com.jmc.warehouse.Views.AgentMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgentMenuController implements Initializable {
    public Button create_form_btn;
    public Button reports_btn;
    public Button notifications_btn;
    public Button profile_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { addListeners(); }

    private void addListeners() {
        create_form_btn.setOnAction(event->onCreateForm());
        reports_btn.setOnAction(event ->onReport());
        notifications_btn.setOnAction(event ->onNotification());
        profile_btn.setOnAction(event->onProfile());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onCreateForm() {
        Model.getInstance().getViewFactory().getAgentSelectedMenuItem().set(AgentMenuOptions.CREATE_RENTAL_FORM);
    }
    private void onReport() {
        Model.getInstance().getViewFactory().getAgentSelectedMenuItem().set(AgentMenuOptions.REPORTS);
    }
    private void onNotification() {
        Model.getInstance().getViewFactory().getAgentSelectedMenuItem().set(AgentMenuOptions.NOTIFICATIONS);
    }
    private void onProfile() {
        Model.getInstance().getViewFactory().getAgentSelectedMenuItem().set(AgentMenuOptions.PROFILE);
    }

    private void onLogout() {
        Stage stage = (Stage) create_form_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().setAgentLoginSuccessFlag(false);
    }

}
