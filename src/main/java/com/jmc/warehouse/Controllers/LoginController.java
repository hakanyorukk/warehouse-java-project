package com.jmc.warehouse.Controllers;

import com.jmc.warehouse.Models.Model;
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
    public PasswordField password_fld;
    public Button login_btn;
    public Label error_lbl;
    public ChoiceBox<AccountType> acc_selector;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.ADMIN, AccountType.WAREHOUSE_AGENT, AccountType.WAREHOUSE_OWNER));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()) );
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        // for accessing current stage
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);

        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.ADMIN) {
            Model.getInstance().getViewFactory().showAdminWindow();
        }
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.WAREHOUSE_OWNER) {
            Model.getInstance().getViewFactory().showOwnerWindow();
        }
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.WAREHOUSE_AGENT) {
            Model.getInstance().getViewFactory().showAgentWindow();
        }

        //Model.getInstance().getViewFactory().showAdminWindow();
    }
}
