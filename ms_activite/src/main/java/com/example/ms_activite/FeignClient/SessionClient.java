package com.example.ms_activite.FeignClient;

import com.example.ms_activite.Dto.SessionDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "session", url = "http://localhost:8082/session" )
public interface SessionClient {

    @GetMapping("/{sessionId}")
    @CircuitBreaker(name = "SessionClient", fallbackMethod = "getSessionByIdFallback")
    SessionDto getSessionById(@PathVariable("sessionId") String sessionId);

    default SessionDto getSessionByIdFallback(String sessionId, Throwable t) {
        return new SessionDto("la session pour le moment est indisonible , reesayer plus tard");
    }

}

