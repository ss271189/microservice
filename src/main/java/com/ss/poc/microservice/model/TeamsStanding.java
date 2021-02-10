package com.ss.poc.microservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TeamsStanding {
    private int countryId;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("league_name")
    private String leagueName;
    @JsonProperty("league_id")
    private int leagueId;
    @JsonProperty("team_name")
    private String teamName;
    @JsonProperty("team_id")
    private int teamId;
    @JsonProperty("overall_league_position")
    private int overallPosition;
}

