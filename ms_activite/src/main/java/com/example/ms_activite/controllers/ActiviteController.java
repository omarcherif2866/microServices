package com.example.ms_activite.controllers;

import com.example.ms_activite.Dto.ActiviteDto;
import com.example.ms_activite.model.Activite;
import com.example.ms_activite.services.IActiviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activite")
@RequiredArgsConstructor
public class ActiviteController {
    private final IActiviteService iActiviteService;

    @PostMapping
    public Activite add(@RequestBody Activite activite) {
        return iActiviteService.add(activite);
    }

    @PatchMapping("/{id}")
    public Activite patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable long id){
        return iActiviteService.update(id,fields);
    }

    @DeleteMapping("/{id}")
    public boolean delete( @PathVariable long id){
        return iActiviteService.delete(id);
    }


    @GetMapping("/all")
    public List<ActiviteDto> getAll(){return (List<ActiviteDto>) iActiviteService.getAll();}


    @GetMapping("/{id}")
    public ActiviteDto getActiviteById(@PathVariable Long id) {
        return iActiviteService.getActivite(id);
    }
}
