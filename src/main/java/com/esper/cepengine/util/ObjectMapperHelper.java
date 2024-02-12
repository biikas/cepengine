package com.esper.cepengine.util;

import com.esper.cepengine.dto.Earthquake;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
                System.out.println("Magnitude: " + earthquake.getMagnitude() + ", Location: " + earthquake.getLocation());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return earthquakes;
    }
}
