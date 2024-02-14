package com.esper.cepengine.listener;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import org.springframework.stereotype.Component;

@Component
public class EsperListner implements UpdateListener {

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
        }
    }

}
