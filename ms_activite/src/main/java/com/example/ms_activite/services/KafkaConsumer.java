package com.example.ms_activite.services;

import com.example.ms_activite.model.Session;
import com.example.ms_activite.repository.SessionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SessionRepository sessionRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "session-changes", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        // Gérer les messages (ex : ADD: {"id":"1","name":"Session1"}, UPDATE: {"id":"1","name":"Session1"}, DELETE: 1)
        try {
            String[] messageParts = message.split(": ", 2);
            String action = messageParts[0];
            String payload = messageParts[1];

            switch (action) {
                case "ADD":
                case "CREATE":  // Ajoutez ce cas pour accepter l'action "CREATE"
                case "UPDATE":
                    Session session = objectMapper.readValue(payload, Session.class);
                    sessionRepository.save(session);
                    System.out.println("Session saved: " + session);
                    break;
                case "DELETE":
                    sessionRepository.deleteById(payload);
                    System.out.println("Session deleted: " + payload);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action: " + action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}