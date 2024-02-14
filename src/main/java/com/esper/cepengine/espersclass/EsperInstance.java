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
    private EPStatement epStatement;
    private EPAdministrator epAdmin;

    public EsperInstance() {
    }

    public EsperInstance(String topic) {
        try{
            Configuration config = new Configuration();
            config.addEventType("Earthquake", Earthquake.class.getName());
            epService = EPServiceProviderManager.getDefaultProvider(config);
            epAdmin = epService.getEPAdministrator();
            epStatement = epAdmin.createEPL(getEpl(topic));
            epStatement.addListener(new EsperListner());
        }catch (Exception e){
            log.info("NO DATA FOUND");
        }

    }

    private String getEpl(String topic) {
        return "select magnitude, location, topic " +
                "from Earthquake " +
                "where magnitude >= 7.2 " +
                "and topic ='" + topic + "'";
    }


    public void sendEvent(Earthquake event) {
        epService.getEPRuntime().sendEvent(event);
    }

    public void destroy(Earthquake event) {
        epService.destroy();
    }


    public void stop() {
        // Additional cleanup or shutdown logic if needed
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Initialization logic if needed
    }
}
