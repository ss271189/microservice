package com.ss.poc.microservice.controller;

import com.ss.poc.microservice.entity.TeamStandingRequest;
import com.ss.poc.microservice.entity.TeamStandingResponse;
import com.ss.poc.microservice.exception.InvalidRequestParameterException;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.Assert.*;

/** 
* FootballLeagueController Tester. 
* 
* @author Shashank Saurabh
* @since <pre>Feb 11, 2021</pre> 
* @version 1.0 
*/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class FootballLeagueControllerTest {

    TeamStandingRequest requestValid;
    TeamStandingRequest requestInvalidCountry;
    TeamStandingRequest requestInvalidLeague;
    TeamStandingRequest requestInvalidTeam;
    TeamStandingRequest validationFailRequest;

    @Autowired
    FootballLeagueController footballLeagueController;

@Before
public void before() throws Exception {
    requestValid=new TeamStandingRequest("England","Championship","Bournemouth");
    requestInvalidCountry=new TeamStandingRequest("England1","Championship","Bournemouth");
    requestInvalidLeague=new TeamStandingRequest("England","Championship1","Bournemouth");
    requestInvalidTeam=new TeamStandingRequest("England","Championship","Bournemouth1");
    validationFailRequest=new TeamStandingRequest("","Championship","Bournemouth1");
}



/** 
* 
* Method: getTeamStandings(@Valid TeamStandingRequest teamStandingRequest) 
* 
*/ 
@Test
public void testGetTeamStandings() throws Exception {
    TeamStandingResponse response=footballLeagueController.getTeamStandings(requestValid);
    assertNotNull(response);
}

    @Test(expected = InvalidRequestParameterException.class)
    public void testGetTeamStandingsInvalidCountry() throws Exception {
        TeamStandingResponse response=footballLeagueController.getTeamStandings(requestInvalidCountry);
        assertNotNull(response);
    }

    @Test(expected = InvalidRequestParameterException.class)
    public void testGetTeamStandingsInvalidLeague() throws Exception {
        TeamStandingResponse response=footballLeagueController.getTeamStandings(requestInvalidLeague);
        assertNotNull(response);
    }

    @Test(expected = InvalidRequestParameterException.class)
    public void testGetTeamStandingsInvalidTeam() throws Exception {
        TeamStandingResponse response=footballLeagueController.getTeamStandings(requestInvalidTeam);
        assertNotNull(response);
    }

    @Test(expected = InvalidRequestParameterException.class)
    public void testGetTeamStandingsMethodArgumentNotValidException() throws Exception {
        TeamStandingResponse response=footballLeagueController.getTeamStandings(requestInvalidTeam);
        assertNotNull(response);
    }


} 
