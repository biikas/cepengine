package com.esper.cepengine.eventproducer;

import com.esper.cepengine.dto.Earthquake;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class EsperProducer {
    public EsperProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;



    public void produceCriticalEvent(Earthquake earthquake){
        try{
            String jsonData = objectMapper.writeValueAsString(earthquake);

            log.info("Sending data to topic {} with payload {}",earthquake.getTopic(),jsonData);
            String newTopic =  earthquake.getTopic().concat("_OUT");
            kafkaTemplate.send(newTopic, jsonData);
        }catch (Exception e){
            log.error("Some Error Occurred : {}",e.getMessage());
        }

    }
}
