package com.jmc.warehouse.Controllers.Admin;

import com.jmc.warehouse.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observable, oldVal, newVal)-> {
            switch(newVal) {
                case CREATE_WAREHOUSE_AGENT-> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateAgentView());
                case CREATE_WAREHOUSE_OWNER -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateOwnerView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminDashboardView());
            }
        });
    }
}
