package com.esper.cepengine.listener;

import com.esper.cepengine.dto.Earthquake;
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

    @KafkaListener(topics = {"A"}, groupId = "test")
    public void recieveFromTopicA(String message) {
        try {
            log.info("********************* Consuming from topic ********************* ");

            ObjectMapper objectMapper = new ObjectMapper();
            Earthquake earthquake = objectMapper.readValue(message, Earthquake.class);
            log.info(earthquake.toString());

            //Todo sent to esprt

        } catch (Exception e) {

            //todo  handle error
            log.error("Error : ", e);
        }
    }
    @KafkaListener(topics = {"B"}, groupId = "test")
    public void recieveFromTopicB(String message) {
        try {
            log.info("********************* Consuming from topic ********************* ");

            ObjectMapper objectMapper = new ObjectMapper();
            Earthquake earthquake = objectMapper.readValue(message, Earthquake.class);
            log.info(earthquake.toString());

            //Todo sent to esprt

        } catch (Exception e) {

            //todo  handle error
            log.error("Error : ", e);
        }
    }
    @KafkaListener(topics = { "C"}, groupId = "test")
    public void recieveFromTopicC(String message) {
        try {
            log.info("********************* Consuming from topic ********************* ");

            ObjectMapper objectMapper = new ObjectMapper();
            Earthquake earthquake = objectMapper.readValue(message, Earthquake.class);
            log.info(earthquake.toString());

            //Todo sent to esprt

        } catch (Exception e) {

            //todo  handle error
            log.error("Error : ", e);
        }
    }
}
