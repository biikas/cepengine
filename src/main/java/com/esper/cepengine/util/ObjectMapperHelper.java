package com.esper.cepengine.util;

import com.esper.cepengine.dto.Earthquake;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
@Service
public class ObjectMapperHelper {

    public List<Earthquake> getData() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Earthquake> earthquakes = new ArrayList<>();

        try {
            // Read JSON file and map it to a list of Earthquake objects
            Earthquake[] earthquakeArray = objectMapper.readValue(new File("src/main/resources/data.json"), Earthquake[].class);
            for (Earthquake earthquake : earthquakeArray) {
                earthquakes.add(earthquake);
                log.info("Magnitude: {}, Location: {}", earthquake.getMagnitude(), earthquake.getLocation());
            }
        } catch (IOException e) {
            log.error("Error occurred while reading data from JSON file", e);
        }

        return earthquakes;
    }
}
