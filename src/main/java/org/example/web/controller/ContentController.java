package org.example.web.controller;

import jakarta.validation.Valid;
import org.example.dto.Content;
import org.example.dto.ContentDTO;
import org.example.dto.ContentResponseDTO;
import org.example.web.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contents")
@CrossOrigin(origins = "*")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping
    public List<ContentResponseDTO> getAllContents() {
        return contentService.getAllContents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponseDTO> getContentById(@PathVariable Long id) {
        Optional<ContentResponseDTO> content = contentService.getContentById(id);
        return content.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/topic/{topicId}")
    public List<ContentResponseDTO> getContentsByTopic(@PathVariable Long topicId) {
        return contentService.getContentsByTopic(topicId);
    }

    @PostMapping
    public Content createContent(@Valid @RequestBody ContentDTO contentDTO) {
        return contentService.createContent(contentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentResponseDTO> updateContent(
        @PathVariable Long id,
        @Valid @RequestBody ContentDTO contentDTO) {
        Optional<ContentResponseDTO> updatedContent = contentService.updateContent(id, contentDTO);
        return updatedContent
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContent(@PathVariable Long id) {
        if (contentService.deleteContent(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}