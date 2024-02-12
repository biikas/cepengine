package com.esper.cepengine.listener;

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

    @KafkaListener(topics = {"A","B","C"}, groupId = "group")
    public void recieveFromTopic(String message) {
        try {
            log.debug("********************* Consuming from topic ********************* ");

            //todo mapto dto and send to esper


        } catch (Exception e) {

            //todo  handle error
            log.error("Error : ", e);
        }
    }
}
