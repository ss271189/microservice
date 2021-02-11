package com.ss.poc.microservice.client;

import com.ss.poc.microservice.model.Country;
import com.ss.poc.microservice.model.League;
import com.ss.poc.microservice.model.TeamsStanding;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 * FootballLeagueClient Tester.
 *
 * @author Shashank Saurabh
 * @version 1.0
 * @since <pre>Feb 11, 2021</pre>
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class FootballLeagueClientTest {


    @Autowired
    FootballLeagueClient footballLeagueClient;

    @Value("${football.league.countryId}")
    int countryId;

    @Value("${football.league.leagueId}")
    int leagueId;

    /**
     * Method: getCountriesDetails()
     */
    @Test
    public void testGetCountriesDetails() throws Exception {
     Country[] countries= footballLeagueClient.getCountriesDetails();
     assertNotNull(countries);
    }

    /**
     * Method: getLeagueDetails(int countryId)
     */
    @Test
    public void testGetLeagueDetails() throws Exception {
       League[] leagues=footballLeagueClient.getLeagueDetails(countryId);
       assertNotNull(leagues);
    }

    /**
     * Method: getTeamsStanding(int leagueId)
     */
    @Test
    public void testGetTeamsStanding() throws Exception {
       TeamsStanding[] teamsStandings=footballLeagueClient.getTeamsStanding(leagueId);
        assertNotNull(teamsStandings);
    }


} 
