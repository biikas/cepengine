package com.esper.cepengine.controller;

import com.esper.cepengine.dto.KafkaTopicCreationRequest;
import com.esper.cepengine.dto.response.ServerResponse;
import com.esper.cepengine.kafka.KafkaTopicEditor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/topic")
public class KafkaTopicsController {

    @Autowired
    private KafkaTopicEditor kafkaTopicEditor;

    @PostMapping("/create")
    public ResponseEntity<ServerResponse> createTopic(@RequestBody KafkaTopicCreationRequest topics) {
        try {
            log.debug("Entering the kafka topic creation api........");
            return ResponseEntity.ok(kafkaTopicEditor.createKafkaTopic(topics.getNewTopic()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/delete")
    public ResponseEntity<ServerResponse> deleteTopic(@RequestBody KafkaTopicCreationRequest topics) {
        try {
            log.debug("Entering the kafka topic deletion api........");
            return ResponseEntity.ok(kafkaTopicEditor.deleteKafkaTopic(topics.getNewTopic()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get")
    public ResponseEntity<ServerResponse> getTopic() {
        try {
            log.debug("Entering the kafka topic deletion api........");
            return ResponseEntity.ok(kafkaTopicEditor.deleteKafkaTopic(topics.getNewTopic()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
