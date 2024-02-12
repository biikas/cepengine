package com.esper.cepengine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Earthquake {

    private String magnitude;
    private String location;

    @JsonProperty("magnitude")
    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Received from Kafka Earthquake{" +
                "magnitude='" + magnitude + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

}
