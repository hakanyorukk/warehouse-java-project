package com.jmc.warehouse.Controllers.Owner;

import com.jmc.warehouse.Models.*;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Entities.OwnerEntity;
import com.jmc.warehouse.Models.Entities.WarehouseEntity;
import com.jmc.warehouse.Services.Owner.CreateWarehouseResult;
import com.jmc.warehouse.Services.Owner.CreateWarehouseService;
import com.jmc.warehouse.Views.ClimaticConditions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class CreateWarehouseController implements Initializable {
    public TextField warehouse_name;
    public TextField warehouse_address;
    public TextField warehouse_dimensions;
    public TextField warehouse_area;
    public Button warehouse_btn;
    public ChoiceBox climaticCon_selector;
    public ChoiceBox assignAgent_selector;
    public Label error_lbl;
    private static final Logger logger = LogManager.getLogger(CreateWarehouseController.class);
    //public ListView<AgentDTO> assignAgent_listview;

    private CreateWarehouseService createWarehouseService;
    private ObservableList<AgentDTO> agentList = FXCollections.observableArrayList();
    private ObservableList<ClimaticConditions> climaditcCons = FXCollections.observableArrayList();

    public void setCreateWarehouseService(CreateWarehouseService createWarehouseService) {
        this.createWarehouseService = createWarehouseService;
    }

    public void setAgentList(ObservableList<AgentDTO> agentList) {
        this.agentList = agentList;

        if(assignAgent_selector != null) {
            assignAgent_selector.setItems(agentList);
        }
    }

    public void setClimaditicCons(ObservableList<ClimaticConditions> climaditcCons) {
        this.climaditcCons = climaditcCons;
        if(climaticCon_selector != null) {
            climaticCon_selector.setItems(climaditcCons);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
/*
        climaticCon_selector.setItems(FXCollections.observableArrayList(ClimaticConditions.AMBIENT, ClimaticConditions.COOLED, ClimaticConditions.HUMIDITY_CONTROLLED, ClimaticConditions.HEATED));
        climaticCon_selector.setValue(ClimaticConditions.AMBIENT);

        // Loading agents
        List<AgentEntity> agents = Model.getInstance().getDatabaseDriver().getAllAgents();
        List<AgentDTO> agentDTOs  = new ArrayList<>();

        for(AgentEntity agent: agents) {
            agentDTOs .add(new AgentDTO(agent.getFullName(), agent.getAgentId(), agent.getCommissionRate()));
        }
        // Assign agents
        ObservableList<AgentDTO> agentList = FXCollections.observableArrayList(agentDTOs );

 */
        climaticCon_selector.setItems(climaditcCons);
        //climaticCon_selector.setValue(ClimaticConditions.AMBIENT);
        assignAgent_selector.setItems(agentList);
        //assignAgent_selector.setValue(agentList.get(0));

       warehouse_btn.setOnAction(event -> createWarehouse());

       /*
        if (agentDTOs.isEmpty()) {
            error_lbl.setText("No agents available. Please create agents first.");
            error_lbl.setStyle("-fx-text-fill: orange;");
            assignAgent_listview.setDisable(true);
            warehouse_btn.setDisable(true);
        } else {
            ObservableList<AgentDTO> agentList = FXCollections.observableArrayList(agentDTOs);
            assignAgent_listview.setItems(agentList);

            // Enable multiple selection
            assignAgent_listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            // Optionally select the first agent by default
            assignAgent_listview.getSelectionModel().selectFirst();
        }

        */
    }

    void createWarehouse() {
        logger.info("Create warehouse clicked");
        String name = warehouse_name.getText();
        String address = warehouse_address.getText();
        String dimensions = warehouse_dimensions.getText();
        String areaText = warehouse_area.getText();
        ClimaticConditions climaticCon = (ClimaticConditions) climaticCon_selector.getValue();
        AgentDTO selectedAgentDTO = (AgentDTO) assignAgent_selector.getValue();

        if(createWarehouseService == null) {
            logger.error("CreateWarehouseService was not injected");
            throw new IllegalStateException("CreateWarehouseService was not injected");
        }
        error_lbl.setStyle("-fx-text-fill: red; -fx-font-size: 1.3em; -fx-font-weight: bold;");

        if(name.isEmpty() || address.isEmpty() || dimensions.isEmpty() ||
                areaText.isEmpty() || climaticCon == null) {
            logger.warn("Creating Warehouse failed: Missing fields");
            error_lbl.setText("Please fill all required fields!");
            return;
        }

        double area;
        try {
            area = Double.parseDouble(areaText);
        } catch (NumberFormatException e) {
            logger.warn("Area is not a double");
            error_lbl.setText("Area must be a valid number!");
            return;
        }

        CreateWarehouseResult result = createWarehouseService.createWarehous(name, address, dimensions, area, climaticCon, selectedAgentDTO);
        logger.debug("Calling create warehouse name={}", name);
        if(result == CreateWarehouseResult.SUCCESS) {
            logger.info("Warehouse created successfully");
            error_lbl.setText("Warehouse created successfully!");
            error_lbl.setStyle("-fx-text-fill: green;");
            clearFields();
        } else {
            logger.error("Error creating warehouse");
            error_lbl.setText("Error creating warehouse!");
            error_lbl.setStyle("-fx-text-fill: red;");
        }

    }

    public void clearFields() {
        warehouse_name.clear();
        warehouse_address.clear();
        warehouse_area.clear();
        warehouse_dimensions.clear();
        //climaticCon_selector.setValue(ClimaticConditions.AMBIENT);
    }
}
