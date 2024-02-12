package com.esper.cepengine.listener;

import com.esper.cepengine.dto.Earthquake;
import com.esper.cepengine.esper.VolcanoEventHandler;
import com.espertech.esper.client.EPServiceProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
    private VolcanoEventHandler volcanoEventHandler;

    @KafkaListener(topics = {"A", "B", "C"}, groupId = "test")
    public void recieveFromTopic(String message) {
        try {
            log.info("********************* Consuming from topic ********************* ");

            ObjectMapper objectMapper = new ObjectMapper();
            Earthquake earthquake = objectMapper.readValue(message, Earthquake.class);
            log.info(earthquake.toString());

            //Todo sent to esprt
            volcanoEventHandler.handle(earthquake);

        } catch (Exception e) {

            //todo  handle error
            log.error("Error : ", e);
        }
    }

}
