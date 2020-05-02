package com.sap.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.api.response.FootballApiResponse;
import com.sap.api.response.LeagueResponse;
import com.sap.api.response.Standings;
import com.sap.api.response.common.HttpConnectionClient;
import com.sap.model.Teams;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballService {
    private static HttpConnectionClient client = new HttpConnectionClient();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public FootballApiResponse getStandings(String countryName, String leagueName, String teamName) {
        LeagueResponse[] leagues = getLeagues();
        List<LeagueResponse> leagueList = Arrays.asList(leagues).stream()
                .filter(leagueResponse -> leagueName.equals(leagueResponse.getLeagueName()))
                .filter(leagueResponse -> countryName.equals(leagueResponse.getCountryName()))
                .collect(Collectors.toList());

        LeagueResponse leagueResponse = leagueList.size()!=0?leagueList.get(0):null;

        Standings[] standings = getStandingDetails(leagueResponse.getLeagueId());
        List<Standings> standingsList = Arrays.asList(standings).stream()
                .filter(standingsObj ->  teamName.equals(standingsObj.getTeamName()))
                .collect(Collectors.toList());

        Standings standing = standingsList.size()!=0?standingsList.get(0):null;

        FootballApiResponse footballApiResponse = new FootballApiResponse();
        if(leagueResponse!=null) {
            footballApiResponse.setCountryId(leagueResponse.getCountryId());
        }
        if(standing!=null) {
            footballApiResponse.setCountryName(standing.getCountryName());
            footballApiResponse.setLeagueId(standing.getLeagueId());
            footballApiResponse.setLeagueName(standing.getLeagueName());
            footballApiResponse.setTeamId(standing.getTeamId());
            footballApiResponse.setTeamName(standing.getTeamName());
            footballApiResponse.setOverallLeaguePosition(standing.getOverallLeaguePosition());
        }

        return footballApiResponse;
    }

    private Standings[] getStandingDetails(String leagueId) {
        Standings[] response = null;
        String url = "https://apiv2.apifootball.com/";
        StringBuilder params = new StringBuilder("?action=");
        params.append("get_standings")
                .append("&league_id=")
                .append(leagueId)
                .append("&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978");
        try {
            String responsePayload = client.sendGET(url, params.toString());
            response = objectMapper.readValue(responsePayload, Standings[].class);
        } catch (Exception ex) {
            System.out.println(""+ex);
        }
        return response;
    }

    private Teams[] getTeams(String leagueId) {
        Teams[] response = null;
        String url = "https://apiv2.apifootball.com/";
        StringBuilder params = new StringBuilder("?action=");
        params.append("get_teams")
                .append("&league_id=")
                .append(leagueId)
                .append("&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978");
        try {
            String responsePayload = client.sendGET(url, params.toString());
            response = objectMapper.readValue(responsePayload, Teams[].class);
        } catch (Exception ex) {
            System.out.println(""+ex);
        }
        return response;
    }

    private LeagueResponse[] getLeagues() {
        LeagueResponse[] response = null;
        String url = "https://apiv2.apifootball.com/";
        StringBuilder params = new StringBuilder("?action=");
        params.append("get_leagues");
        params.append("&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978");
        try {
            String responsePayload = client.sendGET(url, params.toString());
            response = objectMapper.readValue(responsePayload, LeagueResponse[].class);
        } catch (Exception ex) {
            System.out.println(""+ex);
        }
        return response;
    }

}
