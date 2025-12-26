package com.jmc.warehouse.Controllers.Admin;

import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button create_owner_btn;
    public Button create_agent_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        create_agent_btn.setOnAction(event->onAgent());
        create_owner_btn.setOnAction(event->onOwner());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onAgent() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_WAREHOUSE_AGENT);
    }

    private void onOwner() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_WAREHOUSE_OWNER);
    }

    private void onLogout() {

        Stage stage = (Stage) create_owner_btn.getScene().getWindow();

        Model.getInstance().getViewFactory().closeStage(stage);

        Model.getInstance().getViewFactory().showLoginWindow();

        Model.getInstance().setAdminLoginSuccessFlag(false);
    }

}
