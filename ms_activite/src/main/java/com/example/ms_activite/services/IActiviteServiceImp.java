package com.example.ms_activite.services;



import com.example.ms_activite.Dto.SessionDto;
import com.example.ms_activite.Dto.ActiviteDto;
import com.example.ms_activite.FeignClient.SessionClient;
import com.example.ms_activite.model.Activite;
import com.example.ms_activite.repository.ActiviteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IActiviteServiceImp implements IActiviteService {

    private final ActiviteRepository activiteRepository;
    private final SessionClient sessionFeignClient;
    private final RestTemplate restTemplate;

    @Override
    public Activite add(Activite activite) {
        return activiteRepository.save(activite);
    }

    @Override
    public Activite update(long id, Map<Object, Object> fields) {
        Activite stock = activiteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Activite not found: " + id));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Activite.class, (String) key);
            field.setAccessible(true);


        });
        return activiteRepository.save(stock);
    }

    @Override
    public boolean delete(long id) {
        activiteRepository.deleteById(id);
        return activiteRepository.existsById(id);    }


   @Override
   public ActiviteDto getActivite(long id) {
       Activite activite = activiteRepository.findById(id).orElseThrow(() -> new RuntimeException("Activite not found"));
       String sessionId = activite.getSessionId();
       System.out.println("Session ID: " + sessionId);
       SessionDto sessionDto = sessionFeignClient.getSessionById(sessionId);
       return ActiviteDto.builder()
               .name(activite.getName())
               .budget(activite.getBudget())
               .sessionDto(sessionDto)
               .build();
   }


   /* @Override
    public ActiviteDto getActivite(long id) {
        Activite activite = activiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activite not found"));

        String sessionId = activite.getSessionId();
        System.out.println("Session ID: " + sessionId);

        // Utilisation de RestTemplate pour appeler le service Session
        SessionDto sessionDto = restTemplate.getForObject("http://localhost:8082/session/{sessionId}",
                SessionDto.class, sessionId);

        return ActiviteDto.builder()
                .name(activite.getName())
                .budget(activite.getBudget())
                .sessionDto(sessionDto)
                .build();
    }*/

    @Override
    public List<ActiviteDto> getAll() {
        List<Activite> activites = activiteRepository.findAll();
        return activites.stream()
                .map(activite -> {
                    SessionDto sessionDto = sessionFeignClient.getSessionById(activite.getSessionId());
                    return ActiviteDto.builder()
                            .name(activite.getName())
                            .budget(activite.getBudget())
                            .sessionDto(sessionDto)
                            .build();
                })
                .collect(Collectors.toList());
    }


}
