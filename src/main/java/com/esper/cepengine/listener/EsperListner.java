package com.esper.cepengine.listener;

import com.esper.cepengine.dto.Earthquake;
import com.esper.cepengine.eventproducer.EsperProducer;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

@Component
public class EsperListner implements UpdateListener {

    @Autowired
    private EsperProducer esperProducer;

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
            esperProducer.produceCriticalEvent(event);
            messagingTemplate.convertAndSend("/topic/criticalEvents", event);

        }
    }

}
