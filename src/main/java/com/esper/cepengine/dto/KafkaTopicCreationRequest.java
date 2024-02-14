package com.esper.cepengine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.commons.nullanalysis.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaTopicCreationRequest {

    @NotNull
    private String newTopic;

}
