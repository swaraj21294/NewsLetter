package org.example.web.controller;

import org.example.dto.TimeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api")
public class TimeController {
    @GetMapping("/time")
    public ResponseEntity<TimeResponseDTO> getLocalTime() {
        ZonedDateTime now = ZonedDateTime.now();
        TimeResponseDTO response = new TimeResponseDTO(now);
        
        return ResponseEntity.ok(response);
    }
}