package com.esper.cepengine.manager;

import com.esper.cepengine.dto.Earthquake;
import com.esper.cepengine.espersclass.EsperInstance;
import com.espertech.esper.client.EPServiceProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Bikash Shah
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CEPInstanceManager {

    private EPServiceProvider epService;

    private final Map<String, EsperInstance> esperInstances = new ConcurrentHashMap<>();

    public void getOrCreateInstance(Earthquake earthquake) {
        new EsperInstance(earthquake);
    }

    public void stopInstance(String topic) {
        EsperInstance instance = esperInstances.remove(topic);
        if (instance != null) {
            instance.stop();
        }
    }


}
