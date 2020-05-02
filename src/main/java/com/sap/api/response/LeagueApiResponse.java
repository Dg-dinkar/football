package com.sap.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueApiResponse implements Serializable {

    private List<LeagueResponse> response;
}
