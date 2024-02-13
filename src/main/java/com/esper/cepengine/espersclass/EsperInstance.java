package com.esper.cepengine.espersclass;

import com.esper.cepengine.dto.Earthquake;
import com.espertech.esper.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Bikash Shah
 */
@Component
@Slf4j
@Scope(value = "singleton")
public class EsperInstance implements InitializingBean {

    private EPServiceProvider epService;
    public EsperInstance(Earthquake earthquake) {

        Configuration config = new Configuration();
        config.addEventType("Earthquake", Earthquake.class.getName());
        epServiceProvider = EPServiceProviderManager.getDefaultProvider(config);
        configureStatements();
    }

    private EPServiceProvider epServiceProvider;
    private EPStatement volcanoEventStatement;
    @Autowired
    private EarthQuakeEventSuscriber earthQuakeEventSuscriber;


    public EsperInstance() {

    }

    private void configureStatements() {
        EPAdministrator epAdmin = epServiceProvider.getEPAdministrator();
        volcanoEventStatement = epAdmin.createEPL("select magnitude, location, topic from Earthquake where magnitude >='7.2'");
        volcanoEventStatement.addListener(new EarthQuakeEventSuscriber());
    }


    public void stop() {
        // Additional cleanup or shutdown logic if needed
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
