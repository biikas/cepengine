package com.esper.cepengine.kafka;

import com.esper.cepengine.dto.response.ServerResponse;
import com.esper.cepengine.eventproducer.EventProducer;
import com.esper.cepengine.listener.EventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class KafkaTopicEditor {

    @Autowired
    private EventProducer eventProducer;
    @Autowired
    private EventListener eventListener;

    public ServerResponse createKafkaTopic(String topic){

        String[] oldTopics = eventProducer.getTopics();

        String[] newTopics = Arrays.copyOf(oldTopics, oldTopics.length + 1);

        newTopics[oldTopics.length] = topic;

        eventProducer.setTopics(newTopics);

        return ServerResponse.builder()
                .message("New topic added successfully!")
                .code(0)
                .success(true)
                .object(newTopics)
                .build();

    }

    public ServerResponse deleteKafkaTopic(String topicToDelete){

        String[] oldTopics = eventProducer.getTopics();

        boolean topicFound = false;
        for (int i = 0; i < oldTopics.length; i++) {
            if (oldTopics[i].equals(topicToDelete)) {
                topicFound = true;
                break;
            }
        }

        if (!topicFound) {
            return ServerResponse.builder()
                    .message("Topic not found!")
                    .code(2)
                    .success(false)
                    .object(topicToDelete)
                    .build();
        }

        String[] newTopics = new String[oldTopics.length - 1];

        int newIndex = 0;
        for (int i = 0; i < oldTopics.length; i++) {
            if (!oldTopics[i].equals(topicToDelete)) {
                newTopics[newIndex++] = oldTopics[i];
            }
        }

        eventProducer.setTopics(newTopics);

        return ServerResponse.builder()
                .message("New topic deleted successfully!")
                .code(0)
                .success(true)
                .object(newTopics)
                .build();

    }

    public ServerResponse getTopics(){

        String[] topics = eventProducer.getTopics();

        return ServerResponse.builder()
                .message("Topics retrieved successfully!")
                .code(0)
                .success(true)
                .object(topics)
                .build();

    }


}
