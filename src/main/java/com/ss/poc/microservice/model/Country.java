package com.ss.poc.microservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Country {

    @JsonProperty("country_id")
    private int countryId;
    @JsonProperty("country_name")
    private String countryName;

}
