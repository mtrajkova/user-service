package com.bachelor.microservice2.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GymDto {
    private Long id;
    private String name;
    private String locationAddress;
    private String imageAddress;

    public GymDto() {}

    public GymDto(GymDto gymDto) {
        this.id = gymDto.id;
        this.name = gymDto.name;
        this.locationAddress = gymDto.locationAddress;
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

    public String getImageAddress() {
        return imageAddress;
    }
}
