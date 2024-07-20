package com.example.ms_activite.repository;

import com.example.ms_activite.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
}
