package com.jmc.warehouse.Controllers.Owner;

import com.jmc.warehouse.Models.*;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Entities.OwnerEntity;
import com.jmc.warehouse.Models.Entities.WarehouseEntity;
import com.jmc.warehouse.Views.ClimaticConditions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    //public ListView<AgentDTO> assignAgent_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
        assignAgent_selector.setItems(agentList);
        assignAgent_selector.setValue(agentList.get(0));

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

    public void createWarehouse() {
        OwnerEntity currentOwner = Model.getInstance().getCurrentOwner();

        AgentDTO selectedAgentDTO = (AgentDTO) assignAgent_selector.getValue();

        // Get selected agents (multiple selection)
       // ObservableList<AgentDTO> selectedAgentDTOs =
          //      assignAgent_listview.getSelectionModel().getSelectedItems();

        // Get the full AgentEntity from database
        AgentEntity assignedAgent = Model.getInstance().getDatabaseDriver()
               .getAgentById(selectedAgentDTO.getAgentId());

        String name = warehouse_name.getText();
        String address = warehouse_address.getText();
        String dimensions = warehouse_dimensions.getText();
        String areaText = warehouse_area.getText();
        ClimaticConditions climaticCon = (ClimaticConditions) climaticCon_selector.getValue();

        error_lbl.setStyle("-fx-text-fill: red; -fx-font-size: 1.3em; -fx-font-weight: bold;");
        if(name.isEmpty() || address.isEmpty() || dimensions.isEmpty() ||
                areaText.isEmpty() || climaticCon == null) {
            error_lbl.setText("Please fill all required fields!");
            return;
        }

        double area;
        try {
            area = Double.parseDouble(areaText);
        } catch (NumberFormatException e) {
            error_lbl.setText("Area must be a valid number!");
            return;
        }
        // Create WarehouseEntity
        WarehouseEntity warehouse = new WarehouseEntity(name, address, dimensions, area, LocalDate.now(), currentOwner,climaticCon, assignedAgent);
        boolean success = Model.getInstance().getDatabaseDriver().createWarehouse(warehouse);

        // Add selected agents to warehouse
        /*
        Set<AgentEntity> selectedAgents = new HashSet<>();
        for (AgentDTO agentDTO : selectedAgentDTOs) {
            AgentEntity agent = Model.getInstance().getDatabaseDriver()
                    .getAgentById(agentDTO.getAgentId());
            if (agent != null) {
                selectedAgents.add(agent);
            }
        }
        warehouse.setAgents(selectedAgents);
*/
        if (success) {
            error_lbl.setText("Warehouse created successfully!");
            error_lbl.setStyle("-fx-text-fill: green;");
            clearFields();
        } else {
            error_lbl.setText("Error creating warehouse!");
            error_lbl.setStyle("-fx-text-fill: red;");
        }
        // Update owner's warehouse list
        Model.getInstance().setOwnerWarehouses(currentOwner);
    }

    public void clearFields() {
        warehouse_name.clear();
        warehouse_address.clear();
        warehouse_area.clear();
        warehouse_dimensions.clear();
        climaticCon_selector.setValue(ClimaticConditions.AMBIENT);
        //assignAgent_listview.getSelectionModel().clearSelection();
        //assignAgent_listview.getSelectionModel().selectFirst();
    }
}
