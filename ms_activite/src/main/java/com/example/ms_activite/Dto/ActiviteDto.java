package com.example.ms_activite.Dto;

import lombok.Builder;

@Builder
public record ActiviteDto(String name, float budget, SessionDto sessionDto){}

