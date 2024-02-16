package com.esper.cepengine.listener;

import com.esper.cepengine.dto.Earthquake;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

@Slf4j
@Component
public class EsperListner implements UpdateListener {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private KafkaTemplate<String ,String>kafkaTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void update(EventBean[] newData, EventBean[] oldData) {
        for (EventBean event : newData) {
            Double magnitude = (Double) event.get("magnitude");
            String location = (String) event.get("location");
            String topic = (String) event.get("topic");

            handleCriticalEvent(magnitude, location,topic);
        }
    }


    private void handleCriticalEvent(Double magnitude, String location, String topic) {
        if (magnitude != null && location != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("***************************************\n");
            sb.append("* [ALERT] : CRITICAL EVENT DETECTED from topic: ").append(topic).append("\n");
            sb.append("* Location: ").append(location).append(", Magnitude: ").append(magnitude).append("\n");
            sb.append("***************************************\n");
            System.out.println(sb);
            Earthquake event = new Earthquake(magnitude, location, topic);
            produceCriticalEvent(event);
            messagingTemplate.convertAndSend("/topic/criticalEvents", event);

        }
    }

    public void produceCriticalEvent(Earthquake earthquake) {
        try {
            String jsonData = objectMapper.writeValueAsString(earthquake);

            log.info("Sending data to topic {} with payload {}", earthquake.getTopic(), jsonData);
            String newTopic = earthquake.getTopic().concat("_OUT");
            kafkaTemplate.send(newTopic, jsonData);
        } catch (Exception e) {
            log.error("Some Error Occurred : {}", e.getMessage());
        }

    }

}
