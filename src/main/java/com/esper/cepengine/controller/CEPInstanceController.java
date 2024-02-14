package com.esper.cepengine.controller;

import com.esper.cepengine.dto.Earthquake;
import com.esper.cepengine.manager.CEPInstanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bikash Shah
 */
@RestController
@RequestMapping("/instances")
public class CEPInstanceController {

    @Autowired
    private  CEPInstanceManager cepInstanceManager;
//
//    @Autowired
//    public CEPInstanceController(CEPInstanceManager cepInstanceManager) {
//        this.cepInstanceManager = cepInstanceManager;
//    }
//
//    @PostMapping("/{topic}/start")
//    public void startInstance(@PathVariable String topic) {
//        //cepInstanceManager.getOrCreateInstance(topic,new Earthquake());
//    }
//
    @PostMapping("/{topic}/stop")
    public void stopInstance(@PathVariable String topic) {
        cepInstanceManager.stopInstance(topic);
    }
}
