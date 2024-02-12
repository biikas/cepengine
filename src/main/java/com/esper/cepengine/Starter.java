package com.esper.cepengine;

import com.esper.cepengine.eventproducer.EventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * @author Bikash Shah
 */
@Slf4j
public class Starter implements CommandLineRunner {

    @Autowired
    private EventProducer eventProducer;

    @Override
    public void run(String... args) throws Exception {
        eventProducer.call();
    }
}
