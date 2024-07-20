package com.example.ms_session.controllers;

import com.example.ms_session.services.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public String sendMessage(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return "Message sent to Kafka topic";
    }
}
