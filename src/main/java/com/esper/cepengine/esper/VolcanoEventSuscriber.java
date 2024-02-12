package com.esper.cepengine.esper;

import com.esper.cepengine.dto.Earthquake;
import com.esper.cepengine.interfaces.StatementSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class VolcanoEventSuscriber implements StatementSubscriber {
    public String getStatement() {
        return "select magnitude, location from Earthquake";
    }

    public void update(Map<String, Earthquake> eventMap) {

        Earthquake temp1 = (Earthquake) eventMap.get("magnitude");
        Earthquake temp2 = (Earthquake) eventMap.get("location");

        StringBuilder sb = new StringBuilder();
        sb.append("***************************************");
        sb.append("\n* [ALERT] : CRITICAL EVENT DETECTED! ");
        sb.append("\n* " + temp1 + " > " + temp2);
        sb.append("\n***************************************");

        log.debug(sb.toString());
    }

}
