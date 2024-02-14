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

    private final Map<String, EsperInstance> instancesByTopic = new ConcurrentHashMap<>();

    public void getOrCreateInstance(Earthquake earthquake) {
        String topic = earthquake.getTopic();

        EsperInstance instance = instancesByTopic.get(topic);
        if (instance == null) {
            instance = new EsperInstance(topic);
            instancesByTopic.put(topic, instance);
        }

        instance.sendEvent(earthquake);

    }

    public void stopInstance(String topic) {
        EsperInstance instance = instancesByTopic.remove(topic);
        if (instance != null) {
            instance.stop();
        }
    }


}
