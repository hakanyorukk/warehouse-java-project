package com.jmc.warehouse.Controllers.Owner;

import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Views.OwnerMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class OwnerMenuController implements Initializable {
    public Button create_warehous_btn;
    public Button reports_btn;
    public Button notifications_btn;
    public Button profile_btn;
    public Button logout_btn;
    private static Logger logger = LogManager.getLogger(OwnerMenuController.class);

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
        logger.info("Warehouse button clicked");
        Model.getInstance().getViewFactory().getOwnerSelectedMenuItem().set(OwnerMenuOptions.CREATE_WAREHOUSE);
    }

    private void onReports() {
        logger.info("Reports button clicked");
        Model.getInstance().getViewFactory().getOwnerSelectedMenuItem().set(OwnerMenuOptions.REPORTS);
    }

    private void onNotifications() {
        logger.info("Notifications button clicked");
        Model.getInstance().getViewFactory().getOwnerSelectedMenuItem().set(OwnerMenuOptions.NOTIFICATIONS);
    }

    private void onProfile() {
        logger.info("Profile button clicked");
        Model.getInstance().getViewFactory().getOwnerSelectedMenuItem().set(OwnerMenuOptions.PROFILE);
    }

    private void onLogout() {
        logger.info("Logout button clicked");

        Stage stage = (Stage) create_warehous_btn.getScene().getWindow();

        logger.info("Current stage closed");
        Model.getInstance().getViewFactory().closeStage(stage);
        // Show Login Window
        logger.info("Login page opened");
        Model.getInstance().getViewFactory().showLoginWindow();
        // Set Owner Login Success Flag to False
        logger.info("Owner login flag setted to false");
        Model.getInstance().setOwnerLoginSuccessFlag(false);
    }
}
