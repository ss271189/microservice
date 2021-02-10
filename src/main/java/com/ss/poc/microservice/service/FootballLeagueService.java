package com.ss.poc.microservice.service;


import com.ss.poc.microservice.client.FootballLeagueClient;
import com.ss.poc.microservice.entity.TeamStandingRequest;
import com.ss.poc.microservice.entity.TeamStandingResponse;
import com.ss.poc.microservice.exception.InvalidRequestParameter;
import com.ss.poc.microservice.model.Country;
import com.ss.poc.microservice.model.League;
import com.ss.poc.microservice.model.TeamsStanding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class FootballLeagueService {


    @Autowired
    FootballLeagueClient footballLeagueClient;


    public TeamStandingResponse getTeamStandings(TeamStandingRequest teamStandingRequest) {
        Country[] countries = footballLeagueClient.getCountriesDetails();
        int countryId = findCountryIdByName(countries, teamStandingRequest.getCountryName());
        League[] leagues = footballLeagueClient.getLeagueDetails(countryId);
        int leagueId = findLeagueIdByLeagueName(leagues, teamStandingRequest.getLeagueName());
        TeamsStanding[] teamsStandings = footballLeagueClient.getTeamsStanding(leagueId);
        TeamsStanding teamsStanding = findTeamStandingByTeamName(teamsStandings, teamStandingRequest.getTeamName());
        teamsStanding.setCountryId(countryId);
        return TeamStandingResponse.from(teamsStanding);

    }

    private int findCountryIdByName(Country[] countries, String countryName) {
        Optional<Country> optional = Stream.of(countries).filter(country -> countryName.equals(country.getCountryName())).findFirst();
        if (optional.isPresent()) {
            return optional.get().getCountryId();
        }
        throw new InvalidRequestParameter("Invalid Country Name");
    }

    private int findLeagueIdByLeagueName(League[] leagues, String leagueName) {
        Optional<League> optional = Stream.of(leagues).filter(league -> leagueName.equals(league.getLeagueName())).findFirst();
        if (optional.isPresent()) {
            return optional.get().getLeagueId();
        }
        throw new InvalidRequestParameter("Invalid League Name");
    }


    private TeamsStanding findTeamStandingByTeamName(TeamsStanding[] teamsStandings, String teamName) {
        Optional<TeamsStanding> optional = Stream.of(teamsStandings).filter(teamsStanding -> teamName.equals(teamsStanding.getTeamName())).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new InvalidRequestParameter("Invalid Team Name");
    }

}
