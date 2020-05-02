package com.sap.controller;

import com.sap.api.response.FootballApiResponse;
import com.sap.service.FootballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private FootballService footballService;
    @GetMapping(path="/findStandings")
    public FootballApiResponse addNewLoad(@RequestParam String countryName, @RequestParam String teamName, @RequestParam String leagueName) {
        FootballApiResponse response = footballService.getStandings(countryName, leagueName, teamName);
        return response;
    }
}
