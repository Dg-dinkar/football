package com.sap.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Teams implements Serializable {

    @JsonProperty("team_key")
    private String teamKey;

    @JsonProperty("team_name")
    private String teamName;

    public String getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "Teams{" +
                "teamKey='" + teamKey + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
