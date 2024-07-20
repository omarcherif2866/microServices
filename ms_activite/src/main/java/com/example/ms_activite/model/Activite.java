package com.example.ms_activite.model;

import com.example.shared.model.GenericEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activite extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private float budget;
    private String sessionId;
    private String name;
}
