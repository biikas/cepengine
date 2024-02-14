package com.esper.cepengine.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Earthquake {

    private Double magnitude;
    private String location;
    private String topic;

}
