package com.esper.cepengine.esper;

import com.esper.cepengine.dto.Earthquake;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
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
        config.addEventTypeAutoName("com.esper.cepengine.dto");
        epService = EPServiceProviderManager.getDefaultProvider(config);
        createVolcanoCheckExpression();
    }

    private void createVolcanoCheckExpression() {

        log.debug("create Critical Temperature Check Expression");
        volcanoEventStatement = epService.getEPAdministrator().createEPL(volcanoEventSuscriber.getStatement());
        volcanoEventStatement.setSubscriber(volcanoEventSuscriber);
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
