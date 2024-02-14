package com.esper.cepengine.mapper;

import com.esper.cepengine.dto.Earthquake;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EarthquakeMapper {

    public static Earthquake mapToEarthquake(Double magnitude, String location, String topic){
        Earthquake earthquake = new Earthquake();
        log.debug("Mapping the listener to the Earthquake class");
        earthquake.setLocation(location);
        earthquake.setMagnitude(magnitude);
        earthquake.setTopic(topic);
        return earthquake;
    }

}
