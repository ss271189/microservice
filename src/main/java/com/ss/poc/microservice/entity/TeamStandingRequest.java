package com.ss.poc.microservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@ApiModel(description = "Required Input parameter to get team standings based on Country Name, League Name and Team Name")
public class TeamStandingRequest {
    @NotNull
    @NotBlank
    private String countryName;
    @NotNull
    @NotBlank
    private String leagueName;
    @NotNull
    @NotBlank
    private String teamName;
}
