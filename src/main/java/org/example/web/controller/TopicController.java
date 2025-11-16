package org.example.web.controller;

import jakarta.validation.Valid;
import org.example.dto.Topic;
import org.example.dto.TopicDTO;
import org.example.dto.TopicResponseDTO;
import org.example.web.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin(origins = "*")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public ResponseEntity<List<TopicResponseDTO>> getAllTopics() {
        List<TopicResponseDTO> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> getTopicById(@PathVariable Long id) {
        Optional<TopicResponseDTO> topic = topicService.getTopicById(id);
        return topic.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Topic createTopic(@Valid @RequestBody TopicDTO topicDTO) {
        return topicService.createTopic(topicDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> updateTopic(
        @PathVariable Long id,
        @Valid @RequestBody TopicDTO topicDTO) {
        Optional<TopicResponseDTO> updatedTopic = topicService.updateTopic(id, topicDTO);
        return updatedTopic
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        if (topicService.deleteTopic(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}