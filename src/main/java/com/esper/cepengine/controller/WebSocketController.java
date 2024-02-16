package com.esper.cepengine.controller;

import com.esper.cepengine.dto.Earthquake;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Bikash Shah
 */
@Controller
public class WebSocketController {

    @MessageMapping("/sendCriticalEvent")
    @SendTo("/topic/criticalEvents")
    public Earthquake sendCriticalEvent(Earthquake event) {
        return event;
    }
}
