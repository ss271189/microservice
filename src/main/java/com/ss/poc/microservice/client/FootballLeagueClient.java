package com.ss.poc.microservice.client;


import com.ss.poc.microservice.model.Country;
import com.ss.poc.microservice.model.League;
import com.ss.poc.microservice.model.TeamsStanding;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class FootballLeagueClient {

    private static final String APIKEY = "APIkey";
    private static final String ACTION = "action";
    private static final String COUNTRY_ID = "country_id";
    private static final String LEAGUE_ID = "league_id";

    @Value("${football.api.url}")
    private String apiUrl;

    @Value("${football.api.key}")
    private String apiKey;

    @Value("#{${football.api.action}}")
    private Map<String, String> actionMap;

    @Autowired
    RestTemplate restTemplate;


    @Retry(name = "football-client")
    public Country[] getCountriesDetails()  {
        log.debug(" Execution started : getCountriesDetails");
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(ACTION, actionMap.get("getCountry"));
        queryParams.put(APIKEY, apiKey);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl);
        queryParams.forEach(builder::queryParam);
        Country[] countries=restTemplate.getForEntity(builder.toUriString(), Country[].class).getBody();
        log.debug(" Execution finished  : getCountriesDetails");
        return countries;
    }

    @Retry(name = "football-client")
    public League[] getLeagueDetails(int countryId) {
        log.debug(" Execution started : getCountriesDetails");
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(ACTION, actionMap.get("getLeagues"));
        queryParams.put(COUNTRY_ID, String.valueOf(countryId));
        queryParams.put(APIKEY, apiKey);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl);
        queryParams.forEach(builder::queryParam);
        League[] leagues=restTemplate.getForEntity(builder.toUriString(), League[].class).getBody();
        log.debug(" Execution finished  : getCountriesDetails");
        return leagues;
    }

    @Retry(name = "football-client")
    public TeamsStanding[]  getTeamsStanding(int leagueId) {
        log.debug(" Execution started : getCountriesDetails");
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(ACTION, actionMap.get("getStanding"));
        queryParams.put(LEAGUE_ID, String.valueOf(leagueId));
        queryParams.put(APIKEY, apiKey);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl);
        queryParams.forEach(builder::queryParam);
        TeamsStanding[] teamsStandings=restTemplate.getForEntity(builder.toUriString(), TeamsStanding[].class).getBody();
        log.debug(" Execution finished  : getCountriesDetails");
        return teamsStandings;
    }


}
