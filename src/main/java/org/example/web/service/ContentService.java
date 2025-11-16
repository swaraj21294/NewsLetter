package org.example.web.service;
import java.util.stream.Collectors;
import org.example.dto.Content;
import org.example.dto.ContentDTO;
import org.example.dto.ContentResponseDTO;
import org.example.dto.Topic;
import org.example.dto.TopicDTO;
import org.example.web.controller.exception.ResourceNotFoundException;
import org.example.web.repository.ContentRepository;
import org.example.web.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private TopicRepository topicRepository;

    public List<ContentResponseDTO> getAllContents() {
        List<Content> contents = contentRepository.findAll();
        return contents.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ContentResponseDTO> getContentById(Long id) {
        return contentRepository.findById(id).map(this::convertToDTO);
    }

    public List<ContentResponseDTO> getContentsByTopic(Long topicId) {
        List<Content> contents = contentRepository.findByTopicId(topicId);
        return contents.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Content createContent(ContentDTO contentDTO) {
        Topic topic = topicRepository.findById(contentDTO.getTopicId())
            .orElseThrow(() -> new RuntimeException("Topic not found with id: " + contentDTO.getTopicId()));

        Content content = new Content();
        content.setTitle(contentDTO.getTitle());
        content.setBody(contentDTO.getBody());
        content.setScheduledTime(contentDTO.getScheduledTime());
        content.setTopic(topic);
        content.setSent(false);

        return contentRepository.save(content);
    }

    public Optional<ContentResponseDTO> updateContent(Long id, ContentDTO contentDTO) {

        return contentRepository.findById(id).map(content -> {
            // 1. Fetch the associated Topic
            Topic topic = topicRepository.findById(contentDTO.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found with id: " + contentDTO.getTopicId()));

            // 2. Update the Content Entity fields
            content.setTitle(contentDTO.getTitle());
            content.setBody(contentDTO.getBody());
            content.setScheduledTime(contentDTO.getScheduledTime());
            content.setTopic(topic);

            // 3. Save the updated entity
            Content updatedContent = contentRepository.save(content);

            // 4. Map the saved entity to the response DTO
            return mapToContentResponseDTO(updatedContent);
        });
    }

    private ContentResponseDTO mapToContentResponseDTO(Content content) {
        ContentResponseDTO dto = new ContentResponseDTO();

        // Map Content fields
        dto.setId(content.getId());
        dto.setTitle(content.getTitle());
        dto.setBody(content.getBody());
        dto.setScheduledTime(content.getScheduledTime());

        // Map the Topic relationship using a nested DTO
        if (content.getTopic() != null) {
            dto.setTopicId(content.getTopic().getId());
            dto.setTopicName(content.getTopic().getName());
        }

        return dto;
    }

    private TopicDTO mapToTopicNestedDTO(Topic topic) {
        TopicDTO dto = new TopicDTO();
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setDescription(topic.getDescription());
        // NOTE: This DTO must NOT contain the List<Content> or List<Subscriber> to prevent recursion.
        return dto;
    }
    
    public boolean deleteContent(Long id) {
        if (contentRepository.existsById(id)) {
            contentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ContentResponseDTO convertToDTO(Content content) {
        ContentResponseDTO dto = new ContentResponseDTO();
        dto.setId(content.getId());
        dto.setTitle(content.getTitle());
        dto.setBody(content.getBody());
        dto.setScheduledTime(content.getScheduledTime());
        dto.setSent(content.isSent());
        if (content.getTopic() != null) {
            dto.setTopicId(content.getTopic().getId());
            dto.setTopicName(content.getTopic().getName());
        }
        return dto;
    }
}