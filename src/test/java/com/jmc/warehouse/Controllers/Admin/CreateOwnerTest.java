package com.jmc.warehouse.Controllers.Admin;

import com.jmc.warehouse.Services.Admin.CreateOwnerResult;
import com.jmc.warehouse.Services.Admin.CreateOwnerService;
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
class CreateOwnerTest {

    @Mock
    CreateOwnerService createOwnerService;

    CreateOwner controller;

    @BeforeAll
    static void initJavaFx() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        controller = new CreateOwner();
        controller.setCreateOwnerService(createOwnerService);

        controller.owner_fullname = new TextField();
        controller.owner_username = new TextField();
        controller.owner_email = new TextField();
        controller.owner_password = new TextField();
        controller.owner_phone = new TextField();
        controller.owner_taxid = new TextField();
        controller.error_lbl = new Label();
    }

    @Test
    void shouldShowErrorMessageWhenCreationFails() {
        controller.owner_fullname.setText("Hakan");
        controller.owner_username.setText("hakan");
        controller.owner_email.setText("hakan@email.com");
        controller.owner_password.setText("1234");
        controller.owner_phone.setText("123");
        controller.owner_taxid.setText("10");

        when(createOwnerService.createOwner(
                "Hakan", "hakan", "hakan@email.com", "1234", "123", "10"
        )).thenReturn(CreateOwnerResult.ERROR);

        controller.createOwner();

        assertEquals("There is an error creating owner!", controller.error_lbl.getText());
    }

    @Test
    void shouldClearFieldsAndSuccessMessageWhenCreatedSuccessfully() {
        controller.owner_fullname.setText("Hakan");
        controller.owner_username.setText("hakan");
        controller.owner_email.setText("hakan@email.com");
        controller.owner_password.setText("1234");
        controller.owner_phone.setText("123");
        controller.owner_taxid.setText("10");

        when(createOwnerService.createOwner(
                "Hakan", "hakan", "hakan@email.com", "1234", "123", "10"
        )).thenReturn(CreateOwnerResult.SUCCESS);

        controller.createOwner();

        assertEquals("", controller.owner_fullname.getText());
        assertEquals("", controller.owner_username.getText());
        assertEquals("", controller.owner_email.getText());
        assertEquals("", controller.owner_password.getText());
        assertEquals("", controller.owner_phone.getText());
        assertEquals("", controller.owner_taxid.getText());

        assertEquals("Owner Created Successfully!", controller.error_lbl.getText());
    }

}