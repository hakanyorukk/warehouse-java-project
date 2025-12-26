package com.jmc.warehouse.Controllers.Admin;

import com.jmc.warehouse.Services.Admin.CreateAgentResult;
import com.jmc.warehouse.Services.Admin.CreateAgentService;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
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

    private static final Logger logger =
            LogManager.getLogger(CreateAgent.class);


    private CreateAgentService createAgentService;

    public void setCreateAgentService(CreateAgentService createAgentService) {
        this.createAgentService = createAgentService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_agent_btn.setOnAction(event-> createAgent());
    }

    void createAgent() {
        String fullName = agent_fullname.getText();
        String username = agent_username.getText();
        String email = agent_email.getText();
        String password = agent_password.getText();
        String phone = agent_phone.getText();

        logger.info("Create Agent button clicked");

        double commissionRate;
        try {
            commissionRate = Double.parseDouble(agent_commision_rate.getText());
        } catch (NumberFormatException e) {
            logger.warn("Invalid commission rate entered: {}",
                    agent_commision_rate.getText());
            error_lbl.setText("Commission rate must be a number");
            return;
        }
        if(createAgentService == null) {
            logger.error("CreateAgentService was not injected");
            throw new IllegalStateException("CreateAgentService was not injected");
        }
        logger.debug("Calling createAgent service with username={}", username);

        CreateAgentResult result = createAgentService.createAgent(
                fullName,
                username,
                email,
                password,
                phone,
                commissionRate
        );

        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold;");
        if(result == CreateAgentResult.MISSING_FIELDS) {
            logger.info("Agent creation failed: missing fields");
            error_lbl.setText("Please fill all required fields!");
            return;
        } else if (result == CreateAgentResult.SUCCESS) {
            logger.info("Agent created successfully: {}", username);
            error_lbl.setText("Agent Created Successfully!");
            clearFields();
        } else {
            logger.info("Agent creation failed due to system error for user={}", username);
            error_lbl.setText("There is an error creating agent!");
        }

        /*
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

         */
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
