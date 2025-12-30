package com.jmc.warehouse.Controllers.Owner;

import com.jmc.warehouse.Models.Entities.OwnerEntity;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Models.OwnerNotification;
import com.jmc.warehouse.Views.OwnerNotificationCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class OwnerNotificationsController implements Initializable {
    public ListView<OwnerNotification> owner_notifications_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initDate();
        owner_notifications_listview.setItems(Model.getInstance().getOwnerNotifications());
        owner_notifications_listview.setCellFactory(e-> new OwnerNotificationCellFactory());
    }

    private void initDate() {
        OwnerEntity currentOwner = Model.getInstance().getCurrentOwner();
        if(Model.getInstance().getOwnerNotifications().isEmpty()) {
            Model.getInstance().setOwnerNotifications(currentOwner);
        }
    }
}
