package com.example.ms_session.controllers;

import com.example.ms_session.model.Session;
import com.example.ms_session.services.ISessionservice;
import com.example.ms_session.services.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {
    private final ISessionservice iSessionservice;
    private final KafkaProducer kafkaProducer;

    @PostMapping
    public Session add(@RequestBody Session session) {
        Session createdSession = iSessionservice.add(session);
        try {
            // Convertir la session en JSON
            String sessionJson = new ObjectMapper().writeValueAsString(createdSession);
            // Envoyer le message complet
            kafkaProducer.sendMessage("CREATE: " + sessionJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return createdSession;
    }


    @PatchMapping("{id}")
    public Session patchUpdate(@RequestBody Map<Object, Object> fields, @PathVariable String id) {
        Session updatedSession = iSessionservice.update(id, fields);
        kafkaProducer.sendMessage("UPDATE: " + id);
        return updatedSession;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable String id) {
        boolean isDeleted = iSessionservice.delete(id);
        if (isDeleted) {
            kafkaProducer.sendMessage("DELETE: " + id);
        }
        return isDeleted;
    }
  /* @PostMapping
   public Session add(@RequestBody Session session) {
       Session createdSession = iSessionservice.add(session);
       kafkaProducer.sendMessage("CREATE: " + createdSession.getId());
       return createdSession;
   }

    @PatchMapping("{id}")
    public Session patchUpdate(@RequestBody Map<Object, Object> fields, @PathVariable String id) {
        Session updatedSession = iSessionservice.update(id, fields);
        kafkaProducer.sendMessage("UPDATE: " + id);
        return updatedSession;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable String id) {
        boolean isDeleted = iSessionservice.delete(id);
        if (isDeleted) {
            kafkaProducer.sendMessage("DELETE: " + id);
        }
        return isDeleted;
    }*/
}
