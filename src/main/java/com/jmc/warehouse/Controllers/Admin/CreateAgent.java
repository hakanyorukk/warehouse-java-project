package com.jmc.warehouse.Controllers.Admin;

import com.jmc.warehouse.Models.Entities.AdminEntity;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateAgent implements Initializable {
    public TextField agent_username;
    public TextField agent_email;
    public TextField agent_password;
    public TextField agent_phone;
    public TextField agent_commision_rate;
    public Button create_agent_btn;
    public TextField agent_fullname;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_agent_btn.setOnAction(event-> createAgent());
    }

    private void createAgent() {
        AdminEntity currentAdmin = Model.getInstance().getCurrentAdmin();

        String fullName = agent_fullname.getText();
        String username = agent_username.getText();
        String email = agent_email.getText();
        String password = agent_password.getText();
        String phone = agent_phone.getText();
        Double commissionRate = Double.parseDouble(agent_commision_rate.getText());

        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            error_lbl.setText("Please fill all required fields!");
            return;
        }

        AgentEntity agent = new AgentEntity(fullName, username, email, password, phone, commissionRate, currentAdmin, LocalDate.now());
        boolean success = Model.getInstance().getDatabaseDriver().createAgent(agent);
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold;");
        if(success) {
            error_lbl.setText("Agent Created Successfully!");
            clearFields();
        } else {
            error_lbl.setText("There is a error creating agent!");
        }
    }

    private void clearFields() {
        agent_fullname.clear();
        agent_username.clear();
        agent_email.clear();
        agent_password.clear();
        agent_phone.clear();
        agent_commision_rate.clear();
    }
}
