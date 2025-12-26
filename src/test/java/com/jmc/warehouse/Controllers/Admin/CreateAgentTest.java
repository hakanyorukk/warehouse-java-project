package com.jmc.warehouse.Controllers.Admin;

import com.jmc.warehouse.Services.Admin.CreateAgentResult;
import com.jmc.warehouse.Services.Admin.CreateAgentService;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAgentTest {

    @Mock
    CreateAgentService createAgentService;

    CreateAgent controller;

    @BeforeAll
    static void initJavaFx() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        controller = new CreateAgent();
        controller.setCreateAgentService(createAgentService);

        controller.agent_fullname = new TextField();
        controller.agent_username = new TextField();
        controller.agent_email = new TextField();
        controller.agent_password = new TextField();
        controller.agent_phone = new TextField();
        controller.agent_commision_rate = new TextField();
        controller.error_lbl = new Label();

    }

    @Test
    void shouldShowErrorMessageWhenCreationFails() {
        controller.agent_fullname.setText("Hakan");
        controller.agent_username.setText("hakan");
        controller.agent_email.setText("hakan@mail.com");
        controller.agent_password.setText("1234");
        controller.agent_phone.setText("123");
        controller.agent_commision_rate.setText("10");

        when(createAgentService.createAgent(
                "Hakan", "hakan", "hakan@mail.com", "1234", "123", 10.0
        )).thenReturn(CreateAgentResult.ERROR);

        controller.createAgent();

        assertEquals(
                "There is an error creating agent!",
                controller.error_lbl.getText()
        );
    }

    @Test
    void shouldClearFieldsWhenAgentCreatedSuccessfully() {
        controller.agent_fullname.setText("Hakan");
        controller.agent_username.setText("hakan");
        controller.agent_email.setText("hakan@mail.com");
        controller.agent_password.setText("1234");
        controller.agent_phone.setText("123");
        controller.agent_commision_rate.setText("10");

        when(createAgentService.createAgent(
                "Hakan", "hakan", "hakan@mail.com", "1234", "123", 10.0
        )).thenReturn(CreateAgentResult.SUCCESS);

        controller.createAgent();

        assertEquals("", controller.agent_fullname.getText());
        assertEquals("", controller.agent_username.getText());
        assertEquals("", controller.agent_email.getText());
        assertEquals("", controller.agent_password.getText());
        assertEquals("", controller.agent_phone.getText());
        assertEquals("", controller.agent_commision_rate.getText());

        assertEquals("Agent Created Successfully!", controller.error_lbl.getText());
    }

    @Test
    void shouldShowErrorWhenCommissionRateIsNotNumber() {
        controller.agent_commision_rate.setText("abc");

        controller.createAgent();

        assertEquals(
                "Commission rate must be a number",
                controller.error_lbl.getText()
        );
    }

}