package com.sap.api.response;

import java.io.Serializable;

public class FootballApiResponse implements Serializable {

    private String countryName;
    private String countryId;
    private String leagueName;
    private String leagueId;
    private String teamName;
    private String teamId;
    private String overallLeaguePosition;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getOverallLeaguePosition() {
        return overallLeaguePosition;
    }

    public void setOverallLeaguePosition(String overallLeaguePosition) {
        this.overallLeaguePosition = overallLeaguePosition;
    }

    @Override
    public String toString() {
        return "FootballApiResponse{" +
                "countryName='" + countryName + '\'' +
                ", countryId='" + countryId + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", leagueId='" + leagueId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", teamId='" + teamId + '\'' +
                ", overallLeaguePosition='" + overallLeaguePosition + '\'' +
                '}';
    }
}
