package com.jmc.warehouse.Controllers;

import com.jmc.warehouse.Models.Entities.AdminEntity;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Models.Entities.OwnerEntity;
import com.jmc.warehouse.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label login_lbl;
    public Label username_lbl;
    public TextField username_fld;
    public Label password_lbl;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;
    public ChoiceBox<AccountType> acc_selector;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.ADMIN, AccountType.WAREHOUSE_AGENT, AccountType.WAREHOUSE_OWNER));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        // for accessing current stage
        Stage stage = (Stage) error_lbl.getScene().getWindow();

        // Admin Login
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.ADMIN) {
           // Model.getInstance().getViewFactory().showAdminWindow();
            Model.getInstance().evaluateAdminCred(username_fld.getText(), password_fld.getText());
            AdminEntity admin = Model.getInstance().getDatabaseDriver()
                    .getAdminData(username_fld.getText(), password_fld.getText());
            if(Model.getInstance().getAdminLoginSuccessFlag()) {
                Model.getInstance().setCurrentAdmin(admin);
                Model.getInstance().getViewFactory().showAdminWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                username_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No Such Login Credentials.");
            }
        }

        // Owner Login
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.WAREHOUSE_OWNER) {
            // Evaluate Owner Login Credentials
            Model.getInstance().evaluateOwnerCred(username_fld.getText(), password_fld.getText());
            OwnerEntity owner = Model.getInstance().getDatabaseDriver().getOwnerData(username_fld.getText(), password_fld.getText());
            if(Model.getInstance().getOwnerLoginSuccessFlag()) {
                Model.getInstance().setCurrentOwner(owner);
                Model.getInstance().getViewFactory().showOwnerWindow();
                // Close the login stage
               Model.getInstance().getViewFactory().closeStage(stage);

                // Set Owner Warehouses
                OwnerEntity currentOwner = Model.getInstance().getCurrentOwner();
                Model.getInstance().setOwnerWarehouses(currentOwner);
            } else {
                username_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No Such Login Credentials.");
            }

        }

        // Agent Login
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.WAREHOUSE_AGENT) {
            Model.getInstance().evaluateAgentCred(username_fld.getText(), password_fld.getText());
            AgentEntity agent = Model.getInstance().getDatabaseDriver().getAgentData(username_fld.getText(), password_fld.getText());
            if(Model.getInstance().getAgentLoginSuccessFlag()) {
                Model.getInstance().setCurrentAgent(agent);
                Model.getInstance().getViewFactory().showAgentWindow();

                Model.getInstance().getViewFactory().closeStage(stage);

                // Set Agent Rental Warehouses
                AgentEntity currentAgent = Model.getInstance().getCurrentAgent();
                Model.getInstance().setAgentRentalWarehouses(currentAgent);
            } else {
                username_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No Such Login Credentials.");
            }
        }

        //Model.getInstance().getViewFactory().showAdminWindow();
    }
}
