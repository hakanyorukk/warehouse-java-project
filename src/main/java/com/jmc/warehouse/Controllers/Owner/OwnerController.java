package com.jmc.warehouse.Controllers.Owner;

import com.jmc.warehouse.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OwnerController implements Initializable {

    public BorderPane owner_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getOwnerSelectedMenuItem().addListener((observable, oldVal, newVal)-> {
            switch(newVal) {
                case REPORTS -> owner_parent.setCenter(Model.getInstance().getViewFactory().getOwnerReportsView());
                case NOTIFICATIONS -> owner_parent.setCenter(Model.getInstance().getViewFactory().getOwnerNotificationsView());
                case CREATE_WAREHOUSE-> owner_parent.setCenter(Model.getInstance().getViewFactory().getCreateWarehouseView());
                default -> owner_parent.setCenter(Model.getInstance().getViewFactory().getOwnerProfileView());
            }
        });
    }
}
