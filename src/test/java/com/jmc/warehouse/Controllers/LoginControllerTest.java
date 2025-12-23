package com.jmc.warehouse.Controllers;

import com.jmc.warehouse.Services.LoginResult;
import com.jmc.warehouse.Services.LoginService;
import com.jmc.warehouse.Views.AccountType;
import javafx.application.Platform;
import javafx.scene.control.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    LoginService loginService;

    LoginController controller;

    @BeforeAll
    static void initJavaFx() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        controller = new LoginController();
        controller.setLoginService(loginService);

        controller.username_fld = new TextField();
        controller.password_fld = new TextField();
        controller.error_lbl = new Label();
        controller.acc_selector = new ChoiceBox<>();

        controller.acc_selector.getItems().addAll(AccountType.values());
        controller.acc_selector.setValue(AccountType.ADMIN);
    }

    @Test
    void shouldClearFieldsWhenLoginFails() {
        when(loginService.login("bad", "bad", AccountType.ADMIN))
                .thenReturn(LoginResult.INVALID_CREDENTIALS);

        controller.username_fld.setText("bad");
        controller.password_fld.setText("bad");

        controller.onLogin();

        assertEquals("", controller.username_fld.getText());
        assertEquals("", controller.password_fld.getText());
        assertEquals("No such login credentials!", controller.error_lbl.getText());
    }
}
