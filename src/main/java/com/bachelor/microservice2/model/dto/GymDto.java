package com.bachelor.microservice2.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GymDto {
    private Long id;
    private String name;
    private String locationAddress;
    private String imageLocation;
    private String phoneNumber;

    public GymDto() {
    }

    public GymDto(GymDto gymDto) {
        this.id = gymDto.id;
        this.name = gymDto.name;
        this.locationAddress = gymDto.locationAddress;
        this.imageLocation = gymDto.imageLocation;
        this.phoneNumber = gymDto.phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getImageLocation() {
        return imageLocation;
    }
}
