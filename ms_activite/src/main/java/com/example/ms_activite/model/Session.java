package com.example.ms_activite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Session {
    @Id
    private String id;
    private String name;
}