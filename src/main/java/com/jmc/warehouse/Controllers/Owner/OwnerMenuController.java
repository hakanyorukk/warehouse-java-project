package com.jmc.warehouse.Controllers.Owner;

import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Views.OwnerMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class OwnerMenuController implements Initializable {
    public Button create_warehous_btn;
    public Button reports_btn;
    public Button notifications_btn;
    public Button profile_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { addListeners();}

    private void addListeners() {
        logout_btn.setOnAction(event -> onLogout());
        create_warehous_btn.setOnAction(event->onWarehouse());
        reports_btn.setOnAction(event->onReports());
        notifications_btn.setOnAction(event->onNotifications());
        profile_btn.setOnAction(event->onProfile());
    }

    private void onWarehouse() {
        Model.getInstance().getViewFactory().getOwnerSelectedMenuItem().set(OwnerMenuOptions.CREATE_WAREHOUSE);
    }

    private void onReports() {
        Model.getInstance().getViewFactory().getOwnerSelectedMenuItem().set(OwnerMenuOptions.REPORTS);
    }

    private void onNotifications() {
        Model.getInstance().getViewFactory().getOwnerSelectedMenuItem().set(OwnerMenuOptions.NOTIFICATIONS);
    }

    private void onProfile() {
        Model.getInstance().getViewFactory().getOwnerSelectedMenuItem().set(OwnerMenuOptions.PROFILE);
    }

    private void onLogout() {
        // Get Stage
        Stage stage = (Stage) create_warehous_btn.getScene().getWindow();
        // Close the Owner window
        Model.getInstance().getViewFactory().closeStage(stage);
        // Show Login Window
        Model.getInstance().getViewFactory().showLoginWindow();
        // Set Owner Login Success Flag to False
        Model.getInstance().setOwnerLoginSuccessFlag(false);
    }
}
