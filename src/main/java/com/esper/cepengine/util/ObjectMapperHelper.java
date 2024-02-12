package com.esper.cepengine.util;

import com.esper.cepengine.dto.Earthquake;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@Slf4j
@Service
public class ObjectMapperHelper {

    public List<Earthquake> getData() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Earthquake> earthquakes = null;

        try {
            // Read JSON file and map it to a list of Earthquake objects
            earthquakes = Arrays.asList(
                    objectMapper.readValue(new File("D:/Projects/cepengine/src/main/resources/data.json"), Earthquake[].class)
            );

            // Now you can work with the list of Earthquake objects
            for (Earthquake earthquake : earthquakes) {
                log.info("Magnitude: {} , Location: {} ",earthquake.getMagnitude() , earthquake.getLocation());
            }
        } catch (IOException e) {
            log.info("some error occured");
            e.printStackTrace();
        }

        return earthquakes;
    }
}
