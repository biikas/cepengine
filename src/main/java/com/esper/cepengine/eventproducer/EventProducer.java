package com.esper.cepengine.eventproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class EventProducer {

    private final String[] topics = {"A", "B", "C"};
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Random random = new Random();

    public EventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Scheduled(fixedDelay = 5000)
    public void call(){

        String topic = getRandomTopic();
        //todo get data from json
        String jsonData= "";
        kafkaTemplate.send(topic, jsonData);

    }

    private String getRandomTopic() {
        return topics[random.nextInt(topics.length)];
    }
}
