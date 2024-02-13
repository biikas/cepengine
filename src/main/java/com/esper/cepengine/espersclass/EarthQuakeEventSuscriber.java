package com.esper.cepengine.espersclass;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EarthQuakeEventSuscriber implements UpdateListener {
    public String getStatement() {
        return "select magnitude, location, topic from Earthquake where magnitude >='7.2'";
    }

    @Override
    public void update(EventBean[] newData, EventBean[] oldData) {
        for (EventBean event : newData) {
            String magnitude = (String) event.get("magnitude");
            String location = (String) event.get("location");
            String topic = (String) event.get("topic");

            handleCriticalEvent(magnitude, location,topic);
        }
    }

    private void handleCriticalEvent(String magnitude, String location,String topic) {
        if(magnitude !=null && location!=null){
            StringBuilder sb = new StringBuilder();
            sb.append("***************************************");
            sb.append("\n* [ALERT] : CRITICAL EVENT DETECTED from topic: "+topic);
            sb.append("\n* Location: " + location + ", Magnitude: " + magnitude);
            sb.append("\n***************************************");

            // Log or perform any action with the critical event
            System.out.println(sb);
        }

    }

}
