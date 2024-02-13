package com.esper.cepengine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class Earthquake {

    private String magnitude;
    private String location;
    private String topic;

}
