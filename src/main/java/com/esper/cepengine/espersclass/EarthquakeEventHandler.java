package com.esper.cepengine.espersclass;

import com.esper.cepengine.dto.Earthquake;
import com.espertech.esper.client.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EarthquakeEventHandler {

    private EPServiceProvider epService;

    private EPStatement volcanoEventStatement;

    @Autowired
    private EarthQuakeEventSuscriber earthQuakeEventSuscriber;


    public void initService() {
        try{
            Configuration config = new Configuration();
            config.addEventType("Earthquake", Earthquake.class.getName());
            epService = EPServiceProviderManager.getDefaultProvider(config);
            createVolcanoCheckExpression();
        }catch (Exception e){
            log.info("Initialized Esper: Data not found");
        }
    }

    private void createVolcanoCheckExpression() {

        log.debug("create Critical Temperature Check Expression");
        EPAdministrator epAdmin = epService.getEPAdministrator();
        volcanoEventStatement = epAdmin.createEPL(earthQuakeEventSuscriber.getStatement());
        volcanoEventStatement.addListener(new EarthQuakeEventSuscriber());

    }


    public void handle(Earthquake event) {

        log.debug(event.toString());
        epService.getEPRuntime().sendEvent(event);

    }

    @PostConstruct
    public void afterPropertiesSet() {

        log.debug("Configuring..");
        initService();
    }

}
