package com.example.ms_session.repository;

import com.example.ms_session.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<Session, String> {
}
