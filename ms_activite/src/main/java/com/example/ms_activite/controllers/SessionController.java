package com.example.ms_activite.controllers;

import com.example.ms_activite.model.Session;
import com.example.ms_activite.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionRepository sessionRepository;

    @GetMapping
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
}
