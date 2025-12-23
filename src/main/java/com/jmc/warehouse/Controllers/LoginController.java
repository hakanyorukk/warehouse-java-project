package com.jmc.warehouse.Controllers;

import com.jmc.warehouse.Models.Entities.AdminEntity;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Models.Entities.OwnerEntity;
import com.jmc.warehouse.Services.LoginResult;
import com.jmc.warehouse.Services.LoginService;
import com.jmc.warehouse.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public TextField username_fld;
    public TextField password_fld;
    public Label error_lbl;
    public ChoiceBox<AccountType> acc_selector;
    public Button login_btn;

    private LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.values()));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(e -> onLogin());
    }

    void onLogin() {
        // for accessing current stage

        if (loginService == null) {
            throw new IllegalStateException("LoginService was not injected");
        }
        LoginResult result = loginService.login(
                username_fld.getText(),
                password_fld.getText(),
                acc_selector.getValue()
        );

        if (result == LoginResult.SUCCESS) {
            navigate();
            closeLoginWindow();
        } else {
            clearFields();
        }
    }

    private void navigate() {
        switch (acc_selector.getValue()) {
            case ADMIN -> Model.getInstance().getViewFactory().showAdminWindow();
            case WAREHOUSE_OWNER -> Model.getInstance().getViewFactory().showOwnerWindow();
            case WAREHOUSE_AGENT -> Model.getInstance().getViewFactory().showAgentWindow();
        }
    }

    private void closeLoginWindow() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    public void clearFields() {
        username_fld.clear();
        password_fld.clear();
        error_lbl.setText("No such login credentials!");
    }
}
