package com.example.ms_activite.repository;

import com.example.ms_activite.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
}
