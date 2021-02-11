package com.ss.poc.microservice.controller;


import com.ss.poc.microservice.entity.TeamStandingRequest;
import com.ss.poc.microservice.entity.TeamStandingResponse;
import com.ss.poc.microservice.service.FootballLeagueService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Api(description = "Resourse Controller for Football League")
@Slf4j
public class FootballLeagueController {

    @Autowired
    FootballLeagueService footballLeagueService;

    @GetMapping(path = "/teams/standing")
    public TeamStandingResponse getTeamStandings(@Valid TeamStandingRequest teamStandingRequest) {
        log.info(" Request : {}",teamStandingRequest);
        TeamStandingResponse teamStandingResponse=footballLeagueService.getTeamStandings(teamStandingRequest);
        log.info(" Resposne : {}",teamStandingResponse);
        return teamStandingResponse;
    }


}
