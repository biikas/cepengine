package com.esper.cepengine.espersclass;

import com.esper.cepengine.dto.Earthquake;
import com.esper.cepengine.listener.EsperListner;
import com.espertech.esper.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EsperInstance implements InitializingBean {

    private EPServiceProvider epService;
    private EPStatement volcanoEventStatement;

    public EsperInstance() {}

    public EsperInstance(Earthquake earthquake) {
        Configuration config = new Configuration();
        config.addEventType("Earthquake", Earthquake.class.getName());
        epService = EPServiceProviderManager.getDefaultProvider(config);
        configureStatements();
        sendEvent(earthquake);
        volcanoEventStatement.destroy();
    }

    public void sendEvent(Earthquake event) {
        epService.getEPRuntime().sendEvent(event);
    }

    private void configureStatements() {
        EPAdministrator epAdmin = epService.getEPAdministrator();
        volcanoEventStatement = epAdmin.createEPL("select magnitude, location, topic from Earthquake where magnitude >= '7.2'");
        volcanoEventStatement.addListener(new EsperListner());
    }


    public void stop() {
        // Additional cleanup or shutdown logic if needed
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Initialization logic if needed
    }
}
