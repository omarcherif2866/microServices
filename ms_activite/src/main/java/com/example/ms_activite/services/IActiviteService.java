package com.example.ms_activite.services;

import com.example.ms_activite.Dto.ActiviteDto;
import com.example.ms_activite.model.Activite;
import com.example.shared.service.IGenericService;

import java.util.List;
import java.util.Map;
import java.util.Set;

 public interface IActiviteService extends IGenericService<Activite> {

    ActiviteDto getActivite(long id);
     List<ActiviteDto> getAll();
}
