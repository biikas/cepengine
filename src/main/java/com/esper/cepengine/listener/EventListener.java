package com.esper.cepengine.listener;

import com.esper.cepengine.dto.Earthquake;
import com.esper.cepengine.espersclass.EarthquakeEventHandler;
import com.esper.cepengine.manager.CEPInstanceManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class EventListener {

    @Autowired
    private EarthquakeEventHandler earthquakeEventHandler;
    @Autowired
    private CEPInstanceManager cepInstanceManager;

    @KafkaListener(topics = {"A"}, groupId = "test")
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
    @KafkaListener(topics = {"B"}, groupId = "test")
    public void recieveFromTopicB(String message) {
        try {
            log.info("********************* Consuming from topic B********************* ");

            ObjectMapper objectMapper = new ObjectMapper();
            Earthquake earthquake = objectMapper.readValue(message, Earthquake.class);
            earthquake.setTopic("B");

            log.info(earthquake.toString());

            cepInstanceManager.getOrCreateInstance(earthquake);

        } catch (Exception e) {

            log.error("Error : ", e);
        }
    }
    @KafkaListener(topics = { "C"}, groupId = "test")
    public void recieveFromTopicC(String message) {
        try {
            log.info("********************* Consuming from topic C ********************* ");

            ObjectMapper objectMapper = new ObjectMapper();
            Earthquake earthquake = objectMapper.readValue(message, Earthquake.class);
            earthquake.setTopic("C");

            log.info(earthquake.toString());

            cepInstanceManager.getOrCreateInstance(earthquake);

        } catch (Exception e) {

            log.error("Error : ", e);
        }
    }


}
