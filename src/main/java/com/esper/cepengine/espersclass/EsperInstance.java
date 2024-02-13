package com.esper.cepengine.espersclass;

import com.esper.cepengine.dto.Earthquake;
import com.espertech.esper.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EsperInstance implements InitializingBean {

    private EPServiceProvider epService;
    private EPStatement volcanoEventStatement;

    @Autowired
    private EarthQuakeEventSuscriber earthQuakeEventSuscriber;

    @Autowired
    private EarthquakeEventHandler earthquakeEventHandler;

    public EsperInstance() {}

    public EsperInstance(Earthquake earthquake) {
        Configuration config = new Configuration();
        config.addEventType("Earthquake", Earthquake.class.getName());
        epService = EPServiceProviderManager.getDefaultProvider(config);
        configureStatements();
        sendEvent(earthquake);
    }

    public void sendEvent(Earthquake event) {
        epService.getEPRuntime().sendEvent(event);
    }

    private void configureStatements() {
        EPAdministrator epAdmin = epService.getEPAdministrator();
        volcanoEventStatement = epAdmin.createEPL("select magnitude, location, topic from Earthquake where magnitude >= '7.2'");
        volcanoEventStatement.addListener((newEvents, oldEvents) -> {
            for (EventBean event : newEvents) {
                String magnitude = (String) event.get("magnitude");
                String location = (String) event.get("location");
                String topic = (String) event.get("topic");
                handleCriticalEvent(magnitude, location, topic);
            }
        });
    }

    private void handleCriticalEvent(String magnitude, String location, String topic) {
        if (magnitude != null && location != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("***************************************\n");
            sb.append("* [ALERT] : CRITICAL EVENT DETECTED from topic: ").append(topic).append("\n");
            sb.append("* Location: ").append(location).append(", Magnitude: ").append(magnitude).append("\n");
            sb.append("***************************************\n");
            System.out.println(sb);
        }
    }

    public void stop() {
        // Additional cleanup or shutdown logic if needed
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Initialization logic if needed
    }
}
