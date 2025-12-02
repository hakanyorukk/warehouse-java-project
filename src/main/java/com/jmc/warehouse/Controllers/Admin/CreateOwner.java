package com.jmc.warehouse.Controllers.Admin;

import com.jmc.warehouse.Models.Entities.AdminEntity;
import com.jmc.warehouse.Models.Model;
import com.jmc.warehouse.Models.Entities.OwnerEntity;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateOwner implements Initializable {
    public TextField owner_username;
    public TextField owner_email;
    public TextField owner_password;
    public TextField owner_phone;
    public TextField owner_taxid;
    public Button create_owner_btn;
    public TextField owner_fullname;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_owner_btn.setOnAction(event -> createOwner());
    }

    private void createOwner() {
        AdminEntity currentAdmin = Model.getInstance().getCurrentAdmin();

        String fullName = owner_fullname.getText();
        String username = owner_username.getText();
        String email = owner_email.getText();
        String password = owner_password.getText();
        String phone = owner_phone.getText();
        String taxId = owner_taxid.getText();

        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            error_lbl.setText("Please fill all required fields!");
            return;
        }
        OwnerEntity owner = new OwnerEntity(fullName, username, email, password, phone, taxId,currentAdmin, LocalDate.now());
        boolean success = Model.getInstance().getDatabaseDriver().createOwner(owner);
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold;");
        if(success) {
            error_lbl.setText("Owner Created Successfully!");
            clearFields();
        } else {
            error_lbl.setText("There is a error creating owner!");
        }
    }

    private void clearFields() {
        owner_fullname.clear();
        owner_username.clear();
        owner_email.clear();
        owner_password.clear();
        owner_phone.clear();
        owner_taxid.clear();
    }
}
