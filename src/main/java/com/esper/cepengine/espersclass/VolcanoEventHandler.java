package com.esper.cepengine.espersclass;

import com.esper.cepengine.dto.Earthquake;
import com.espertech.esper.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@Scope(value = "singleton")
public class VolcanoEventHandler implements InitializingBean {

    private EPServiceProvider epService;

    private EPStatement volcanoEventStatement;

    @Autowired
    private VolcanoEventSuscriber volcanoEventSuscriber;


    public void initService() {
        Configuration config = new Configuration();
        config.addEventType("Earthquake", Earthquake.class.getName());
        epService = EPServiceProviderManager.getDefaultProvider(config);
        createVolcanoCheckExpression();
    }

    private void createVolcanoCheckExpression() {

        log.debug("create Critical Temperature Check Expression");
        EPAdministrator epAdmin = epService.getEPAdministrator();
        volcanoEventStatement = epAdmin.createEPL(volcanoEventSuscriber.getStatement());
        volcanoEventStatement.addListener(new VolcanoEventSuscriber());

    }


    public void handle(Earthquake event) {

        log.debug(event.toString());
        epService.getEPRuntime().sendEvent(event);

    }

    @Override
    public void afterPropertiesSet() {

        log.debug("Configuring..");
        initService();
    }

}
