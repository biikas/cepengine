package com.esper.cepengine.listener;

import com.esper.cepengine.dto.Earthquake;
import com.esper.cepengine.manager.CEPInstanceManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
@Getter
@Setter
public class EventListener {

    @Autowired
    private CEPInstanceManager cepInstanceManager;

    private String[] topics = {"A", "B", "C"};

    @KafkaListener(topics = topics, groupId = "test")
    public void recieveFromTopicA(String message) {
        try {
            log.info("********************* Consuming from topic A ********************* ");

            ObjectMapper objectMapper = new ObjectMapper();
            Earthquake earthquake = objectMapper.readValue(message, Earthquake.class);
            earthquake.setTopic("A");
            log.info(earthquake.toString());

            cepInstanceManager.getOrCreateInstance(earthquake);

        } catch (Exception e) {

            log.error("Error : ", e);
        }
    }

}
