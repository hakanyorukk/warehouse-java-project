package com.jmc.warehouse.Models;

public class AgentDTO {
    private Integer agentId;
    private String fullName;
    private Double commisionRate;

    public AgentDTO(String fullName, Integer agentId, Double commisionRate) {
        this.agentId = agentId;
        this.fullName = fullName;
        this.commisionRate = commisionRate;
    }

    public Integer getAgentId() { return agentId; }
    public String getFullName() { return fullName; }
    public Double getCommisionRate() {return commisionRate; }

    @Override
    public String toString() {
        return fullName + ", Commission Rate: " + commisionRate + ", ID: " + agentId ;
    }
}