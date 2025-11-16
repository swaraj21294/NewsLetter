package org.example.web.controller;

import jakarta.validation.Valid;
import org.example.dto.Subscriber;
import org.example.dto.SubscriberDTO;
import org.example.dto.SubscriberResponseDTO;
import org.example.web.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscribers")
@CrossOrigin(origins = "*")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @GetMapping
    public List<SubscriberResponseDTO> getAllSubscribers() {
        return subscriberService.getAllSubscribers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriberResponseDTO> getSubscriberById(@PathVariable Long id) {
        Optional<SubscriberResponseDTO> subscriber = subscriberService.getSubscriberById(id);
        return subscriber.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/topic/{topicId}")
    public List<SubscriberResponseDTO> getSubscribersByTopic(@PathVariable Long topicId) {
        return subscriberService.getSubscribersByTopic(topicId);
    }

    @PostMapping
    public Subscriber createSubscriber(@Valid @RequestBody SubscriberDTO subscriberDTO) {
        return subscriberService.createSubscriber(subscriberDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriberResponseDTO> updateSubscriber(@PathVariable Long id, @Valid @RequestBody SubscriberDTO subscriberDTO) {
        SubscriberResponseDTO updatedSubscriber = subscriberService.updateSubscriber(id, subscriberDTO);
        return new ResponseEntity<>(updatedSubscriber, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubscriber(@PathVariable Long id) {
        if (subscriberService.deleteSubscriber(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}