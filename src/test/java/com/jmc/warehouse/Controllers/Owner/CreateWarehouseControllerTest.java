package com.jmc.warehouse.Controllers.Owner;

import com.jmc.warehouse.Services.Owner.CreateWarehouseResult;
import com.jmc.warehouse.Services.Owner.CreateWarehouseService;
import com.jmc.warehouse.Views.ClimaticConditions;
import javafx.application.Platform;
import javafx.scene.control.ChoiceBox;
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
class CreateWarehouseControllerTest {
    @Mock
    CreateWarehouseService createWarehouseService;

    CreateWarehouseController controller;

    @BeforeAll
    static void initJavaFx() {
        Platform.startup(() ->{});
    }

    @BeforeEach
    void setup() {
        controller = new CreateWarehouseController();
        controller.setCreateWarehouseService(createWarehouseService);

        controller.warehouse_name = new TextField();
        controller.warehouse_address = new TextField();
        controller.warehouse_dimensions = new TextField();
        controller.warehouse_area = new TextField();
        controller.climaticCon_selector = new ChoiceBox<>();
        controller.assignAgent_selector = new ChoiceBox<>();
        controller.error_lbl = new Label();
    }

    @Test
    void shouldShowErrorWhenFieldMissing() {

        controller.createWarehouse();

        assertEquals("Please fill all required fields!", controller.error_lbl.getText());
    }

    @Test
    void shouldShowSuccessMessageAndClearFieldsWhenCreatedSuccessfully() {

        controller.warehouse_name.setText("Warehouse A");
        controller.warehouse_address.setText("address");
        controller.warehouse_dimensions.setText("10 10");
        controller.warehouse_area.setText("100");
        controller.climaticCon_selector.setValue(ClimaticConditions.AMBIENT);
        controller.assignAgent_selector.setValue(null);

        when(createWarehouseService.createWarehous(
                "Warehouse A",
                "address",
                "10 10",
                100.0,
                ClimaticConditions.AMBIENT,
                null
        )).thenReturn(CreateWarehouseResult.SUCCESS);

        controller.createWarehouse();

        assertEquals(
                "Warehouse created successfully!",
                controller.error_lbl.getText()
        );

        // Assert — fields cleared
        assertEquals("", controller.warehouse_name.getText());
        assertEquals("", controller.warehouse_address.getText());
        assertEquals("", controller.warehouse_dimensions.getText());
        assertEquals("", controller.warehouse_area.getText());
    }


}