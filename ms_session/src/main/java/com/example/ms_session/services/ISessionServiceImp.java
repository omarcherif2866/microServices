package com.example.ms_session.services;

import com.example.ms_session.model.Session;
import com.example.ms_session.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class ISessionServiceImp implements ISessionservice {

    private final SessionRepository sessionRepository;

    @Override
    public Session add(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session update(String id, Map<Object, Object> fields) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session not found: " + id));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Session.class, (String) key);
            field.setAccessible(true);


        });
        return sessionRepository.save(session);
    }

    @Override
    public boolean delete(String id) {
        sessionRepository.deleteById(id);
        return sessionRepository.existsById(id);    }

    @Override
    public Session getSession(String id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));    }

    @Override
    public Set<Session> getAllSessions() {
        return (Set<Session>) sessionRepository.findAll();
    }
}
