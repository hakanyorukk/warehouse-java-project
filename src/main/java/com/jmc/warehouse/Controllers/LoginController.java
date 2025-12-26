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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public TextField username_fld;
    public TextField password_fld;
    public Label error_lbl;
    public ChoiceBox<AccountType> acc_selector;
    public Button login_btn;

    private LoginService loginService;

    private static final Logger logger = LogManager.getLogger(LoginController.class);

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
        logger.info("Login button clicked");
        if (loginService == null) {
            logger.error("LoginService was not injected");
            throw new IllegalStateException("LoginService was not injected");
        }
        LoginResult result = loginService.login(
                username_fld.getText(),
                password_fld.getText(),
                acc_selector.getValue()
        );
        logger.debug("Login result called username={}", username_fld.getText());

        if (result == LoginResult.SUCCESS) {
            logger.info("LoginResult success");
            navigate();
            closeLoginWindow();
        } else {
            logger.error("Invalid credentials");
            clearFields();
        }
    }

    private void navigate() {
        logger.info("Navigated to window");
        switch (acc_selector.getValue()) {
            case ADMIN -> Model.getInstance().getViewFactory().showAdminWindow();
            case WAREHOUSE_OWNER -> Model.getInstance().getViewFactory().showOwnerWindow();
            case WAREHOUSE_AGENT -> Model.getInstance().getViewFactory().showAgentWindow();
        }
    }

    private void closeLoginWindow() {
        // for accessing current stage
        logger.warn("Login window closed");
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    public void clearFields() {
        logger.warn("Login fields cleared");
        username_fld.clear();
        password_fld.clear();
        error_lbl.setText("No such login credentials!");
    }
}
