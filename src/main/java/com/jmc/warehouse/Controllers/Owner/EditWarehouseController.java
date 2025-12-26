package com.jmc.warehouse.Controllers.Owner;

import com.jmc.warehouse.Models.*;
import com.jmc.warehouse.Models.Entities.AgentEntity;
import com.jmc.warehouse.Models.Entities.OwnerEntity;
import com.jmc.warehouse.Models.Entities.WarehouseEntity;
import com.jmc.warehouse.Views.ClimaticConditions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditWarehouseController implements Initializable {
    public TextField warehouse_name;
    public TextField warehouse_address;
    public TextField warehouse_dimensions;
    public TextField warehouse_area;
    public ChoiceBox climaticCon_selector;
    public ChoiceBox assignAgent_selector;
    public Button warehouse_btn;
    public Label error_lbl;
    private final Warehouse warehouse;
    private static final Logger logger = LogManager.getLogger(EditWarehouseController.class);

    public EditWarehouseController(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warehouse_name.setText(warehouse.getName());
        warehouse_address.setText(warehouse.getAddress());
        warehouse_dimensions.setText(warehouse.getDimensions());
        warehouse_area.setText(String.valueOf(warehouse.getArea()));

        /*
        warehouse_name.textProperty().bind(warehouse.nameProperty());
        warehouse_address.textProperty().bind(warehouse.addressProperty());
        warehouse_dimensions.textProperty().bind(warehouse.dimensionsProperty());
        warehouse_area.textProperty().bind(warehouse.areaProperty().asString());
*/
        climaticCon_selector.setItems(FXCollections.observableArrayList(ClimaticConditions.AMBIENT, ClimaticConditions.COOLED, ClimaticConditions.HUMIDITY_CONTROLLED, ClimaticConditions.HEATED));
        climaticCon_selector.setValue(warehouse.getClimaticCon());

        // Loading agents
        List<AgentEntity> agents = Model.getInstance().getDatabaseDriver().getAllAgents();
        List<AgentDTO> agentDTOs  = new ArrayList<>();

        // Get the current agent ID
        Integer currentAgentId = (warehouse.getAgent() != null)
                ? warehouse.getAgent().getAgentId()
                : null;

        for(AgentEntity agent : agents) {
            AgentDTO dto = new AgentDTO(agent.getFullName(), agent.getAgentId(), agent.getCommissionRate());

            // If warehouse has an agent and this is that agent, add it at the beginning
            if(currentAgentId != null && agent.getAgentId() == currentAgentId) {
                agentDTOs.add(0, dto);
            } else {
                agentDTOs.add(dto);
            }
        }

        ObservableList<AgentDTO> agentList = FXCollections.observableArrayList(agentDTOs);

        if (!agentList.isEmpty()) {
            assignAgent_selector.setItems(agentList);
            // Set the first agent as default (will be current agent if it exists)
            assignAgent_selector.setValue(agentList.get(0));
        }

        warehouse_btn.setOnAction(event -> onEditWarehouse());

    }

    public void onEditWarehouse() {
        logger.info("Edit Warehouse Button clicked");
        OwnerEntity currentOwner = Model.getInstance().getCurrentOwner();
        String name = warehouse_name.getText();
        String address = warehouse_address.getText();
        String dimensions = warehouse_dimensions.getText();
        String areaStr = warehouse_area.getText();

        AgentDTO selectedAgentDTO = (AgentDTO) assignAgent_selector.getValue();
        AgentEntity assignedAgent = Model.getInstance().getDatabaseDriver()
                .getAgentById(selectedAgentDTO.getAgentId());



        ClimaticConditions climaticCon = (ClimaticConditions) climaticCon_selector.getValue();
        double area;
        try {
            area = Double.parseDouble(areaStr);
        } catch (NumberFormatException e) {
            logger.warn("Area is not a double");
            error_lbl.setText("Invalid area value.");
            return;
        }
        //WarehouseEntity warehouse = new WarehouseEntity(name, address, dimensions, area, LocalDate.now(), currentOwner, climaticCon, assignedAgent);
        boolean success = Model.getInstance().getDatabaseDriver().updateWarehouse(this.warehouse.getId(), name, address, dimensions, area, climaticCon, assignedAgent);
        logger.debug("Calling Edit warehouse service name={}", name);
        if (success) {
            logger.info("Warehouse edited successfully");
            error_lbl.setText("Warehouse edited successfully!");
            error_lbl.setStyle("-fx-text-fill: green;");
        } else {
            logger.error("Error editing warehouse!");
            error_lbl.setText("Error editing warehouse!");
            error_lbl.setStyle("-fx-text-fill: red;");
        }

        Model.getInstance().setOwnerWarehouses(currentOwner);

    }

}
