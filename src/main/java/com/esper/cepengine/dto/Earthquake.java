package com.esper.cepengine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Earthquake {

    private String magnitude;
    private String location;

}
