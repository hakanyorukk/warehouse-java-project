package com.jmc.warehouse.Models.Entities;

import com.jmc.warehouse.Views.ClimaticConditions;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "warehouses")
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "dimensions")
    private String dimensions;

    @Column(name = "area")
    private Double area;

    @Column(name = "climatic_conditions")
    @Enumerated(EnumType.STRING)  // Stores as String ("NORMAL", "COLD", "HOT")
    private ClimaticConditions climaticConditions;

    @Column(name = "dateCreated")
    private LocalDate dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id")
    private OwnerEntity ownerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", referencedColumnName = "agent_id")
    private AgentEntity agentId;

    /*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "warehouse_agents_assignment",
            joinColumns = @JoinColumn(name = "warehouse_id"),
            inverseJoinColumns = @JoinColumn(name = "agent_id")
    )
    private Set<AgentEntity> agents = new HashSet<>();
*/
    // Default constructor (required by JPA)
    public WarehouseEntity() {}

    // Constructor with all fields
    public WarehouseEntity(String name, String address,
                           String dimensions, Double area, LocalDate dateCreated, OwnerEntity ownerId, ClimaticConditions climaticConditions, AgentEntity agentId
                           ) {
        this.ownerId = ownerId;
        this.name = name;
        this.address = address;
        this.dimensions = dimensions;
        this.area = area;
        this.climaticConditions = climaticConditions;
        this.dateCreated = dateCreated;
        this.agentId = agentId;
    }

    // Getters and Setters


    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public OwnerEntity getOwnerId() {return ownerId;}
    public void setOwnerId(OwnerEntity ownerId) {this.ownerId = ownerId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getDimensions() {return dimensions;}
    public void setDimensions(String dimensions) {this.dimensions = dimensions;}

    public Double getArea() {return area;}
    public void setArea(Double area) {this.area = area;}

    public ClimaticConditions getClimaticConditions() {return climaticConditions;}
    public void setClimaticConditions(ClimaticConditions climaticConditions) {this.climaticConditions = climaticConditions;}

    public LocalDate getDateCreated() {return dateCreated;}
    public void setDateCreated(LocalDate dateCreated) {this.dateCreated = dateCreated;}

    public AgentEntity getAgent() { return agentId;}
    public void setAgent(AgentEntity agent) {this.agentId = agent;}

    /*
    // Methods for managing agents
    public Set<AgentEntity> getAgents() { return agents; }
    public void setAgents(Set<AgentEntity> agents) { this.agents = agents; }

    public void addAgent(AgentEntity agent) {
        this.agents.add(agent);
    }

    public void removeAgent(AgentEntity agent) {
        this.agents.remove(agent);
    }

    public void clearAgents() {
        this.agents.clear();
    }
*/

    @Override
    public String toString() {
        return getName();
    }
}