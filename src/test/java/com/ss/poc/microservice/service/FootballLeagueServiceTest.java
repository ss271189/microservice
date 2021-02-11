package com.ss.poc.microservice.service;

import com.ss.poc.microservice.client.FootballLeagueClient;
import com.ss.poc.microservice.entity.TeamStandingRequest;
import com.ss.poc.microservice.entity.TeamStandingResponse;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

/** 
* FootballLeagueService Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 11, 2021</pre> 
* @version 1.0 
*/ 
public class FootballLeagueServiceTest {
    TeamStandingRequest request;

    @Autowired
    FootballLeagueService footballLeagueService;

    @Before
    public void before() throws Exception {
        request=new TeamStandingRequest("England","Championship","Bournemouth");
    }

/** 
* 
* Method: getTeamStandings(TeamStandingRequest teamStandingRequest) 
* 
*/ 
@Test
public void testGetTeamStandings() throws Exception { 
  TeamStandingResponse response=footballLeagueService.getTeamStandings(request);
  assertNotNull(response);
} 




} 
