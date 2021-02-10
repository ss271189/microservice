package com.ss.poc.microservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ss.poc.microservice.model.TeamsStanding;
import lombok.Data;

import java.util.Objects;


@Data
public class TeamStandingResponse {
    @JsonProperty("Country ID & Name")
    private String country;
    @JsonProperty("League ID & Name")
    private String league;
    @JsonProperty("Team ID & Name")
    private String team;
    @JsonProperty("overallPosition")
    private int overallPosition;


       public static TeamStandingResponse from(TeamsStanding teamStanding) {
           TeamStandingResponse response = new TeamStandingResponse();
        if (Objects.nonNull(teamStanding)) {
            response.setCountry("(" + teamStanding.getCountryId() + ") - " + teamStanding.getCountryName());
            response.setLeague("(" + teamStanding.getLeagueId() + ") - " + teamStanding.getLeagueName());
            response.setTeam("(" + teamStanding.getTeamId() + ") - " + teamStanding.getTeamName());
            response.setOverallPosition(teamStanding.getOverallPosition());
        }
        return response;

    }
}
