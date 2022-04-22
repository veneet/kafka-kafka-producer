package com.example.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, EventMessage> bookKafkaTemplate;

    private static final String TOPIC = "NewTopic";

    @GetMapping("/publish/{message}")
    public String publishMessage(@PathVariable("message") final String message) {
        kafkaTemplate.send(TOPIC, message);
        return "Published Successfully!";
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody EventMessage eventMessage) {
        bookKafkaTemplate.send(TOPIC, eventMessage);
        return "Published Successfully!";
    }
}
