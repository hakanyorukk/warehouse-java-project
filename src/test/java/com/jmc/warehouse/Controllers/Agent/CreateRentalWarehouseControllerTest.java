package com.jmc.warehouse.Controllers.Agent;

import com.jmc.warehouse.Models.WarehouseDTO;
import com.jmc.warehouse.Services.Agent.CreateRentalWarehouseResult;
import com.jmc.warehouse.Services.Agent.CreateRentalWarehouseService;
import com.jmc.warehouse.Views.ClimaticConditions;
import javafx.application.Platform;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateRentalWarehouseControllerTest {
    @Mock
    CreateRentalWarehouseService createRentalWarehouseService;

    CreateRentalWarehouseController controller;

    @BeforeAll
    static void initJavaFx() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        controller = new CreateRentalWarehouseController();
        controller.setCreateRentalWarehouseService(createRentalWarehouseService);


        controller.warehouse_selector = new ChoiceBox<>();
        controller.tenant_name_fld = new TextField();
        controller.monthly_price_fld = new TextField();
        controller.start_date_picker = new DatePicker();
        controller.end_date_picker = new DatePicker();
        controller.error_lbl = new Label();

    }

    @Test
    void shouldShowErrorWhenFieldsMissing() {

        when(createRentalWarehouseService.createRentalForm(
                null, "", "", null, null
        )).thenReturn(CreateRentalWarehouseResult.MISSING_FIELDS);

        controller.createRentalForm();

        assertEquals(
                "Please fill all required fields!",
                controller.error_lbl.getText()
        );
    }

    @Test
    void shouldClearFieldsAndShowSuccessMessage() {

        WarehouseDTO warehouse =
                new WarehouseDTO("WH", "Addr", 100.0, ClimaticConditions.AMBIENT, 1, null);

        controller.warehouse_selector.getItems().add(warehouse);
        controller.warehouse_selector.setValue(warehouse);

        controller.tenant_name_fld.setText("Tenant A");
        controller.monthly_price_fld.setText("500");
        controller.start_date_picker.setValue(LocalDate.now());
        controller.end_date_picker.setValue(LocalDate.now().plusDays(30));

        when(createRentalWarehouseService.createRentalForm(
                warehouse,
                "Tenant A",
                "500",
                controller.start_date_picker.getValue(),
                controller.end_date_picker.getValue()
        )).thenReturn(CreateRentalWarehouseResult.SUCCESS);

        controller.createRentalForm();

        assertEquals("Warehouse rented successfully!", controller.error_lbl.getText());
        assertEquals("", controller.tenant_name_fld.getText());
        assertEquals("", controller.monthly_price_fld.getText());
        assertNull(controller.start_date_picker.getValue());
        assertNull(controller.end_date_picker.getValue());
    }



}