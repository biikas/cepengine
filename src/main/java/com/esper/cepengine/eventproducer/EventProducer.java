package com.esper.cepengine.eventproducer;

import com.esper.cepengine.dto.Earthquake;
import com.esper.cepengine.util.ObjectMapperHelper;
import com.esper.cepengine.util.ThreadUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
@Getter
@Setter
public class EventProducer {

    @Autowired
    private ObjectMapperHelper objectMapperHelper;

    private String[] topics = {"A", "B", "C"};

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Random random = new Random();

    private final ObjectMapper objectMapper;

    public EventProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Async
    public void call(){
        while(true){
            try {
                List<Earthquake> data = objectMapperHelper.getData();
                processAndSendData(data);
            }catch (Exception e){
                log.error("Error ", e);
            }
            ThreadUtil.sleepForSecond(10);
        }
    }
    public void processAndSendData(List<Earthquake> data) {
        try {

            String topic = getRandomTopic();

            for (Earthquake earthquake : data) {
                // Convert Earthquake object to JSON
                String jsonData = objectMapper.writeValueAsString(earthquake);

                log.info("Sending data to topic {} with payload {}",topic,jsonData);
                kafkaTemplate.send(topic, jsonData);
            }
        } catch (JsonProcessingException e) {
            // Handle JSON processing exception
            e.printStackTrace();
        }
    }

    private String getRandomTopic() {
        return topics[random.nextInt(topics.length)];
    }
}
